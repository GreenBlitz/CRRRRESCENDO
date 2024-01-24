package edu.greenblitz.robotName;

import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.commands.shooter.ShootByPower;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

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

    public void initButtons() {
        // put buttons here
    }
    
    public void DefaultCommands(){
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(DRIVE_MODE));
        Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
    }

}
