package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class RunRollerByJoystick extends RollerCommand{

    private SmartJoystick joystick;

    public RunRollerByJoystick(SmartJoystick joystick){
        super();
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        roller.setPower(joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR);
    }

    @Override
    public void end(boolean interrupted) {
        roller.setPower(0);
    }
}
