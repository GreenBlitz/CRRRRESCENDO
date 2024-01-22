package edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.SimulationWrist;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.IWrist;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.Arm.EndEffector.Wrist.WristInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.littletonrobotics.junction.Logger;

import static edu.greenblitz.robotName.RobotConstants.SimulationConstants.BATTERY_VOLTAGE;

public class SimulationWrist implements IWrist {

    SingleJointedArmSim wristSimulation;

    private double appliedVoltage;

    private PIDController controller = SimulationWristConstants.SIM_PID.getPIDController();

    private SendableChooser<Boolean> isObjectIn;

    public SimulationWrist() {
        wristSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(SimulationWristConstants.NUMBER_OF_MOTORS),
                SimulationWristConstants.GEAR_RATIO,
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
        isObjectIn = new SendableChooser<>();
        isObjectIn.setDefaultOption("False", false);
        isObjectIn.addOption("True", true);
        SmartDashboard.putData(isObjectIn);
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
        inputs.temperature = 0;
        inputs.hasHitForwardLimit = wristSimulation.hasHitLowerLimit();
        inputs.hasHitBackwardsLimit = wristSimulation.hasHitLowerLimit();
        inputs.isObjectInside = isObjectIn.getSelected();
    }
}
