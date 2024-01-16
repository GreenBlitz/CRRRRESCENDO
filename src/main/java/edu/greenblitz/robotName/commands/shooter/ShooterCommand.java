package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.subsystems.shooter.Shooter;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class ShooterCommand extends GBCommand {

	protected Shooter shooter;

	public ShooterCommand(){
		shooter = Shooter.getInstance();
		require(shooter);
	}


}
