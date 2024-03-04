package edu.greenblitz.robotName.subsystems.shooter.pivot;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;
import edu.wpi.first.math.interpolation.Interpolator;
import edu.wpi.first.math.interpolation.InverseInterpolator;

public class PivotInterpulationMap {

	public static InterpolatingTreeMap<Double, Double> DISTANCE_TO_ANGLE = new InterpolatingTreeMap<>(
			InverseInterpolator.forDouble(), Interpolator.forDouble()
	);

	static {
		DISTANCE_TO_ANGLE.put(0.9, PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE.getRadians());
		DISTANCE_TO_ANGLE.put(3.47, PivotConstants.PresetPositions.PODIUM.ANGLE.getRadians());
		DISTANCE_TO_ANGLE.put(5.39, PivotConstants.PresetPositions.RIGHT_STAGE.ANGLE.getRadians());
	}
}
