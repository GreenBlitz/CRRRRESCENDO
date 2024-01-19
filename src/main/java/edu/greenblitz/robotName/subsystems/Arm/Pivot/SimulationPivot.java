package edu.greenblitz.robotName.subsystems.Arm.Pivot;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants.Simulation.SIM_PID;
import static edu.greenblitz.robotName.subsystems.Arm.Pivot.PivotConstants.Simulation.STARTING_ANGLE;

public class SimulationPivot implements IPivot {
    SingleJointedArmSim pivotSimulation;
    private double appliedVoltage;

    private PIDController controller = SIM_PID.getPIDController();

    public SimulationPivot() {
        pivotSimulation = new SingleJointedArmSim(
                DCMotor.getFalcon500(PivotConstants.Simulation.NUMBER_OF_MOTORS),
                PivotConstants.Simulation.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        PivotConstants.LENGTH_OF_SHOOTER,
                        PivotConstants.SHOOTER_MASS_KG
                ),
                PivotConstants.LENGTH_OF_SHOOTER,
                PivotConstants.BACKWARD_ANGLE_LIMIT,
                PivotConstants.FORWARD_ANGLE_LIMIT,
                false,
                STARTING_ANGLE
        );
    }


    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void moveToAngle(double goalAngle) {
        controller.setSetpoint(goalAngle);
        setVoltage(controller.calculate(Pivot.getInstance().getAngleInRadians()));
    }


    @Override
    public void setVoltage(double voltage) {
        appliedVoltage = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        pivotSimulation.setInputVoltage(appliedVoltage);
    }


    @Override
    public void setIdleMode(NeutralModeValue idleMode) {
        Logger.getInstance().recordOutput("Arm/Pivot", "tried setting the idleMode to " + idleMode.name());
    }


    @Override
    public void resetPosition(double position) {
        Logger.getInstance().recordOutput("Arm/Pivot", "tried to reset the position to " + position);
    }

    @Override
    public void updateInputs(PivotInputsAutoLogged inputs) {
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
