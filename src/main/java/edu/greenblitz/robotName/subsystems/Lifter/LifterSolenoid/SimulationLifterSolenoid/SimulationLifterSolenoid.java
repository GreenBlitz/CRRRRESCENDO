package edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.SimulationLifterSolenoid;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.ILifterSolenoid;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterSolenoid.LifterSolenoidInputsAutoLogged;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationLifterSolenoid implements ILifterSolenoid {

	DCMotorSim simulationMotor;
	double appliedOutputs;
	public SimulationLifterSolenoid(){
		simulationMotor = new DCMotorSim(
				DCMotor.getCIM(SimulationLifterSolenoidConstants.MOTOR_NUM),
				1,
				0.005
		);
	}

	@Override
	public void openSolenoid() {
		setVoltage(LifterSolenoidConstants.POWER_TO_OPEN * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}

	@Override
	public void closeSolenoid() {
		setVoltage(LifterSolenoidConstants.POWER_TO_CLOSE * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}

	@Override
	public void holdSolenoid() {
		setVoltage(LifterSolenoidConstants.POWER_TO_HOLD * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}

	private void setVoltage(double voltage){
		appliedOutputs = voltage;
		simulationMotor.setInputVoltage(voltage);
	}
	@Override
	public void updateInputs(LifterSolenoidInputsAutoLogged inputs) {
		simulationMotor.update(RobotConstants.SimulationConstants.TIME_STEP);
		inputs.voltage = appliedOutputs;
		inputs.current = simulationMotor.getCurrentDrawAmps();
		inputs.isOpen = inputs.voltage > 0;
	}
}
