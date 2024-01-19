package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Rotation2d;

public class Constraints {

	private Rotation2d min;

	private Rotation2d max;

	public Constraints (Rotation2d angle1, Rotation2d angle2) {
		this.min = angle1.getRadians() >= angle2.getRadians()? angle2 : angle1;
		this.max = angle1.getRadians() >= angle2.getRadians()? angle1 : angle2;
	}

	public boolean checkIfConstraintCompletelyInsideAnother(Constraints other) {
		return other.max.getRadians() < max.getRadians() && other.min.getRadians() > min.getRadians();
	}
	public boolean checkIfConstraintAtLeastPartiallyInsideAnother(Constraints other) {
		return other.max.getRadians() < max.getRadians() || other.min.getRadians() > min.getRadians();
	}

}
