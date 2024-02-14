package edu.greenblitz.robotName;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import edu.greenblitz.robotName.commands.PanicMode;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteToMiddleOfRoller;
import edu.greenblitz.robotName.commands.arm.roller.RunByPower.RunRollerCounterClockwiseUntilNoteIsInside;
import edu.greenblitz.robotName.commands.arm.roller.RunRollerByJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringMode;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingState;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingStateAndShoot;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;

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

    public static void init(){
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
        secondJoystick.START.whileTrue(new PanicMode());
        secondJoystick.BACK.whileTrue(new ToggleScoringMode());
        secondJoystick.A.whileTrue(new MoveElbowAndWrist(ElbowConstants.PresetPositions.SCORE.ANGLE, WristConstants.PresetPositions.SCORE.ANGLE));
        secondJoystick.B.whileTrue(new MoveElbowAndWristToSafe());
        secondJoystick.X.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE));
        secondJoystick.Y.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.TRANSFER.ANGLE));
        secondJoystick.POV_DOWN.whileTrue(new CollectNoteToScoringMode());

        mainJoystick.A.whileTrue(new GoToShootingState());
    }

    public void romyButtons() {
        mainJoystick.R1.whileTrue(new RunIntakeByJoystick(mainJoystick)); // Gripper
        mainJoystick.L1.whileTrue(new GoToShootingState()); // Move2Pos
//        mainJoystick.R2.whileTrue(new SwerveRequest.); // robot centric
    }
    public void schoriButtons() {
//        secondJoystick.R1.whileTrue(new ScoreToSpeaker()); //doesn't exist yet
//        secondJoystick.L1.whileTrue(new ScoreToAmp()); //does Schori want this function?
//        secondJoystick.A.whileTrue(new Eject()); //doesn't exist yet
        secondJoystick.B.whileTrue(new PanicMode());
//        secondJoystick.POV_UP.whileTrue(new Climb()); //doesn't exist yet
    }

    public void thirdJoystickButtons(){
        SmartJoystick usedJoystick = thirdJoystick;
        usedJoystick.A.whileTrue(new RunRollerByJoystick(usedJoystick));
        usedJoystick.A.whileTrue(new RunIntakeByJoystick(usedJoystick));
        usedJoystick.B.whileTrue(new RunFunnelByJoystick(usedJoystick));
        usedJoystick.B.whileTrue(new RunFlyWheelByJoystick(usedJoystick));
    }

    public void initializeDefaultCommands() {
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
    }


}
