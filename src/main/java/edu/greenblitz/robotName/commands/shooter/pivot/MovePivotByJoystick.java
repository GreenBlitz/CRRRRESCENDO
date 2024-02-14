package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MovePivotByJoystick extends PivotCommand {

    private SmartJoystick joystick;

    public MovePivotByJoystick(SmartJoystick joystick) {
        super();
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        double power = joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR;
        pivot.setPower(-power);
    }
}