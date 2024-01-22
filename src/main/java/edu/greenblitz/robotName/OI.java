package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.RotateToAngleSupplier;
import edu.greenblitz.robotName.commands.swerve.RotateToPoint;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import org.littletonrobotics.junction.Logger;

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

    Translation2d a = new Translation2d(0,5);
    public void initButtons(){
        mainJoystick.R1.whileTrue(
                new MoveByJoysticks(MoveByJoysticks.DriveMode.NORMAL, new RotateToPoint(() -> a))
        );
        Logger.recordOutput("a",a);

    }
}
