package edu.greenblitz.robotName.subsystems.lifter.neoLifter;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.lifter.ILifter;
import edu.greenblitz.robotName.subsystems.lifter.LifterConstants;
import edu.greenblitz.robotName.subsystems.lifter.LifterInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public class NeoLifter implements ILifter {

    private GBSparkMax motor;
    private TalonSRX solenoid;

    public NeoLifter() {
        motor = new GBSparkMax(NeoLifterConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        solenoid = new TalonSRX(NeoLifterConstants.SOLENOID_ID);
        motor.config(NeoLifterConstants.CONFIG);

        motor.getReverseLimitSwitch(NeoLifterConstants.BACKWARD_LIMIT_SWITCH_TYPE)
                .enableLimitSwitch(NeoLifterConstants.IS_BACKWARD_LIMIT_SWITCH_ENABLED);
        motor.getForwardLimitSwitch(NeoLifterConstants.FORWARD_LIMIT_SWITCH_TYPE)
                .enableLimitSwitch(NeoLifterConstants.IS_FORWARD_LIMIT_SWITCH_ENABLED);

        motor.setSoftLimit(
                CANSparkMax.SoftLimitDirection.kReverse,
                LifterConstants.BACKWARD_LIMIT.getRadians()
        );
        motor.setSoftLimit(
                CANSparkMax.SoftLimitDirection.kForward,
                LifterConstants.FORWARD_LIMIT.getRadians()
        );
        motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void resetEncoder(Rotation2d position) {
        motor.getEncoder().setPosition(position.getRadians());
    }

    @Override
    public void stop() {
        this.setPower(0);
    }

    @Override
    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        motor.setIdleMode(idleMode);
    }

    @Override
    public void goToPosition(Rotation2d position) {
        motor.getPIDController().setReference(
                position.getRadians(),
                CANSparkMax.ControlType.kPosition,
                NeoLifterConstants.PID_SLOT,
                NeoLifterConstants.FEED_FORWARD.calculate(position.getRadians())
        );
    }

    @Override
    public void openSolenoid() {
        setPowerToSolenoid(LifterConstants.POWER_TO_OPEN_SOLENOID);
    }

    @Override
    public void closeSolenoid() {
        setPowerToSolenoid(LifterConstants.POWER_TO_CLOSE_SOLENOID);
    }

    @Override
    public void holdSolenoid() {
        setPowerToSolenoid(LifterConstants.POWER_TO_HOLD_SOLENOID);
    }

    @Override
    public void setPowerToSolenoid(double powerSolenoid) {
        solenoid.set(TalonSRXControlMode.PercentOutput, powerSolenoid);
    }

    @Override
    public void updateInputs(LifterInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.position = motor.getEncoder().getPosition();
        inputs.velocity = motor.getEncoder().getVelocity();
        inputs.isForwardSwitchPressed = motor.getForwardLimitSwitch(NeoLifterConstants.FORWARD_LIMIT_SWITCH_TYPE).isPressed();
        inputs.isBackwardSwitchPressed = motor.getReverseLimitSwitch(NeoLifterConstants.BACKWARD_LIMIT_SWITCH_TYPE).isPressed();
        inputs.currentSolenoid = solenoid.getStatorCurrent();
        inputs.voltageSolenoid = solenoid.getMotorOutputVoltage();
        inputs.isOpenSolenoid = inputs.voltageSolenoid > 0;

    }
}