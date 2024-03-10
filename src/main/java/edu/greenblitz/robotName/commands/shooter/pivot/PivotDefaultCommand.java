package edu.greenblitz.robotName.commands.shooter.pivot;


import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class PivotDefaultCommand extends PivotCommand {

	public void initialize(){
		super.initialize();
		pivot.setCurrentAngle();
	}
	
	@Override
	public void execute() {
		if (ScoringModeSelector.isClimbMode()) {
			pivot.setCurrentAngle(PivotConstants.PresetPositions.SAFE.ANGLE);
		}
		pivot.standInPlace();
	}
}
