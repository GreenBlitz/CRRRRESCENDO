package edu.greenblitz.robotName.subsystems.shooter.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class PivotInputs {
	
	public double appliedOutput;
	
	public double outputCurrent;
	
	public Rotation2d position;
	
	public double velocity;
	
	public double acceleration;
	
	public Rotation2d absoluteEncoderPosition;
	
	public double temperature;
	
	public boolean hasHitForwardLimit;
	
	public boolean hasHitBackwardsLimit;

	public Rotation2d positionReference;
}