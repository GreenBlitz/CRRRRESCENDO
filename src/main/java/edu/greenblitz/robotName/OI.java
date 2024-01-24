package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle.MoveWristToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.pivot.movePivotToAngle.MovePivotToAngle;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.subsystems.Arm.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Wrist;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
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
        DefaultCommands();
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

    public void DefaultCommands(){
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
        Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
        Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
    }
    
    public void initButtons(){
        secondJoystick.A.onTrue(new MovePivotToAngle(Units.degreesToRadians(45), true));
        secondJoystick.B.onTrue(new MovePivotToAngle(Units.degreesToRadians(315), true));
        secondJoystick.X.onTrue(new MoveWristToAngle(Units.degreesToRadians(45), true));
        secondJoystick.Y.onTrue(new MoveWristToAngle(Units.degreesToRadians(315), true));
    }
}
