package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.prototypes.runMotors;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class OI {
    private static OI instance;

    private SmartJoystick mainJoystick;
    private SmartJoystick secondJoystick;

    private OI() {
        mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
        secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
        initButt();
    }

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }

    public void initButt(){
        butt();
    }

    public void butt(){
        secondJoystick.B.whileTrue(new runMotors());
    }

    public SmartJoystick getMainJoystick() {
        return mainJoystick;
    }

    public SmartJoystick getSecondJoystick() {
        return secondJoystick;
    }
}
