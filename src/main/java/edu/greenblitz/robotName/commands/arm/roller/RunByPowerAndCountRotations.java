package edu.greenblitz.robotName.commands.arm.roller;

import edu.wpi.first.math.geometry.Rotation2d;

public class RunByPowerAndCountRotations extends RollerCommand {

	private Rotation2d rotationCounter;

	private boolean isClockWise;

	public RunByPowerAndCountRotations(Rotation2d rotationCounter, boolean isClockWise) {
		super();
		this.rotationCounter = rotationCounter;
		this.isClockWise = isClockWise;
	}

	@Override
	public void initialize() {
		roller.resetEncoder(new Rotation2d(0));
	}

	@Override
	public void execute() {
		if (isClockWise) {
			roller.rollClockwise();
		} else {
			roller.rollCounterClockwise();
		}
	}

	@Override
	public boolean isFinished() {
		return Math.abs(roller.getAngle().getRotations()) > Math.abs(rotationCounter.getRotations());
	}
}
