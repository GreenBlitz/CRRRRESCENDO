package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.Elbow.UnCollidingMoveElbow;
import edu.greenblitz.robotName.commands.Pivot.UnCollidingMovePivot;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

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
        secondJoystick.A.onTrue(new UnCollidingMoveElbow(ElbowConstants.BACKWARD_ANGLE_LIMIT));
        secondJoystick.B.onTrue(new UnCollidingMoveElbow(ElbowConstants.FORWARD_ANGLE_LIMIT));
        secondJoystick.POV_DOWN.onTrue(new UnCollidingMoveElbow(ElbowConstants.BRODER));
        secondJoystick.Y.onTrue(new UnCollidingMovePivot(PivotConstants.BACKWARD_ANGLE_LIMIT));
        secondJoystick.X.onTrue(new UnCollidingMovePivot(PivotConstants.FORWARD_ANGLE_LIMIT));
    }
}
