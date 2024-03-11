package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.solenoid.Solenoid;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

public class MoveLifterByJoystick extends LifterCommand {

	private SmartJoystick joystick;
	Solenoid solenoid;

	public MoveLifterByJoystick(SmartJoystick joystick) {
		super();
		this.joystick = joystick;
		solenoid = Solenoid.getInstance();
	}

	@Override
	public void execute() {
		lifter.setPower((joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y) - 0.1) * (4 / 9.0));
	}

	@Override
	public void end(boolean interrupted) {
		lifter.stop();
		solenoid.openSolenoid();
	}
}