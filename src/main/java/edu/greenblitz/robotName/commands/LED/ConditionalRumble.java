package edu.greenblitz.robotName.commands.LED;

import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ConditionalRumble extends ConditionalCommand {
	public ConditionalRumble() {
		super(
				new Rumble(),
				new InstantCommand(),
				
				() -> LED.getInstance().rumble());
	}
	
}
