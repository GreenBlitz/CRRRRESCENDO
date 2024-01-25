package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.GBSubsystem;

public class Lifter extends GBSubsystem {
    private static Lifter instance;
    private ILifter lifter;
    private LifterInputsAutoLogged lifterInputs;

    private Lifter() {
        lifter = LifterFactory.create();
        lifterInputs = new LifterInputsAutoLogged();
        lifter.updateInputs(lifterInputs);
        setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public static Lifter getInstance() {
        init();
        return instance;
    }

    public static void init() {
        if (instance == null) {
            instance = new Lifter();
        }
    }

    @Override
    public void periodic() {
        lifter.updateInputs(lifterInputs);
    }

    public void goToDestinationByPID() {
        lifter.goToPosition(lifterInputs.destination);
    }

    public void setPower(double power) {
        lifter.setPower(power);
    }

    public void setVoltage(double voltage) {
        lifter.setVoltage(voltage);
    }

    public void resetEncoder(double position) {
        lifter.resetEncoder(position);
    }

    public void resetEncoder() {
        lifter.resetEncoder(0);
    }

    public boolean isMotorAtPosition() {
        return lifterInputs.isMotorAtPosition;
    }

    public void setDestination(double destination) {
        lifterInputs.destination = destination;
    }

    public boolean isSwitchPressed() {
        return lifterInputs.isSwitchPressed;
    }

    public void stopMotor() {
        lifter.stopMotor();
    }

    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lifter.setIdleMode(mode);
    }
}