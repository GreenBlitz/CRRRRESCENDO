package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.TestSparkmax;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;

public class MoveTestSparkmaxByPower extends GBCommand {
	
	private double power;
	public MoveTestSparkmaxByPower(double power){this.power = power;}
	
	
	@Override
	public void execute(){
		TestSparkmax.getInstance().moveMotor(power);
	}
	
	@Override
	public void end(boolean interrupted) {
		TestSparkmax.getInstance().moveMotor(0);
	}
}
