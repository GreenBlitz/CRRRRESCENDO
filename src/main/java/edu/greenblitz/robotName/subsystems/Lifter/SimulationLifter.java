package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationLifter implements ILifter{

    private LifterInputs lastInputs = new LifterInputs();
    private DCMotorSim motorSimulation;

    @Override
    public void setPower(double power) {
        lastInputs.power = power;
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }


    @Override
    public void setVoltage(double voltage) {
        lastInputs.voltage = voltage;
        double appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        motorSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoderTo(double position) {
        lastInputs.position = position;
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return lastInputs.position <= position + LifterConstants.PID_TOLERANCE &&  lastInputs.position >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void updateInputs(LifterInputs lastInputs) {
    }
    @Override
    public void stopMotor() {
        lastInputs.power = 0;
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lastInputs.idleMode = mode;
    }

    @Override
    public double getPosition() {
        return lastInputs.position;
    }

    @Override
    public boolean isSwitchPressed() {
        return lastInputs.position <= 0;
    }

}
