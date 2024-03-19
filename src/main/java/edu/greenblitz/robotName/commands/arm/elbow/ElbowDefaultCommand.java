package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.ScoringModeSelector;

public class ElbowDefaultCommand extends ElbowCommand {
	
	@Override
	public void initialize() {
		elbow.setCurrentAngle(ElbowConstants.PresetPositions.SAFE.ANGLE);
	}
	
	@Override
	public void execute() {
		if (ScoringModeSelector.isClimbMode()) {
			elbow.setPower(0);
			elbow.setCurrentAngle();
		}
		else if (!SwerveChassis.getInstance().isRobotNearBoundsOfField()) {
			elbow.standInPlace();
		}
	}
}