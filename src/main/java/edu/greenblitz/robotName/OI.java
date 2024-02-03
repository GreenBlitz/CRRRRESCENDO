package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.PanicMode;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringMode;
import edu.greenblitz.robotName.commands.setObjectInIsFunnel;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.swerve.MoveToAmp;
import edu.greenblitz.robotName.commands.swerve.MoveToSpeaker;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;

public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;
    private SmartJoystick thirdJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        thirdJoystick = new SmartJoystick(RobotConstants.Joystick.THIRD);
        initButtons();
        initializeDefaultCommands();
    }

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }

    public SmartJoystick getMainJoystick() {
        return mainJoystick;
    }

    public SmartJoystick getSecondJoystick() {
        return secondJoystick;
    }

    public void initButtons() {
        secondJoystick.START.onTrue(new PanicMode());
        secondJoystick.BACK.onTrue(new ToggleScoringMode());
        secondJoystick.A.onTrue(new MoveElbowAndWrist(ElbowConstants.PresetPositions.SCORE.ANGLE, WristConstants.PresetPositions.SCORE.ANGLE));
        secondJoystick.B.onTrue(new MoveElbowAndWristToSafe());
        secondJoystick.X.onTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.STARTING.ANGLE));
        secondJoystick.Y.onTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.TRANSFER.ANGLE));
        secondJoystick.POV_DOWN.onTrue(new CollectNoteToScoringMode());

        mainJoystick.A.whileTrue(new MoveToAmp());
        mainJoystick.B.whileTrue(new MoveToSpeaker());

        thirdJoystick.A.onTrue(new setObjectInIsFunnel(true));
        thirdJoystick.B.onTrue(new setObjectInIsFunnel(false));
    }

    public void initializeDefaultCommands(){
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
    }

  
}
