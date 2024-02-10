package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class RunFlyWheelByJoystick extends FlyWheelCommand{

    private SmartJoystick joystick;

    public RunFlyWheelByJoystick(SmartJoystick joystick){
        super();
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        flyWheel.setPower(joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR, joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR);
    }

    @Override
    public void end(boolean interrupted) {
        flyWheel.setPower(0, 0);
    }



}
