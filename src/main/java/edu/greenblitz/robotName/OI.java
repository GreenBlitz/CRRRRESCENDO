package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.GetToClimbMode;
import edu.greenblitz.robotName.commands.LED.UpdateLEDStateDefaultCommand;
import edu.greenblitz.robotName.commands.PrepareToScore;
import edu.greenblitz.robotName.commands.ScoreOnReady;
import edu.greenblitz.robotName.commands.ShootOnReady;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristWithRunFunnel;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowByJoystick;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteInRoller;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToAmp;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToTrap;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristByButton;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.climbing.ClimbUp;
import edu.greenblitz.robotName.commands.climbing.CloseAndThenHoldSolenoid;
import edu.greenblitz.robotName.commands.climbing.getLifterReady;
import edu.greenblitz.robotName.commands.climbing.lifter.MoveLifterByJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteFromFeeder;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringModeWithPivotForJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.intake.RunIntakeByPower;
import edu.greenblitz.robotName.commands.shchori.ClimbOrArmDown;
import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class OI {

    private static OI instance;

    private final SmartJoystick mainJoystick;

    private final SmartJoystick secondJoystick;

    private final SmartJoystick thirdJoystick;

    private final SmartJoystick fourthJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        thirdJoystick = new SmartJoystick(RobotConstants.Joystick.THIRD);
        fourthJoystick = new SmartJoystick(RobotConstants.Joystick.FOURTH);

        initButtons();
        initializeDefaultCommands();
    }

    public static void init() {
        if (instance == null) {
            instance = new OI();
        }
    }

    public static OI getInstance() {
        init();
        return instance;
    }

    public SmartJoystick getMainJoystick() {
        return mainJoystick;
    }

    public SmartJoystick getSecondJoystick() {
        return secondJoystick;
    }

    public SmartJoystick getThirdJoystick() {
        return thirdJoystick;
    }

    public SmartJoystick getFourthJoystick() {
        return fourthJoystick;
    }

    public void initButtons() {
        romyButtons();
        shchoriButtons();
    }

    public void romyButtons() {
        //Collect Note
        mainJoystick.R1.whileTrue(new CollectNoteToScoringModeWithPivotForJoystick());
        mainJoystick.POV_DOWN.whileTrue(new CollectNoteFromFeeder());

        //Auto Aim For Speaker Or Amp
        mainJoystick.L2.whileTrue(new PrepareToScore());

        //Shoot
        mainJoystick.L2_HARD.whileTrue(new ScoreOnReady());

        //Reset Robot Angle
        mainJoystick.Y.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetChassisPose()));

        //Climbing
        mainJoystick.A.whileTrue(new ReleaseNoteFromRollerToTrap());//release note from roller and take wrist back
        mainJoystick.B.whileTrue(new MoveWristToAngle(WristConstants.PresetPositions.SCORE_TRAP));

        //disable and enable the limelight led
        mainJoystick.BACK.onTrue(new InstantCommand(() -> MultiLimelight.getInstance().setLimelightsLedOff()));
        mainJoystick.START.onTrue(new InstantCommand(() -> MultiLimelight.getInstance().setLimelightsLedOn()));

        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
    }

    public void shchoriButtons() {
        //ScoringMode
        secondJoystick.START.onTrue(new ToggleScoringMode());
        secondJoystick.X.whileTrue(new GetToClimbMode());

        //Reverse Intake
        secondJoystick.B.whileTrue(new RunIntakeByPower(-0.5));

        //Arm
        secondJoystick.BACK.onTrue(new InstantCommand(() -> Roller.getInstance().setObjectOut()));
        secondJoystick.A.onTrue(new ReleaseNoteFromRollerToAmp());
        secondJoystick.R1.whileTrue(new MoveElbowByJoystick(secondJoystick, SmartJoystick.Axis.LEFT_Y));

        //FlyWheel Run
        secondJoystick.L1.whileTrue(new RunFlyWheelByVelocityUntilInterrupted(FlyWheelConstants.SHOOTING_VELOCITY, secondJoystick));

        //Wrist Control
        secondJoystick.POV_UP.whileTrue(new MoveWristByButton(true));
        secondJoystick.POV_DOWN.whileTrue(new MoveWristByButton(false));

        //Note-Roller Control
        secondJoystick.POV_RIGHT.whileTrue(new MoveNoteInRoller(true));
        secondJoystick.POV_LEFT.whileTrue(new MoveNoteInRoller(false));

        //Funnel
        secondJoystick.R1.whileTrue(new RunFunnelByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_Y));

        //Climbing
        secondJoystick.Y.whileTrue(new ClimbOrArmDown());
        secondJoystick.R2.whileTrue(new MoveLifterByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_TRIGGER));
        secondJoystick.L2.whileTrue(
                new SequentialCommandGroup(
                        new CloseAndThenHoldSolenoid(),
                        new MoveLifterByJoystick(secondJoystick, SmartJoystick.Axis.LEFT_TRIGGER)
                )
        );
    }

    public void thirdJoystickButtons() {
        SmartJoystick usedJoystick = thirdJoystick;
    }

    public void fourthJoystickButtons() {
        SmartJoystick usedJoystick = fourthJoystick;
        usedJoystick.B.whileTrue(new CollectNoteFromFeeder());
        usedJoystick.R1.onTrue(new InstantCommand(() -> SmartDashboard.putNumber("Pivot angleee", Pivot.getInstance().getAngle().getDegrees())));
        usedJoystick.Y.whileTrue(new MovePivotByJoystick(usedJoystick, SmartJoystick.Axis.LEFT_Y));
    }

    public void initializeDefaultCommands() {
//		Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
        LED.getInstance().setDefaultCommand(new UpdateLEDStateDefaultCommand());
        FlyWheel.getInstance().setDefaultCommand(new FlyWheelDefaultCommand());
    }
}