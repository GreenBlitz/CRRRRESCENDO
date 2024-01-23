package edu.greenblitz.robotName;


import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.commands.shooter.ShootByPower;
import edu.greenblitz.robotName.commands.swerve.RotateToAngle;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        initButtons();
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

    static GBSparkMax motor = new GBSparkMax(22, CANSparkMaxLowLevel.MotorType.kBrushless);
    public void initButtons(){
        mainJoystick.A.whileTrue(new ShootByPower(0.9));
        mainJoystick.X.whileTrue(new ShootByPower(0.5));
        mainJoystick.B.whileTrue(
                new GBCommand() {
                    @Override
                    public void execute() {
                        motor.set(0.2);
                    }
                    
                    @Override
                    public void end(boolean interrupted) {
                        motor.set(0);
                    }
                }
        );
        mainJoystick.Y.whileTrue(
                new GBCommand() {
                    @Override
                    public void execute() {
                        motor.set(-0.2);
                    }
                    
                    @Override
                    public void end(boolean interrupted) {
                        motor.set(0);
                    }
                }
        );
    }
}
