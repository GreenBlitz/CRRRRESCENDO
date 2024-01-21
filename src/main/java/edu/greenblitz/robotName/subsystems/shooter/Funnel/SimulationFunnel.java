package edu.greenblitz.robotName.subsystems.shooter.Funnel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumber;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimulationFunnel implements IFunnel{
	
	private DCMotorSim motorSimulation;
	private double appliedOutput;
	private SendableChooser<Boolean> isObjectIn;
	
	public SimulationFunnel() {
		motorSimulation = new DCMotorSim(
				DCMotor.getNEO(FunnelConstants.SimulationConstants.NUMBER_OF_MOTORS),
				FunnelConstants.SimulationConstants.GEAR_RATIO,
				FunnelConstants.SimulationConstants.MOMENT_OF_INERTIA
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
		funnelInputs.velocity = motorSimulation.getAngularVelocityRPM();
		funnelInputs.isObjectIn = isObjectIn.getSelected();
	}
}
