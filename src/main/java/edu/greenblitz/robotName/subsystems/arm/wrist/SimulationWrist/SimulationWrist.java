package edu.greenblitz.robotName.subsystems.arm.wrist.SimulationWrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.IWrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.BATTERY_VOLTAGE;

public class SimulationWrist implements IWrist {

    private SingleJointedArmSim wristSimulation;

    private double appliedVoltage;

    private PIDController controller;

    private WristInputsAutoLogged lastInputs;

    public SimulationWrist() {
        wristSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(SimulationWristConstants.NUMBER_OF_MOTORS),
                SimulationWristConstants.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        WristConstants.LENGTH_OF_ENDEFFECTOR,
                        WristConstants.WRIST_MASS_KG
                ),
                WristConstants.LENGTH_OF_ENDEFFECTOR,
                WristConstants.BACKWARD_ANGLE_LIMIT.getRadians(),
                WristConstants.FORWARD_ANGLE_LIMIT.getRadians(),
                false,
                WristConstants.PresetPositions.STARTING.ANGLE.getRadians()
        );

        controller = SimulationWristConstants.SIMULATION_PID.getPIDController();
    }


    @Override
    public void setPower(double power) {
        setVoltage(power * BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        wristSimulation.setInputVoltage(appliedVoltage);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        Logger.recordOutput("Arm/Wrist", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void resetAngle(Rotation2d position) {
        Logger.recordOutput("Arm/Wrist", "tried to reset the position to " + position);
    }

    @Override
    public void resetEncoder() {
        Logger.recordOutput("Arm/Wrist", "tried to reset the encoder");
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        controller.setSetpoint(targetAngle.getRadians());
        setVoltage(controller.calculate(lastInputs.position));
    }

    @Override
    public void standInPlace() {
        setVoltage(SimulationWristConstants.SIMULATION_FEED_FORWARD.calculate(lastInputs.position,0));
    }

    @Override
    public void updateInputs(WristInputsAutoLogged inputs) {
        wristSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = wristSimulation.getCurrentDrawAmps();
        inputs.position = wristSimulation.getAngleRads();
        inputs.velocity = wristSimulation.getVelocityRadPerSec();
        inputs.absoluteEncoderPosition = wristSimulation.getAngleRads();
        inputs.temperature = 0;
        inputs.hasReachedForwardLimit = wristSimulation.hasHitLowerLimit();
        inputs.hasReachedBackwardLimit = wristSimulation.hasHitLowerLimit();

        lastInputs = inputs;
    }
}
