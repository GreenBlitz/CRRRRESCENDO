package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.MotorFlyWheel.neoFlyWheel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.MotorFlyWheel.MotorFlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;


public class NeoFlyWheel implements IFlyWheel {
    private GBSparkMax motor;

    public NeoFlyWheel() {
        motor = new GBSparkMax(MotorFlyWheelConstants.MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
                MotorFlyWheelConstants.PID_SLOT,
                MotorFlyWheelConstants.FEEDFORWARD.calculate(velocity)
        );
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.velocity = motor.getEncoder().getVelocity();
    }
}
