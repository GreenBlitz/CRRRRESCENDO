package edu.greenblitz.robotName.subsystems.ChassisIntake;

import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationChassisIntake implements IChassisIntake{
	private DCMotorSim motorSimulation;
	private double appliedOutput;
	
	public SimulationChassisIntake(){
		motorSimulation = new DCMotorSim(
				DCMotor.getNEO(ChassisIntakeConstants.SimulationConstants.NUMBER_OF_MOTORS),
				ChassisIntakeConstants.SimulationConstants.GEAR_RATIO,
				ChassisIntakeConstants.SimulationConstants.MOMENT_OF_INERTIA
		);
	}
	@Override
	public void setPower(double power) {
		setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}
	
	@Override
	public void setVoltage(double voltage) {
		appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
		motorSimulation.setInputVoltage(appliedOutput);
	}
	
	@Override
	public void updateInputs(ChassisIntakeInputsAutoLogged chassisIntakeInputs) {
		chassisIntakeInputs.appliedOutput = appliedOutput;
		chassisIntakeInputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
		chassisIntakeInputs.velocity = motorSimulation.getAngularVelocityRPM();
	}
}
