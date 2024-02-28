package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MoveElbowByJoystick extends ElbowCommand {

    private SmartJoystick joystick;
    
    private SmartJoystick.Axis axis;

    public MoveElbowByJoystick(SmartJoystick joystick, SmartJoystick.Axis axis) {
        super();
        this.joystick = joystick;
        this.axis = axis;
    }

    @Override
    public void execute() {
        double power = joystick.getAxisValue(axis);
        elbow.setPower(power);
    }
}