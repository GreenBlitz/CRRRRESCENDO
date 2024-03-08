package edu.greenblitz.robotName.subsystems.lifter.simulationLifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.lifter.ILifter;
import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.lifter.LifterInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter {

    private SingleJointedArmSim lifterSimulation;
    private double appliedOutput;
    private ProfiledPIDController pidController;
    DCMotorSim simulationSolenoidMotor;
    double appliedSolenoidOutputs;

    public SimulationLifter() {
        lifterSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(SimulationLifterConstants.NUMBER_OF_MOTORS),
                SimulationLifterConstants.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        LifterConstants.LENGTH_OF_LIFTER,
                        LifterConstants.LIFTER_MASS_KG
                ),
                LifterConstants.LENGTH_OF_LIFTER,
                LifterConstants.BACKWARD_LIMIT.getRadians(),
                LifterConstants.FORWARD_LIMIT.getRadians(),
                false,
                LifterConstants.STARTING_ANGLE.getRadians()
        );
        pidController = SimulationLifterConstants.SIMULATION_PID;

        simulationSolenoidMotor = new DCMotorSim(
                DCMotor.getCIM(SimulationLifterConstants.NUMBER_OF_SOLENOID_MOTORS),
                SimulationLifterConstants.MOTOR_GEARING,
                SimulationLifterConstants.MOTOR_JKG_METERS_SQUARED
        );
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(
                voltage,
                RobotConstants.SimulationConstants.MIN_MOTOR_VOLTAGE,
                RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
        );
        lifterSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoder(Rotation2d position) {
        Logger.recordOutput("Lifter", "tried setting the position to " + position);
    }

    @Override
    public void stop() {
        this.setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        Logger.recordOutput("Lifter", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void goToPosition(Rotation2d position) {
        setVoltage(pidController.calculate(lifterSimulation.getAngleRads(), position.getRadians()));
    }

    @Override
    public void openSolenoid() {
        setPowerToSolenoid(LifterConstants.POWER_TO_OPEN_SOLENOID);
    }

    @Override
    public void closeSolenoid() {
        setPowerToSolenoid(LifterConstants.POWER_TO_CLOSE_SOLENOID);
    }

    @Override
    public void holdSolenoid() {
        setPowerToSolenoid(LifterConstants.POWER_TO_HOLD_SOLENOID);
    }

    @Override
    public void setPowerToSolenoid(double power) {
        setVoltageSolenoid(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    public void setVoltageSolenoid(double voltage) {
        appliedSolenoidOutputs = voltage;
        simulationSolenoidMotor.setInputVoltage(voltage);
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        lifterSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = lifterSimulation.getCurrentDrawAmps();
        inputs.position = Rotation2d.fromRadians(lifterSimulation.getAngleRads());
        inputs.velocity = lifterSimulation.getVelocityRadPerSec();

        inputs.voltageSolenoid = appliedSolenoidOutputs;
        inputs.currentSolenoid = simulationSolenoidMotor.getCurrentDrawAmps();
        inputs.isOpenSolenoid = inputs.voltageSolenoid > 0;
    }
}