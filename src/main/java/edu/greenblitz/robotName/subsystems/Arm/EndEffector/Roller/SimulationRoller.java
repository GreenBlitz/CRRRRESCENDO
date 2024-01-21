package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Roller;

import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class SimulationRoller implements IRoller{
	
	private FlywheelSim motorSimulation;
	private double appliedOutput;
	
	public SimulationRoller() {
		motorSimulation = new FlywheelSim(
				DCMotor.getNEO(RollerConstants.SimulationConstants.NUMBER_OF_MOTORS),
				RollerConstants.SimulationConstants.GEAR_RATIO,
				RollerConstants.SimulationConstants.MOMENT_OF_INERTIA
		);
	}
	
	@Override
	public void setPower(double power) {
		double voltage = power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE;
		appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
		motorSimulation.setInputVoltage(appliedOutput);
	}
	
	@Override
	public void updateInputs(RollerInputsAutoLogged rollerInputsAutoLogged) {
		rollerInputsAutoLogged.appliedOutput = appliedOutput;
		rollerInputsAutoLogged.outputCurrent = motorSimulation.getCurrentDrawAmps();
		rollerInputsAutoLogged.velocity = motorSimulation.getAngularVelocityRPM();
	}
}
