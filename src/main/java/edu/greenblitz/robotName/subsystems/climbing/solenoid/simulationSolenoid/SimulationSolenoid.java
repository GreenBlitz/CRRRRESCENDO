package edu.greenblitz.robotName.subsystems.climbing.solenoid.simulationSolenoid;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.ISolenoid;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.SolenoidConstants;
import edu.greenblitz.robotName.subsystems.climbing.solenoid.SolenoidInputsAutoLogged;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationSolenoid implements ISolenoid {

	private DCMotorSim simulationSolenoidMotor;

	private double appliedSolenoidOutputs;


	public SimulationSolenoid(){
		simulationSolenoidMotor =
				 new DCMotorSim(
						DCMotor.getCIM(SimulationSolenoidConstants.NUMBER_OF_SOLENOID_MOTORS),
						SimulationSolenoidConstants.MOTOR_GEARING,
						 SimulationSolenoidConstants.MOTOR_JKG_METERS_SQUARED
				);
	}

	@Override
	public void openSolenoid() {
		setPowerToSolenoid(SolenoidConstants.POWER_TO_OPEN_SOLENOID);
	}

	@Override
	public void closeSolenoid() {
		setPowerToSolenoid(SolenoidConstants.POWER_TO_CLOSE_SOLENOID);
	}

	@Override
	public void holdSolenoid() {
		setPowerToSolenoid(SolenoidConstants.POWER_TO_HOLD_SOLENOID);
	}

	@Override
	public void setPowerToSolenoid(double power) {
		setVoltageSolenoid(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}

	public void setVoltageSolenoid(double voltage) {
		appliedSolenoidOutputs = voltage;
		simulationSolenoidMotor.setInputVoltage(voltage);
	}

	@Override
	public void updateInputs(SolenoidInputsAutoLogged inputs) {
		inputs.voltageSolenoid = appliedSolenoidOutputs;
		inputs.currentSolenoid = simulationSolenoidMotor.getCurrentDrawAmps();
		inputs.isOpenSolenoid = inputs.voltageSolenoid > 0;
	}

}
