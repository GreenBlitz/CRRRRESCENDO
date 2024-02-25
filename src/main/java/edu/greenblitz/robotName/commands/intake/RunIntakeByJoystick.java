package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunIntakeByJoystick extends IntakeCommand {

    private SmartJoystick joystick;

    public RunIntakeByJoystick(SmartJoystick joystick) {
        super();
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        intake.setPower(joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X) * RobotConstants.General.SAFETY_POWER_CONVERSION_FACTOR);
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }
}