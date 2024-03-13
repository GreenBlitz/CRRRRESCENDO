package edu.greenblitz.robotName.subsystems.shooter.pivot;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;
import edu.wpi.first.math.util.Units;

public class PivotInterpolationMap {
	
	public static InterpolatingTreeMap<Double, Double> DISTANCE_TO_ANGLE = new InterpolatingTreeMap<>(
			InverseInterpolator.forDouble(), Interpolator.forDouble()
	);
	
	static {
		DISTANCE_TO_ANGLE.put(0.9, PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE.getRadians());
		DISTANCE_TO_ANGLE.put(2.0, Units.degreesToRadians(41));
		DISTANCE_TO_ANGLE.put(2.7, Units.degreesToRadians(34.2));
		DISTANCE_TO_ANGLE.put(3.47, PivotConstants.PresetPositions.PODIUM.ANGLE.getRadians());
		DISTANCE_TO_ANGLE.put(4.5, Units.degreesToRadians(30));
		DISTANCE_TO_ANGLE.put(5.9, PivotConstants.PresetPositions.RIGHT_STAGE.ANGLE.getRadians());
	}
}
