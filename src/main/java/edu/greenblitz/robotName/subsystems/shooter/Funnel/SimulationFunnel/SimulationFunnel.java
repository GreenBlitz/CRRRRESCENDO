package edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.IFunnel;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel.SimulationFunnelConstants.*;

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
		SmartDashboard.putData(isObjectIn);
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
	public void updateInputs(FunnelInputsAutoLogged funnelInputs) {
		funnelInputs.appliedOutput = appliedOutput;
		funnelInputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
		funnelInputs.temperature = 0;
		funnelInputs.isObjectIn = isObjectIn.getSelected();
	}
}
