package edu.greenblitz.robotName.subsystems.arm.roller.neoRoller;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.subsystems.arm.roller.IRoller;
import edu.greenblitz.robotName.subsystems.arm.roller.RollerInputsAutoLogged;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NeoRoller implements IRoller {

    private GBSparkMax motor;

    private RollerInputsAutoLogged lastInputs;

    public NeoRoller() {
        motor = new GBSparkMax(NeoRollerConstants.MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);

        motor.config(NeoRollerConstants.ROLLER_CONFIG_OBJECT);
        motor.getPIDController().setFeedbackDevice(motor.getEncoder());
        resetEncoder(Rotation2d.fromDegrees(0));
        lastInputs = new RollerInputsAutoLogged();
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
    public void moveToPosition(Rotation2d position) {
        SmartDashboard.putNumber("targett", position.getRotations());
        motor.getPIDController().setReference(
                position.getRotations(),
                CANSparkMax.ControlType.kPosition
        );
    }

    public boolean isObjectInByCurrent(RollerInputsAutoLogged inputs){
        if (inputs.outputCurrent >= NeoRollerConstants.NOTE_IN_CURRENT) return true;
        if ((inputs.position.getRotations() - motor.getEncoder().getPosition() <= NeoRollerConstants.MOVING_TOLERANCE.getRotations())) return true;
        return false;
    }

    @Override
    public void updateInputs(RollerInputsAutoLogged inputs) {
        inputs.outputCurrent = motor.getOutputCurrent();
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.isObjectIn = isObjectInByCurrent(inputs);
        inputs.position = Rotation2d.fromRotations(motor.getEncoder().getPosition());
    }
}