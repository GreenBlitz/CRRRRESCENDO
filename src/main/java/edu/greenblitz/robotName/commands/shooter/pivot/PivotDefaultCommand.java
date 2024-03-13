package edu.greenblitz.robotName.commands.shooter.pivot;


import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;

public class PivotDefaultCommand extends PivotCommand {
	@Override
	public void initialize() {
		pivot.setCurrentAngle();
	}

	@Override
	public void execute() {
//		pivot.moveToAngle(ShootingStateCalculations.getTargetShooterAngle(
//				ShootingPositionConstants.LEGAL_SHOOTING_ZONE
//		));
		pivot.standInPlace();
	}
}
