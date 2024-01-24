package edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.simulationFlyWheel;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.IFlyWheel;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class SimulationFlyWheel implements IFlyWheel {
    private FlywheelSim rightMotorFlywheel;
    private FlywheelSim leftMotorFlywheel;
    private double rightMotorApplied, leftMotorApplied;
    private PIDController rightMotorPIDController;
    private PIDController leftMotorPIDController;

    public SimulationFlyWheel() {
        rightMotorFlywheel = new FlywheelSim(
                DCMotor.getNEO(SimulationFlyWheelConstants.RightMotor.NUMBER_OF_MOTORS),
                SimulationFlyWheelConstants.RightMotor.GEARING,
                SimulationFlyWheelConstants.RightMotor.J_KG_METERS_SQUARED
        );
        rightMotorPIDController = SimulationFlyWheelConstants.RightMotor.SIMULATION_PID;

        leftMotorFlywheel = new FlywheelSim(
                DCMotor.getNEO(SimulationFlyWheelConstants.LeftMotor.NUMBER_OF_MOTORS),
                SimulationFlyWheelConstants.LeftMotor.GEARING,
                SimulationFlyWheelConstants.LeftMotor.J_KG_METERS_SQUARED
        );
        leftMotorPIDController = SimulationFlyWheelConstants.LeftMotor.SIMULATION_PID;
    }

    @Override
    public void setPower(double rightPower, double leftPower) {
        setVoltage(
                rightPower * RobotConstants.SimulationConstants.BATTERY_VOLTAGE,
                leftPower  * RobotConstants.SimulationConstants.BATTERY_VOLTAGE
        );

    }

    @Override
    public void setVoltage(double rightVoltage, double leftVoltage) {
        rightMotorApplied = MathUtil.clamp(
                rightVoltage,
                -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE,
                RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
        );
        leftMotorApplied = MathUtil.clamp(
                leftVoltage,
                -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE,
                RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
        );
    }

    @Override
    public void setVelocity(double rightVelocity, double leftVelocity) {
        rightMotorPIDController.setSetpoint(rightVelocity);
        leftMotorPIDController.setSetpoint(leftVelocity);

        setVoltage(
                rightMotorPIDController.calculate(rightMotorFlywheel.getAngularVelocityRPM()),
                leftMotorPIDController.calculate(leftMotorFlywheel.getAngularVelocityRPM())
        );

    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        rightMotorFlywheel.update(RobotConstants.SimulationConstants.TIME_STEP);
        leftMotorFlywheel.update(RobotConstants.SimulationConstants.TIME_STEP);
    }
}
