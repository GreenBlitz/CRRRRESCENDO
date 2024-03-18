package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class RunFunnelByJoystick extends FunnelCommand {

    private SmartJoystick joystick;

    private SmartJoystick.Axis axis;

    public RunFunnelByJoystick(SmartJoystick joystick, SmartJoystick.Axis axis) {
        super();
        this.joystick = joystick;
        this.axis = axis;
    }

    @Override
    public void execute() {
        funnel.setPower(joystick.getAxisValue(axis));
    }

}