package edu.greenblitz.robotName.subsystems.Arm.Wrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.BATTERY_VOLTAGE;
import static edu.greenblitz.robotName.subsystems.Arm.Wrist.WristConstants.Simulation.SIM_PID;

public class SimulationWrist implements IWrist {

    SingleJointedArmSim wristSimulation;

    private double appliedVoltage;

    private PIDController controller = SIM_PID.getPIDController();


    public SimulationWrist() {
        wristSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(WristConstants.Simulation.NUMBER_OF_MOTORS),
                WristConstants.Simulation.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        WristConstants.LENGTH_OF_ENDEFFECTOR,
                        WristConstants.SHOOTER_MASS_KG
                ),
                WristConstants.LENGTH_OF_ENDEFFECTOR,
                WristConstants.BACKWARD_ANGLE_LIMIT,
                WristConstants.FORWARD_ANGLE_LIMIT,
                false,
                WristConstants.STARTING_ANGLE
        );
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
    public void resetAngle(double position) {
        Logger.recordOutput("Arm/Wrist", "tried to reset the position to " + position);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        controller.setSetpoint(goalAngle);
        setVoltage(controller.calculate(wristSimulation.getAngleRads()));
    }

    @Override
    public void updateInputs(WristInputsAutoLogged inputs) {
        wristSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedVoltage;
        inputs.outputCurrent = wristSimulation.getCurrentDrawAmps();
        inputs.position = wristSimulation.getAngleRads();
        inputs.velocity = wristSimulation.getVelocityRadPerSec();
        inputs.absoluteEncoderPosition = wristSimulation.getAngleRads();
        inputs.hasHitForwardLimit = wristSimulation.hasHitLowerLimit();
        inputs.hasHitBackwardsLimit = wristSimulation.hasHitLowerLimit();
        inputs.kP = SIM_PID.getKp();
        inputs.kI = SIM_PID.getKi();
        inputs.kD = SIM_PID.getKd();
    }
}
