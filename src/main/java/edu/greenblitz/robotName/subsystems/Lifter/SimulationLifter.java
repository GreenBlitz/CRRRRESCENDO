package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.DigitalInputMap;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import org.littletonrobotics.junction.Logger;

public class SimulationLifter implements ILifter {

    private LifterInputsAutoLogged inputs;
    private DCMotorSim motorSimulation;
    private double appliedOutput;


    public SimulationLifter() {
        inputs = new LifterInputsAutoLogged();
        motorSimulation = new DCMotorSim(DCMotor.getNEO(SimulationConstants.NUMBER_OF_MOTORS), SimulationConstants.GEAR_RATIO, SimulationConstants.MOMENT_OF_INERTIA);
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
    public void setDestination(double destination) {
        inputs.destination = destination;
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        motorSimulation.update(RobotConstants.SimulationConstants.TIME_STEP);

        inputs.appliedOutput = appliedOutput;
        inputs.outputCurrent = motorSimulation.getCurrentDrawAmps();
        inputs.position = motorSimulation.getAngularPositionRotations();
        inputs.velocity = motorSimulation.getAngularVelocityRPM();
        this.inputs.isSwitchPressed = DigitalInputMap.getInstance().getValue(LifterConstants.SWITCH_ID);
        inputs.isSwitchPressed = this.inputs.isSwitchPressed;
        inputs.destination = this.inputs.destination;

        this.inputs.isMotorAtPosition = Math.abs(motorSimulation.getAngularPositionRotations() - this.inputs.destination) <= LifterConstants.TOLERANCE;
        inputs.isMotorAtPosition = this.inputs.isMotorAtPosition;
    }

}
