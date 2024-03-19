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
		lifter.setPower(joystick.getAxisValue(axis));
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
		solenoid.openSolenoid();
	}
}