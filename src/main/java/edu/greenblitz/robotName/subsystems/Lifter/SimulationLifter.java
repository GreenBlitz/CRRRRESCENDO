package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter{

    private LifterInputs lastInputs = new LifterInputs();
    private DCMotorSim motorSimulation;
    private double appliedOutput;


    public SimulationLifter(){

    }

    @Override
    public void setPower(double power) {
        lastInputs.power = power;
        setVoltage(power * RobotConstants.SimulationConstants.BATTERY_VOLTAGE);
    }


    @Override
    public void setVoltage(double voltage) {
        lastInputs.voltage = voltage;
        appliedOutput = MathUtil.clamp(voltage, -RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE, RobotConstants.SimulationConstants.MAX_MOTOR_VOLTAGE);
        motorSimulation.setInputVoltage(appliedOutput);
    }

    @Override
    public void resetEncoderTo(double position) {

        Logger.recordOutput("Lifter", "tried setting the position to " +position);



    }

    @Override
    public boolean isMotorInPosition(double position) {
        return lastInputs.position

    }

    @Override
    public void updateInputs(LifterInputsAutoLogged lastInputs) {
        lastInputs.power = this.lastInputs.power;
        lastInputs.position = this.lastInputs.position;
        lastInputs.voltage = this.lastInputs.voltage;
        lastInputs.idleMode = this.lastInputs.idleMode;
    }
    @Override
    public void stopMotor() {
        this.setPower(0);

    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        Logger.getInstance().recordOutput("Lifter", "tried setting the idleMode to "+mode.name());
    }

    @Override
    public boolean isSwitchPressed() {

    }

}
