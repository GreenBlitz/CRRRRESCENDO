package edu.greenblitz.robotName.subsystems.shooter.funnel.simulationFunnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.funnel.IFunnel;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.greenblitz.robotName.subsystems.shooter.funnel.simulationFunnel.SimulationFunnelConstants.*;

public class SimulationFunnel implements IFunnel {
	
	private DCMotorSim motorSimulation;
	
	private double appliedOutput;
	
	private SendableChooser<Boolean> isObjectIn;
	
	public SimulationFunnel() {
		motorSimulation = new DCMotorSim(
				DCMotor.getNEO(NUMBER_OF_MOTORS),
				GEAR_RATIO,
				MOMENT_OF_INERTIA
		);
		isObjectIn = new SendableChooser<>();
		isObjectIn.setDefaultOption("False", false);
		isObjectIn.addOption("True", true);
		SmartDashboard.putData("Funnel Object", isObjectIn);
	}
	
	@Override
	public void setPower(double power) {
		setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}
	
	public void setVoltage(double voltage) {
		appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
		motorSimulation.setInputVoltage(appliedOutput);
	}
	
	@Override
	public void updateInputs(FunnelInputsAutoLogged inputs) {
		motorSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);
		
		inputs.appliedOutput = appliedOutput;
		inputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
		inputs.isObjectIn = isObjectIn.getSelected();
	}
}