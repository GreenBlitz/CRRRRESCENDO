package edu.greenblitz.robotName.subsystems.intake.simulationIntake;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.intake.IIntake;
import edu.greenblitz.robotName.subsystems.intake.IntakeInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.intake.neoIntake.NeoIntakeConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimulationIntake implements IIntake {
	
	private DCMotorSim motorSimulation;
	
	private double appliedOutput;
	
	private SendableChooser<Boolean> beamBreaker;
	
	private PIDController pidController;

	public SimulationIntake() {
		motorSimulation = new DCMotorSim(
				DCMotor.getNEO(SimulationIntakeConstants.NUMBER_OF_MOTORS),
				SimulationIntakeConstants.GEAR_RATIO,
				SimulationIntakeConstants.MOMENT_OF_INERTIA
		);
		
		pidController = NeoIntakeConstants.PID_CONTROLLER.getPIDController();
		
		beamBreaker = new SendableChooser<>();
		beamBreaker.setDefaultOption("False", false);
		beamBreaker.addOption("True", true);
		SmartDashboard.putData("is object in intake entrance", beamBreaker);
	}
	
	@Override
	public void setPower(double power) {
		setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
	}
	
	@Override
	public void setVoltage(double voltage) {
		appliedOutput = MathUtil.clamp(
				voltage,
				-RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE,
				RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
		);
		motorSimulation.setInputVoltage(appliedOutput);
	}
	
	@Override
	public void setVelocity(double velocity) {
		pidController.setSetpoint(velocity);
		setVoltage(
				pidController.calculate(motorSimulation.getAngularVelocityRPM())
		);
	}
	
	@Override
	public void updateInputs(IntakeInputsAutoLogged intakeInputs) {
		intakeInputs.appliedOutput = appliedOutput;
		intakeInputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
		intakeInputs.velocity = motorSimulation.getAngularVelocityRPM();
		intakeInputs.beamBreakerValue = beamBreaker.getSelected();
	}
}