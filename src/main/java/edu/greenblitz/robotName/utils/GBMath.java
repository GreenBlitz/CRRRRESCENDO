package edu.greenblitz.robotName.utils;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class GBMath {
	/**
	 *
	 * modulo (2,6) -> 0
	 * modulo (2,3) -> 1
	 * modulo (-2,3) -> 1
	 * modulo (3,-2) -> -1 -> 1
	 *
	 * @param x the number to be modulu-ed
	 * @param y the modulo base
	 * @return the correct modulo result
	 */
	public static double absoluteModulo(double x, double y) {
		return ((x % y) + y) % y;
	}

	public static double reverseAngle(double angleInRadians){
		return angleInRadians == 0 ? 0 : angleInRadians > 0 ? -Math.PI * 2 + angleInRadians : angleInRadians + Math.PI * 2;
	}

	public static boolean isRangeContainsAnotherRange(double edge1Range1, double edge2Range1, double edge1Range2, double edge2Range2){
		return (edge1Range1<edge1Range2 && edge1Range1<edge2Range2 && edge2Range1>edge1Range2 && edge2Range1>edge2Range2)
				||
				(edge1Range1>edge1Range2 && edge1Range1>edge2Range2 && edge2Range1<edge1Range2 && edge2Range1<edge2Range2);
	}

}
