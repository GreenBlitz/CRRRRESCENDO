package edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.IPivot;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.BATTERY_VOLTAGE;
import static edu.greenblitz.robotName.subsystems.shooter.Pivot.SimulationPivot.SimulationPivotConstants.*;


public class SimulationPivot implements IPivot {

    private SingleJointedArmSim pivotSimulation;

    private double appliedVoltage;

    private PIDController controller;


    public SimulationPivot() {
        pivotSimulation = new SingleJointedArmSim(
                DCMotor.getFalcon500(NUMBER_OF_MOTORS),
                GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        PivotConstants.LENGTH_OF_SHOOTER,
                        PivotConstants.SHOOTER_MASS_KG
                ),
                PivotConstants.LENGTH_OF_SHOOTER,
                PivotConstants.BACKWARD_ANGLE_LIMIT.getRadians(),
                PivotConstants.FORWARD_ANGLE_LIMIT.getRadians(),
                false,
                PivotConstants.PresetPositions.STARTING.ANGLE.getRadians()
        );
        controller = SIMULATION_PID.getPIDController();
    }


    @Override
    public void setPower(double power) {
        setVoltage(power * BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        pivotSimulation.setInputVoltage(appliedVoltage);
    }

    @Override
    public void setIdleMode(NeutralModeValue idleMode) {
        Logger.recordOutput("Arm/Pivot", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void resetAngle(Rotation2d position) {
        Logger.recordOutput("Arm/Pivot", "tried to reset the position to " + position);
    }

    @Override
    public void moveToAngle(Rotation2d targetAngle) {
        controller.setSetpoint(targetAngle.getRadians());
        setVoltage(controller.calculate(pivotSimulation.getAngleRads()));
    }

    @Override
    public void standInPlace(Rotation2d targetAngle) {
        setPower(0);
    }

    @Override
    public void updateInputs(PivotInputsAutoLogged inputs) {
        pivotSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = pivotSimulation.getCurrentDrawAmps();
        inputs.position = Rotation2d.fromRadians(pivotSimulation.getAngleRads());
        inputs.velocity = pivotSimulation.getVelocityRadPerSec();
        inputs.absoluteEncoderPosition = pivotSimulation.getAngleRads();
        inputs.temperature = 0;
        inputs.hasHitForwardLimit = pivotSimulation.hasHitLowerLimit();
        inputs.hasHitBackwardsLimit = pivotSimulation.hasHitLowerLimit();
    }
}
