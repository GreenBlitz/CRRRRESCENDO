package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.simulationFlyWheel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class SimulationFlyWheel implements IFlyWheel {
    FlywheelSim flyWheelSimulation;
    private double appliedVoltage;
    PIDController pidController;

    public SimulationFlyWheel() {
        flyWheelSimulation = new FlywheelSim(
                DCMotor.getNEO(SimulationFlyWheelConstants.NUMBER_OF_MOTORS),
                SimulationFlyWheelConstants.GEARING,
                SimulationFlyWheelConstants.J_KG_METERS_SQUARED
        );
        pidController = SimulationFlyWheelConstants.SIMULATION_PID;
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(
                voltage,
                RobotConstants.SimulationConstants.MIN_MOTOR_VOLTAGE,
                RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
        );
        flyWheelSimulation.setInputVoltage(appliedVoltage);
    }

    @Override
    public void setVelocity(double velocity) {
        double power = velocity / SimulationFlyWheelConstants.POWER_TO_VELOCITY_FACTOR;
        setPower(power);
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        flyWheelSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.velocity = flyWheelSimulation.getAngularVelocityRPM();
        inputs.temperature = 0;
    }
}
