package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.LED.UpdateLEDStateDefaultCommand;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRoller;
import edu.greenblitz.robotName.commands.arm.roller.RollerDefaultCommand;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollCounterClockwise;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.getNoteToSystem.*;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteFromFeeder;
import edu.greenblitz.robotName.commands.intake.*;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ForwardRunFunnelUntilObjectIn;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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
        mainJoystick.R1.whileTrue(new CollectNoteToScoringModeForJoystick());
        mainJoystick.L1.whileTrue(new CollectNoteFromFeeder());
        mainJoystick.Y.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetPoseByVision()));

        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
    }

    public void shchoriButtons() {
        //ScoringMode
        secondJoystick.START.onTrue(new ToggleScoringMode());

        //Arm
        secondJoystick.BACK.onTrue(new InstantCommand(() -> Roller.getInstance().setObjectOut()));
        secondJoystick.A.onTrue(new ReleaseNoteFromRoller());
        secondJoystick.B.whileTrue(new MoveElbowAndWrist(
                ElbowConstants.PresetPositions.SCORE,
                WristConstants.PresetPositions.SCORE
        ));
        secondJoystick.X.whileTrue(new MoveElbowAndWrist(
                ElbowConstants.PresetPositions.SAFE,
                WristConstants.PresetPositions.SAFE
        ));

        //FlyWheel Run
        secondJoystick.L1.whileTrue(new RunFlyWheelByVelocityUntilInterrupted(100, secondJoystick));

        //Pivot Poses
        secondJoystick.POV_UP.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.RIGHT_STAGE.ANGLE));
        secondJoystick.POV_LEFT.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.PODIUM.ANGLE));
        secondJoystick.POV_DOWN.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE));
        secondJoystick.R1.whileTrue(new MovePivotByJoystick(secondJoystick, SmartJoystick.Axis.LEFT_Y));

        //Funnel
        secondJoystick.R1.whileTrue(new RunFunnelByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_Y));

        //Intake
        secondJoystick.POV_RIGHT.whileTrue(new RunIntakeByPower(-0.4));

        //Fully collect
        secondJoystick.Y.whileTrue(new CollectNoteToScoringMode());
    }

    public void thirdJoystickButtons() {
        SmartJoystick usedJoystick = thirdJoystick;
        usedJoystick.R1.whileTrue(new NoteToShooterWithoutArm());
        usedJoystick.L1.whileTrue(new RunFunnelByJoystick(usedJoystick, SmartJoystick.Axis.RIGHT_Y));

    }

    public void fourthJoystickButtons() {
        SmartJoystick usedJoystick = fourthJoystick;
        usedJoystick.B.whileTrue(new CollectNoteFromFeeder());
        usedJoystick.R1.onTrue(new InstantCommand(() -> SmartDashboard.putNumber("Pivot angleee", Pivot.getInstance().getAngle().getDegrees())));
        usedJoystick.Y.whileTrue(new MovePivotByJoystick(usedJoystick, SmartJoystick.Axis.LEFT_Y));
    }

    public void initializeDefaultCommands() {
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
        Roller.getInstance().setDefaultCommand(new RollerDefaultCommand());
        LED.getInstance().setDefaultCommand(new UpdateLEDStateDefaultCommand());
    }
}

