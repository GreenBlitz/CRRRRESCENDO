package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj.Timer;

public class RumbleRomy extends GBCommand {

	private SmartJoystick joystick0;

	private SmartJoystick joystick1;

	private Timer timer;

	private boolean wasObjectIn;

	private boolean isJoystick1Connected;

	public RumbleRomy(SmartJoystick joystick0){
		this.joystick0 = joystick0;
		this.joystick1 = null;
		this.wasObjectIn = false;
		this.timer = new Timer();
	}

	public RumbleRomy(SmartJoystick joystick0, SmartJoystick joystick1){
		this.joystick0 = joystick0;
		this.joystick1 = joystick1;
		this.wasObjectIn = false;
		this.timer = new Timer();
	}

	@Override
	public void initialize() {
		isJoystick1Connected = joystick1 != null;
	}

	@Override
	public void execute() {
		if (!Intake.getInstance().isObjectIn()){
			wasObjectIn = false;
		}
		if (Intake.getInstance().isObjectIn() && !wasObjectIn){
			wasObjectIn = true;
			timer.restart();
		}
		if (wasObjectIn && timer.get() < 1.5) {
			joystick0.rumble(true,1);
			if (isJoystick1Connected) {
				joystick1.rumble(true, 1);
			}
		}
		else {
			joystick0.rumble(true,0);
			if (isJoystick1Connected) {
				joystick1.rumble(true, 0);
			}
			timer.stop();
		}
	}

}
