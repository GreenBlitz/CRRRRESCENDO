package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.DigitalInputMap;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter {

    private LifterInputs inputs = new LifterInputs();
    private DCMotorSim motorSimulation;
    private double appliedOutput;


    public SimulationLifter() {
        motorSimulation = new DCMotorSim(
                DCMotor.getNEO(SimulationConstants.NUMBER_OF_MOTORS),
                SimulationConstants.GEAR_RATIO,
                SimulationConstants.MOMENT_OF_INERTIA);
    }

    @Override
    public void setPower(double power) {
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }


    @Override
    public void setVoltage(double voltage) {
        appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        motorSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoderTo(double position) {
        Logger.recordOutput("Lifter", "tried setting the position to " + position);
    }

    @Override
    public boolean isMotorInPosition(double position) {
        return inputs.position <= position + LifterConstants.PID_TOLERANCE && inputs.position >= position - LifterConstants.PID_TOLERANCE;
    }

    @Override
    public void stopMotor() {
        this.setPower(0);

    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        Logger.getInstance().recordOutput("Lifter", "tried setting the idleMode to " + mode.name());
    }

    @Override
    public boolean isSwitchPressed() {
        return inputs.isSwitchPressed;
    }

    @Override
    public void updateInputs(LifterInputs inputs) {
        motorSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
        inputs.position = motorSimulation.getAngularPositionRotations();
        inputs.velocity = motorSimulation.getAngularVelocityRPM();
        inputs.isSwitchPressed = DigitalInputMap.getInstance().getValue(LifterConstants.SWITCH_ID);
        inputs.kP = LifterConstants.PID_KP;
        inputs.kI = LifterConstants.PID_KI;
        inputs.kD = LifterConstants.PID_KD;
    }

}
