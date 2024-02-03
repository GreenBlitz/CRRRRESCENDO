package edu.greenblitz.robotName.subsystems.arm.roller.SimulationRoller;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimulationRoller implements IRoller {
	
	private DCMotorSim rollerSimulation;

	private double appliedOutput;

	private SendableChooser<Boolean> isObjectInArm;

	public SimulationRoller() {
		rollerSimulation = new DCMotorSim(
				DCMotor.getBag(SimulationRollerConstants.NUMBER_OF_MOTORS),
				SimulationRollerConstants.GEAR_RATIO,
				SimulationRollerConstants.MOMENT_OF_INERTIA
		);

		isObjectInArm = new SendableChooser<>();
		isObjectInArm.setDefaultOption("False", false);
		isObjectInArm.addOption("True", true);
		SmartDashboard.putData("Is Object In Arm?", isObjectInArm);
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
		rollerInputs.isObjectInArm = isObjectInArm.getSelected();
	}
}
