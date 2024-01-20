package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.neoFlyWheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;


public class NeoFlyWheel implements IFlyWheel {
    private GBSparkMax motor;
    private SimpleMotorFeedforward feedforward;

    public NeoFlyWheel() {
        motor = new GBSparkMax(NeoFlyWheelConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
    public void setVelocity(double velocity) {
        motor.getPIDController().setReference(
                velocity,
                CANSparkMax.ControlType.kVelocity,
                NeoFlyWheelConstants.PID_SLOT,
                NeoFlyWheelConstants.FEEDFORWARD.calculate(velocity)
        );
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.velocity = motor.getEncoder().getVelocity();
    }
}
