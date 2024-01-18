package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.GBSubsystem;

public class Lifter extends GBSubsystem {

    private static Lifter instance;
    private ILifter lifter;

    private Lifter() {
        lifter = LifterFactory.create();
    }
    public static Lifter getInstance() {
        if(instance == null) {
            instance = new Lifter();
        }
        return instance;
    }

    public void setPower(double power) {
        lifter.setPower(power);
    }

    public void setVoltage(double voltage) {
        lifter.setVoltage(voltage);
    }
    public void setPosition(double position) {
        lifter.setPosition(position);
    }
    public boolean isMotorInPosition(double position) {
        return lifter.isMotorInPosition(position);
    }
    public void updateInputs(LifterInputs lastInputs) {

    }
    public void stopMotor() {
        lifter.stopMotor();
    }

    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lifter.setIdleMode(mode);
    }


}