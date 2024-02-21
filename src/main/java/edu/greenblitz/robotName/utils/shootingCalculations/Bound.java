package edu.greenblitz.robotName.utils.shootingCalculations;

import edu.wpi.first.math.geometry.Translation2d;

public class Bound {
	
	private final Translation2d lowerLimit;
	
	private final Translation2d upperLimit;
	
	private final double lowerLimitX;
 
	private final double lowerLimitY;
	
    private final double upperLimitX;
	
    private final double upperLimitY;
	
	public Bound(Translation2d lowerLimit, Translation2d upperLimit) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		lowerLimitX = lowerLimit.getX();
		lowerLimitY = lowerLimit.getY();
		upperLimitX = upperLimit.getX();
		upperLimitY = upperLimit.getY();
	}
	
	public Translation2d getLowerLimit() {
		return lowerLimit;
	}
	
	public Translation2d getUpperLimit() {
		return upperLimit;
	}
	
	public Translation2d getClosestLimitToPosition(Translation2d position) {
		double upperLimitTargetDelta = upperLimitY - position.getY();
		double lowerLimitTargetDelta = position.getY() - lowerLimitY;
		return upperLimitTargetDelta > lowerLimitTargetDelta ? lowerLimit : upperLimit;
	}
	
	public boolean isInXBound(Translation2d position) {
		double positionX = position.getX();
		return lowerLimitX > upperLimitX ?
				positionX > upperLimitX && positionX < lowerLimitX :
				positionX > lowerLimitX && positionX < upperLimitX;
	}
	
	public boolean isInYBound(Translation2d position) {
		return position.getY() > lowerLimit.getY() && position.getY() < upperLimit.getY();
	}
	
	public boolean isPositionInBound(Translation2d position) {
		return isInXBound(position) && isInYBound(position);
	}
}