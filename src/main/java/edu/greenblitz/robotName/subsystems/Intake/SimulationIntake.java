package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationIntake implements IIntake {
	private DCMotorSim motorSimulation;
	private double appliedOutput;
	
	public SimulationIntake() {
		motorSimulation = new DCMotorSim(
				DCMotor.getNEO(IntakeConstants.SimulationConstants.NUMBER_OF_MOTORS),
				IntakeConstants.SimulationConstants.GEAR_RATIO,
				IntakeConstants.SimulationConstants.MOMENT_OF_INERTIA
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
	public void updateInputs(IntakeInputsAutoLogged chassisIntakeInputs) {
		chassisIntakeInputs.appliedOutput = appliedOutput;
		chassisIntakeInputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
		chassisIntakeInputs.velocity = motorSimulation.getAngularVelocityRPM();
	}
}