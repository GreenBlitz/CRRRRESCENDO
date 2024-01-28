package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter {
    private DCMotorSim motorSimulation;
    private double appliedOutput;
    private ProfiledPIDController pidController;
    private Debouncer debouncer;
    private DigitalInput digitalInput;

    public SimulationLifter() {
        digitalInput = new DigitalInput(LifterConstants.SWITCH_ID);
        debouncer = new Debouncer(SimulationConstants.DEBOUNCE_TIME_FOR_SWITCH);
        motorSimulation = new DCMotorSim(DCMotor.getNEO(SimulationConstants.NUMBER_OF_MOTORS), SimulationConstants.GEAR_RATIO, SimulationConstants.MOMENT_OF_INERTIA);
        pidController = SimulationConstants.SIMULATION_PID;
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }


    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(voltage, RobotConstants.SimulationConstants.MIN_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        motorSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoder(double position) {
        Logger.recordOutput("Lifter", "tried setting the position to " + position);
    }

    @Override
    public void stopMotor() {
        this.setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        Logger.getInstance().recordOutput("Lifter", "tried setting the idleMode to " + idleMode.name());
    }

    @Override
    public void goToPosition(double pos) {
        setPower(pidController.calculate(motorSimulation.getAngularPositionRotations(), pos));
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        motorSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);
        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
        inputs.position = motorSimulation.getAngularPositionRotations();
        inputs.velocity = motorSimulation.getAngularVelocityRPM();
        inputs.isSwitchPressed = debouncer.calculate(digitalInput.get());
        inputs.isMotorAtPosition = Math.abs(motorSimulation.getAngularPositionRotations() - inputs.destination) <= LifterConstants.TOLERANCE;
    }
}
