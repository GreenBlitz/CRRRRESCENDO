package edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.SimulationRoller;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.IRoller;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.RollerUtils.RollerInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationRoller implements IRoller {
	
	private DCMotorSim rollerSimulation;

	private double appliedOutput;
	
	public SimulationRoller() {
		rollerSimulation = new DCMotorSim(
				DCMotor.getBag(SimulationRollerConstants.NUMBER_OF_MOTORS),
				SimulationRollerConstants.GEAR_RATIO,
				SimulationRollerConstants.MOMENT_OF_INERTIA
		);
	}
	
	@Override
	public void setPower(double power) {
		double voltage = power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE;
		appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
		rollerSimulation.setInputVoltage(appliedOutput);
	}
	
	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputs) {
		rollerInputs.appliedOutput = appliedOutput;
		rollerInputs.outputCurrent = rollerSimulation.getCurrentDrawAmps();
	}
}
