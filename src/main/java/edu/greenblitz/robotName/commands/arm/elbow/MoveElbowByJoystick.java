package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MoveElbowByJoystick extends ElbowCommand {

    private SmartJoystick joystick;
    
    private SmartJoystick.Axis axis;

    private boolean isForward;

    public MoveElbowByJoystick(SmartJoystick joystick, SmartJoystick.Axis axis, boolean isForward) {
        super();
        this.joystick = joystick;
        this.axis = axis;
        this.isForward = isForward;
    }

    @Override
    public void execute() {
        double power = joystick.getAxisValue(axis);
        if (!isForward){
            power = -power;
        }
        elbow.setPower(power * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR);
    }
}