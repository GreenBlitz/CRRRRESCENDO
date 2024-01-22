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

}
