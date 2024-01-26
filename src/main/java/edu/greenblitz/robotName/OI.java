package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.arm.MoveArmBy2Angles;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooterArmCoordination.speaker.PickUpSpeaker;
import edu.greenblitz.robotName.commands.shooterArmCoordination.transfer.ShooterToArmTransfer;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.arm.Wrist;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

import static edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants.DRIVE_MODE;

public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
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
//        secondJoystick.A.onTrue(new PickUpSpeaker());
//        secondJoystick.B.onTrue(new ShooterToArmTransfer(true));
//        secondJoystick.X.onTrue(new MovePivotToAngle(Units.degreesToRadians(165)));
//        secondJoystick.Y.onTrue(new MoveArmBy2Angles(Rotation2d.fromDegrees(215), Rotation2d.fromDegrees(20)));
        secondJoystick.A.onTrue(new MoveElbowToAngle(ElbowConstants.TRANSFER_ANGLE));
        secondJoystick.B.onTrue(new MoveElbowToAngle(ElbowConstants.BACKWARD_ANGLE_LIMIT));
        secondJoystick.X.onTrue(new MoveWristToAngle(WristConstants.TRANSFER_ANGLE));
        secondJoystick.Y.onTrue(new MoveWristToAngle(Rotation2d.fromDegrees(0)));
        secondJoystick.L1.onTrue(new MovePivotToAngle(PivotConstants.TRANSFER_ANGLE));
        secondJoystick.L3.onTrue(new MovePivotToAngle(PivotConstants.PICK_UP_ANGLE));

    }
    
    public void initializeDefaultCommands(){
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
    }

  
}
