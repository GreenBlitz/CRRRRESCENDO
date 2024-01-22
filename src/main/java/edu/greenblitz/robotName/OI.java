package edu.greenblitz.robotName;


import edu.greenblitz.robotName.commands.arm.Elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.Elbow.MoveElbowToAngle.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.Wrist.MoveWristToAngle.MoveWristToAngle;
import edu.greenblitz.robotName.commands.arm.Wrist.WristDefaultCommand;
import edu.greenblitz.robotName.subsystems.Arm.Elbow;
import edu.greenblitz.robotName.subsystems.Arm.Wrist;
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
        //do buttons here
    }
}
