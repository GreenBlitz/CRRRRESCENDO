package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class FlyWheelDefaultCommand extends RunFlyWheelByVelocity {

	public FlyWheelDefaultCommand() {
		super(getSpeed().getAsDouble());
	}

	public static DoubleSupplier getSpeed(){
		if (DriverStation.isAutonomous()){
			return () -> FlyWheelConstants.SHOOTING_VELOCITY;
		}
		else {
			return () -> FlyWheelConstants.DEFAULT_VELOCITY;
		}
	}
}
