package edu.greenblitz.robotName.subsystems.Arm.EndEffector;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.BATTERY_VOLTAGE;

public class SimulationEndEffector implements IEndEffector {

    SingleJointedArmSim pivotSimulation;

    private double appliedVoltage;

    private PIDController controller = EndEffectorConstants.Simulation.SIM_PID.getPIDController();


    public SimulationEndEffector() {
        pivotSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(EndEffectorConstants.Simulation.NUMBER_OF_MOTORS),
                EndEffectorConstants.Simulation.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        EndEffectorConstants.LENGTH_OF_SHOOTER,
                        EndEffectorConstants.SHOOTER_MASS_KG
                ),
                EndEffectorConstants.LENGTH_OF_SHOOTER,
                EndEffectorConstants.BACKWARD_ANGLE_LIMIT,
                EndEffectorConstants.FORWARD_ANGLE_LIMIT,
                false,
                EndEffectorConstants.STARTING_ANGLE
        );
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
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        Logger.recordOutput("Arm/Pivot", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void resetAngle(double position) {
        Logger.recordOutput("Arm/Pivot", "tried to reset the position to " + position);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        controller.setSetpoint(goalAngle);
        setVoltage(controller.calculate(pivotSimulation.getAngleRads()));
    }

    @Override
    public void updateInputs(EndEffectorInputsAutoLogged inputs) {
        pivotSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = pivotSimulation.getCurrentDrawAmps();
        inputs.position = pivotSimulation.getAngleRads();
        inputs.velocity = pivotSimulation.getVelocityRadPerSec();
        inputs.absoluteEncoderPosition = pivotSimulation.getAngleRads();
        inputs.hasHitForwardLimit = pivotSimulation.hasHitLowerLimit();
        inputs.hasHitBackwardsLimit = pivotSimulation.hasHitLowerLimit();
    }
}
