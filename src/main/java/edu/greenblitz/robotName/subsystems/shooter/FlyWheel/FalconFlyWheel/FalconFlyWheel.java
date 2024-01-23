package edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel;


import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.IFlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.NeoFlyWheel.NeoFlyWheelConstants;


import static edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FalconFlyWheel.FalconFlyWheelConstants.*;


public class FalconFlyWheel implements IFlyWheel {
    private TalonFX motor;

    public FalconFlyWheel() {
        motor = new TalonFX(NeoFlyWheelConstants.MOTOR_ID);
        motor.getConfigurator().apply(CURRENT_LIMITS_CONFIGS);
        motor.getConfigurator().apply(CLOSED_LOOP_RAMPS_CONFIGS);
        motor.getConfigurator().apply(MOTION_MAGIC_CONFIGS);
        motor.setNeutralMode(NEUTRAL_MODE_VALUE);
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
        motor.setControl(new VelocityVoltage(velocity));
    }

    @Override
    public void updateInputs(FlyWheelInputsAutoLogged inputs) {
        inputs.appliedOutput = motor.getMotorVoltage().getValue();
        inputs.outputCurrent = motor.getSupplyCurrent().getValue();
        inputs.temperature = motor.getDeviceTemp().getValue();
        inputs.velocity = motor.getVelocity().getValue();
    }
}
