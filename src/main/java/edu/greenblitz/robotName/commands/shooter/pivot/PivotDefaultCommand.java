package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class PivotDefaultCommand extends PivotCommand {

	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public void execute() {
		pivot.moveToAngle(ShootingStateCalculations.getTargetShooterAngle());
	}
}
