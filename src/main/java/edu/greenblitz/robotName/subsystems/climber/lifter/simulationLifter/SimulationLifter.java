package edu.greenblitz.robotName.subsystems.climber.lifter.simulationLifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.climber.lifter.ILifter;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterInputsAutoLogged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter {

    private SingleJointedArmSim lifterSimulation;
    private double appliedOutput;
    private ProfiledPIDController pidController;
    double positionReference;

    public SimulationLifter() {
        lifterSimulation = new SingleJointedArmSim(
                DCMotor.getNEO(SimulationLifterConstants.NUMBER_OF_MOTORS),
                SimulationLifterConstants.GEAR_RATIO,
                SingleJointedArmSim.estimateMOI(
                        LifterConstants.LENGTH_OF_LIFTER,
                        LifterConstants.LIFTER_MASS_KG
                ),
                LifterConstants.LENGTH_OF_LIFTER,
                LifterConstants.BACKWARD_LIMIT,
                LifterConstants.FORWARD_LIMIT,
                false,
                LifterConstants.STARTING_POSITION
        );
        pidController = SimulationLifterConstants.SIMULATION_PID;

        positionReference = lifterSimulation.getAngleRads();
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }

    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(
                voltage,
                RobotConstants.SimulationConstants.MIN_MOTOR_VOLTAGE,
                RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE
        );
        lifterSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoder(double position) {
        Logger.recordOutput("Lifter", "tried setting the position to " + position);
    }

    @Override
    public void stop() {
        this.setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        Logger.recordOutput("Lifter", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void goToPosition(double position) {
        positionReference = position;
        setVoltage(pidController.calculate(lifterSimulation.getAngleRads(), position));
    }


    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        lifterSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = lifterSimulation.getCurrentDrawAmps();
        inputs.position = lifterSimulation.getAngleRads();
        inputs.positionReference = positionReference;
        inputs.velocity = lifterSimulation.getVelocityRadPerSec();
    }
}