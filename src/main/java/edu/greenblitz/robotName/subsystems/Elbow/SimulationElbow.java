package edu.greenblitz.robotName.subsystems.Elbow;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.*;

public class SimulationElbow implements IElbow {

    SingleJointedArmSim elbowSimulation;

    private double appliedVoltage;

    private final PIDController controller = ElbowConstants.Simulation.SIM_PID.getPIDController();


    public SimulationElbow() {
        elbowSimulation = new SingleJointedArmSim(
                DCMotor.getFalcon500(ElbowConstants.Simulation.NUMBER_OF_MOTORS),
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
        setVoltage(power * BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -MAX_MOTOR_VOLTAGE, MAX_MOTOR_VOLTAGE);
        elbowSimulation.setInputVoltage(appliedVoltage);
    }

    @Override
    public void setIdleMode(NeutralModeValue idleMode) {
        Logger.recordOutput("Arm/Elbow", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void resetAngle(double position) {
        Logger.recordOutput("Arm/Elbow", "tried to set the position to " + position);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        controller.setSetpoint(goalAngle);
        setVoltage(controller.calculate(elbowSimulation.getAngleRads()));
    }

    @Override
    public void updateInputs(ElbowInputsAutoLogged inputs) {
        elbowSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = elbowSimulation.getCurrentDrawAmps();
        inputs.position = elbowSimulation.getAngleRads();
        inputs.velocity = elbowSimulation.getVelocityRadPerSec();
        inputs.absoluteEncoderPosition = elbowSimulation.getAngleRads();
        inputs.hasHitForwardLimit = elbowSimulation.hasHitLowerLimit();
        inputs.hasHitBackwardsLimit = elbowSimulation.hasHitLowerLimit();
    }
}
