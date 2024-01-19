package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.Arm.moveArmToPosition;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.util.Units;

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

    public void initButtons(){
        secondJoystick.A.whileTrue(new moveArmToPosition(Units.degreesToRadians(300),Units.degreesToRadians(130)));
        secondJoystick.B.whileTrue(new moveArmToPosition(Units.degreesToRadians(135),Units.degreesToRadians(240)));
        secondJoystick.Y.whileTrue(new moveArmToPosition(Units.degreesToRadians(0),Units.degreesToRadians(0)));
    }
}
