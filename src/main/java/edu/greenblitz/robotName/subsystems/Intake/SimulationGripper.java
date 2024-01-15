package edu.greenblitz.robotName.subsystems.Intake;

import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

import static edu.greenblitz.robotName.subsystems.Intake.GripperConstants.simulationGripper.*;

public class SimulationGripper implements IGripper {

    FlywheelSim gripperSimulation;
    private double appliedVoltage;

    public SimulationGripper() {
        gripperSimulation = new FlywheelSim(DCMotor.getNEO(NUM_MOTORS), GEARING, J_KG_METERS_SQUARED);
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        gripperSimulation.setInputVoltage(appliedVoltage);
    }

    @Override
    public void updateInputs(GripperInputs inputs) {
        gripperSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedVoltage;
        inputs.velocity = gripperSimulation.getAngularVelocityRPM();
    }
}
