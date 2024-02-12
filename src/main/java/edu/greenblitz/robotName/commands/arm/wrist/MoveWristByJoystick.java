package edu.greenblitz.robotName.commands.arm.wrist;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MoveWristByJoystick extends WristCommand {

    private SmartJoystick joystick;

    public MoveWristByJoystick(SmartJoystick joystick) {
        super();
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        double power = joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR;
        wrist.setPower(power);
    }
}