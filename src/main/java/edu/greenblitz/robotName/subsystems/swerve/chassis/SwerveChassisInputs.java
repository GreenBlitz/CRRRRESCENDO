package edu.greenblitz.robotName.subsystems.swerve.chassis;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class SwerveChassisInputs {
 
	public boolean isVisionEnabled;
	
    public double numberOfDetectedAprilTag;
	
    public double xAxisSpeed;
	
    public double yAxisSpeed;
	
    public double omegaRadiansPerSecond;
	
    public double accelerationX;
	
    public double accelerationY;
}