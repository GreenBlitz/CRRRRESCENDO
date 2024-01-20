package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.Elbow.moveElbowToPosition;
import edu.greenblitz.robotName.commands.Pivot.movePivotToPosition;
import edu.greenblitz.robotName.commands.smartElbow;
import edu.greenblitz.robotName.commands.smartPivot;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
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
        secondJoystick.A.onTrue(new smartElbow(ElbowConstants.BACKWARD_ANGLE_LIMIT));
        secondJoystick.B.onTrue(new smartElbow(ElbowConstants.FORWARD_ANGLE_LIMIT));
        secondJoystick.POV_DOWN.onTrue(new smartElbow(ElbowConstants.BRODER));
        secondJoystick.Y.onTrue(new smartPivot(PivotConstants.BACKWARD_ANGLE_LIMIT));
        secondJoystick.X.onTrue(new smartPivot(PivotConstants.FORWARD_ANGLE_LIMIT));
    }
}
