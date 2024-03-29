package edu.greenblitz.robotName.subsystems.arm.elbow.simulationElbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.arm.elbow.IElbow;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.BATTERY_VOLTAGE;
import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE;

public class SimulationElbow implements IElbow {

    private SingleJointedArmSim elbowSimulation;

    private double appliedVoltage;

    private PIDController controller;

    private ElbowInputsAutoLogged lastInputs;

    public SimulationElbow() {
        elbowSimulation = new SingleJointedArmSim(
                DCMotor.getFalcon500(SimulationElbowConstants.NUMBER_OF_MOTORS),
                SimulationElbowConstants.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        ElbowConstants.ARM_LENGTH,
                        ElbowConstants.ARM_MASS_KG
                ),
                ElbowConstants.ARM_LENGTH,
                ElbowConstants.BACKWARD_ANGLE_LIMIT.getRadians(),
                ElbowConstants.FORWARD_ANGLE_LIMIT.getRadians(),
                false,
                ElbowConstants.PresetPositions.STARTING.ANGLE.getRadians()
        );
        controller = SimulationElbowConstants.SIMULATION_PID.getPIDController();
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(
                voltage,
                -MAX_MOTOR_VOLTAGE,
                MAX_MOTOR_VOLTAGE
        );
        elbowSimulation.setInputVoltage(appliedVoltage);
    }

    @Override
    public void setIdleMode(NeutralModeValue idleMode) {
        Logger.recordOutput("Arm/Elbow", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void resetAngle(Rotation2d position) {
        Logger.recordOutput("Arm/Elbow", "tried to set the position to " + position);
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        controller.setSetpoint(targetAngle.getRadians());
        setVoltage(controller.calculate(lastInputs.position.getRadians()));
    }

    @Override
    public void standInPlace(Rotation2d targetAngle) {
        moveToAngle(targetAngle);
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        elbowSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = elbowSimulation.getCurrentDrawAmps();
        inputs.position = Rotation2d.fromRadians(elbowSimulation.getAngleRads());
        inputs.velocity = elbowSimulation.getVelocityRadPerSec();
        inputs.acceleration = (elbowSimulation.getVelocityRadPerSec() - inputs.velocity) / RobotConstants.SimulationConstants.TIME_STEP;
        inputs.hasReachedForwardLimit = elbowSimulation.hasHitLowerLimit();
        inputs.hasReachedBackwardLimit = elbowSimulation.hasHitLowerLimit();

        lastInputs = inputs;
    }
}