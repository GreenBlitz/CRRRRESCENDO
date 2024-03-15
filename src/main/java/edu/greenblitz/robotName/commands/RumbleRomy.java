package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.units.Time;
import edu.wpi.first.wpilibj.Timer;

public class RumbleRomy extends GBCommand {

	private SmartJoystick joystick;

	private Timer timer;

	private boolean wasObjectIn;

	public RumbleRomy(SmartJoystick joystick){
		this.joystick = joystick;
		this.wasObjectIn = false;
		this.timer = new Timer();
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
			joystick.rumble(true,1);
		}
		else {
			joystick.rumble(true,0);
			timer.stop();
		}
	}

}
