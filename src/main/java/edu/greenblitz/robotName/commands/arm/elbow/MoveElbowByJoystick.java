package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MoveElbowByJoystick extends ElbowCommand {

    private SmartJoystick joystick;

    public MoveElbowByJoystick(SmartJoystick joystick){
        super();
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        double power = joystick.getAxisValue(SmartJoystick.Axis.RIGHT_Y) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR;
        elbow.setPower(power);
    }
}
