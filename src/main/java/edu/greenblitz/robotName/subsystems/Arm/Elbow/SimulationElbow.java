package edu.greenblitz.robotName.subsystems.Arm.Elbow;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Arm.ElbowInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowConstants.Simulation.SIM_PID;

public class SimulationElbow implements IElbow {
    SingleJointedArmSim elbowSimulation;
    private double appliedVoltage;

    public SimulationElbow() {
        elbowSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(ElbowConstants.Simulation.NUMBER_OF_MOTORS),
                ElbowConstants.Simulation.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        ElbowConstants.ARM_LENGTH,
                        ElbowConstants.ARM_MASS_KG
                ),
                ElbowConstants.ARM_LENGTH,
                ElbowConstants.BACKWARD_ANGLE_LIMIT,
                ElbowConstants.FORWARD_ANGLE_LIMIT,
                false,
                ElbowConstants.STARTING_ANGLE
        );
    }



    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }



    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        elbowSimulation.setInputVoltage(appliedVoltage);
    }



    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {

        elbowSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = elbowSimulation.getCurrentDrawAmps();
        inputs.position = elbowSimulation.getAngleRads();
        inputs.velocity = elbowSimulation.getVelocityRadPerSec();
        inputs.absoluteEncoderPosition = elbowSimulation.getAngleRads();
        inputs.absoluteEncoderVelocity = elbowSimulation.getVelocityRadPerSec();

        inputs.hasHitForwardLimit = elbowSimulation.hasHitLowerLimit();
        inputs.hasHitBackwardsLimit = elbowSimulation.hasHitLowerLimit();

        inputs.kP = SIM_PID.getKp();
        inputs.kI = SIM_PID.getKi();
        inputs.kD = SIM_PID.getKd();
    }
    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        Logger.getInstance().recordOutput("Arm/Elbow", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void setSoftLimit(CANSparkMax.SoftLimitDirection direction, double limit) {
        Logger.getInstance().recordOutput("Arm/Elbow", "tried to set soft limit for direction " + direction.name() + " to " + limit);
    }

    @Override
    public void updateInputs(edu.greenblitz.robotName.subsystems.Arm.Elbow.ElbowInputsAutoLogged inputs) {

    }

    @Override
    public void setAngleRadiansByPID(double goalAngle, double feedForward) {
        Logger.getInstance().recordOutput("Arm/Elbow", "tried setting the goal angle to " + goalAngle + " with feed  forward of " + feedForward);
    }

    @Override
    public void setPosition(double position) {
        Logger.getInstance().recordOutput("Arm/Elbow", "tried to set the position to " + position);
    }
}
