package edu.greenblitz.robotName.commands.shooter.pivot;


import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.wpi.first.math.geometry.Rotation2d;

public class PivotDefaultCommand extends PivotCommand {
	
//	@Override
//	public void execute() {
//		pivot.moveToAngle(() -> ShootingStateCalculations.getTargetShooterAngle(ShootingPositionConstants.LEGAL_SHOOTING_ZONE));
//	}
	
	public void initialize(){
		super.initialize();
		pivot.setCurrentAngle();
	}
	
	@Override
	public void execute() {
		pivot.standInPlace();
	}
}
