package edu.greenblitz.robotName.commands.shooter.pivot;


import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;

public class PivotDefaultCommand extends PivotCommand {

	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public void execute() {
		if (ScoringModeSelector.isClimbMode()) {
			pivot.setCurrentAngle(PivotConstants.PresetPositions.SAFE.ANGLE);
		}
		pivot.moveToAngle(ShootingStateCalculations.getTargetShooterAngle());
	}
}
