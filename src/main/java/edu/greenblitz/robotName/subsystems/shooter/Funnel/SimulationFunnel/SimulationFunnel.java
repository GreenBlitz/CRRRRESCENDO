package edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.FunnelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.IFunnel;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.shooter.Funnel.SimulationFunnel.SimulationFunnelConstants.*;

public class SimulationFunnel implements IFunnel {
	
	private DCMotorSim motorSimulation;

	private double appliedOutput;

	private SendableChooser<Boolean> isObjectIn;

	private PIDController pidController;

	private FunnelInputsAutoLogged lastInputs;


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

		pidController = SIMULATION_PID.getPIDController();

		lastInputs = new FunnelInputsAutoLogged();
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
	public void resetEncoder(Rotation2d position) {
		Logger.recordOutput("Funnel", "tried to reset3 the position to " + position);
	}

	@Override
	public void moveToPosition(Rotation2d position) {
		pidController.setSetpoint(position.getRotations());
		setVoltage(pidController.calculate(lastInputs.position.getRotations()));
	}

	@Override
	public void updateInputs(FunnelInputsAutoLogged inputs) {
		inputs.appliedOutput = appliedOutput;
		inputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
		inputs.temperature = 0;
		inputs.isObjectIn = isObjectIn.getSelected();
		inputs.position = Rotation2d.fromRotations(motorSimulation.getAngularPositionRotations());

		lastInputs = inputs;
	}
}
