package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class FunnelInputs {
	
	public double appliedOutput;

	public double outputCurrent;

	public double temperature;

	public boolean isObjectIn;

	public Rotation2d position;
	
}
