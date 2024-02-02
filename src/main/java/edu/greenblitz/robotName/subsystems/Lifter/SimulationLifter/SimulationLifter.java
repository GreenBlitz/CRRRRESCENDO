package edu.greenblitz.robotName.subsystems.Lifter.SimulationLifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Lifter.ILifter;
import edu.greenblitz.robotName.subsystems.Lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.Lifter.LifterInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter {

    private SingleJointedArmSim lifterSimulation;
    private double appliedOutput;
    private ProfiledPIDController pidController;

    public SimulationLifter() {
        lifterSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(SimulationLifterConstants.NUMBER_OF_MOTORS),
                SimulationLifterConstants.GEAR_RATIO,
                SimulationLifterConstants.MOMENT_OF_INERTIA,
                LifterConstants.LENGTH_OF_LIFTER,
                LifterConstants.BACKWARD_LIMIT.getRadians(),
                LifterConstants.FORWARD_LIMIT.getRadians(),
                false,
                LifterConstants.STARTING_ANGLE.getRadians()
        );
        pidController = SimulationLifterConstants.SIMULATION_PID;
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(voltage, RobotConstants.SimulationConstants.MIN_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        lifterSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoder(Rotation2d position) {
        Logger.recordOutput("Lifter", "tried setting the position to " + position);
    }

    @Override
    public void stopMotor() {
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
    public void updateInputs(LifterInputsAutoLogged inputs) {
        lifterSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = lifterSimulation.getCurrentDrawAmps();
        inputs.position = lifterSimulation.getAngleRads();
        inputs.velocity = lifterSimulation.getVelocityRadPerSec();
    }
}
