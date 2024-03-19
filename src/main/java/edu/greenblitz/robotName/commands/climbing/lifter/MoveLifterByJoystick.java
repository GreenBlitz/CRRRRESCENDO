package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.climber.solenoid.Solenoid;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MoveLifterByJoystick extends LifterCommand {

	private SmartJoystick joystick;
	private SmartJoystick.Axis axis;
	private Solenoid solenoid;

	public MoveLifterByJoystick(SmartJoystick joystick, SmartJoystick.Axis axis) {
		super();
		this.joystick = joystick;
		this.axis = axis;
		solenoid = Solenoid.getInstance();
	}

	@Override
	public void execute() {
<<<<<<< HEAD
		lifter.setPower((joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y) - LifterConstants.LIFTER_JOYSTICK_OFFSET) * (LifterConstants.LIFTER_JOYSTICK_NORMALIZER));
=======
		lifter.setPower(joystick.getAxisValue(axis));
>>>>>>> 3a63d4444e6f83a361b3da5fbdb756cc23079eff
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
		solenoid.openSolenoid();
	}
}