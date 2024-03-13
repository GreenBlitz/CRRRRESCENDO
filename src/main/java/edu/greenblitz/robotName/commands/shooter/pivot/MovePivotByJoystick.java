package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MovePivotByJoystick extends PivotCommand {

    private SmartJoystick joystick;

    private SmartJoystick.Axis axis;

    public MovePivotByJoystick(SmartJoystick joystick, SmartJoystick.Axis axis) {
        super();
        this.joystick = joystick;
        this.axis = axis;
    }

    @Override
    public void execute() {
        double power = joystick.getAxisValue(axis) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR * 0.4;
        pivot.setPower(power);
    }

}