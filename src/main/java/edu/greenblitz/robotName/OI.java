package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.Elbow.moveElbowToPosition;
import edu.greenblitz.robotName.commands.Pivot.movePivotToPosition;
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
        secondJoystick.A.whileTrue(new moveElbowToPosition(Units.degreesToRadians(310)));
        secondJoystick.B.whileTrue(new moveElbowToPosition(Units.degreesToRadians(135)));
        secondJoystick.Y.whileTrue(new movePivotToPosition(Units.degreesToRadians(0)));
        secondJoystick.X.whileTrue(new movePivotToPosition(Units.degreesToRadians(181)));
    }
}
