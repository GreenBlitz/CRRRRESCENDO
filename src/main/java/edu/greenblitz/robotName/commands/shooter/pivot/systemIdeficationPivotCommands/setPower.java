package edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands;

import edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot.SystemIdenficationPivot;
import edu.greenblitz.robotName.utils.GBCommand;

public class setPower extends GBCommand {

	@Override
	public void execute() {
		SystemIdenficationPivot.getInstance().setPower(0.5);

	}

	@Override
	public void end(boolean interrupted) {
		//pivot.setPower(0);
	}
}
