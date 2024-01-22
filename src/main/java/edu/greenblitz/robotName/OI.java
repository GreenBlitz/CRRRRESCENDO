package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.swerve.adadad;
import edu.greenblitz.robotName.commands.swerve.susstysre;
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
        secondJoystick.A.whileTrue(new adadad(Units.degreesToRadians(310)));
        secondJoystick.B.whileTrue(new adadad(Units.degreesToRadians(140)));
        secondJoystick.X.whileTrue(new susstysre(Units.degreesToRadians(304)));
        secondJoystick.Y.whileTrue(new susstysre(Units.degreesToRadians(50)));
    }
}
