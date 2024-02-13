package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.PanicMode;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.roller.RunRollerByJoystick;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringMode;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.lifter.ExtendLifter;
import edu.greenblitz.robotName.commands.lifter.ReverseLifting;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingState;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

import static edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants.DRIVE_MODE;

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
//        initializeDefaultCommands();
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

//        mainJoystick.A.whileTrue(new GoToShootingState());
        mainJoystick.A.whileTrue(new ExtendLifter());
        mainJoystick.B.whileTrue(new ReverseLifting());
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