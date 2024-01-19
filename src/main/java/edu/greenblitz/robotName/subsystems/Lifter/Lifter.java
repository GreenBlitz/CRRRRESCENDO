package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.controller.PIDController;

public class Lifter extends GBSubsystem {

    private static Lifter instance;
    private ILifter lifter;
    private SparkMaxPIDController pid1;
    private SparkMaxPIDController pid2;

    private Lifter() {
        lifter = LifterFactory.create();
        pid1 = lifter.getMotor1PID();
        pid2 = lifter.getMotor2PID();
        pid1.setP(LifterConstants.PID_KP);
        pid1.setI(LifterConstants.PID_KI);
        pid1.setD(LifterConstants.PID_KD);

        pid2.setP(LifterConstants.PID_KP);
        pid2.setI(LifterConstants.PID_KI);
        pid2.setD(LifterConstants.PID_KD);
    }
    public static Lifter getInstance() {
        if(instance == null) {
            instance = new Lifter();
        }
        return instance;
    }
    public void getToPoseByPID(double pos) {
//        lifter.setPowerForMotor1(pid1.);
    }
    public void setPower(double power) {
        lifter.setPowerForMotor1(power);
        lifter.setPowerForMotor2(power);
    }

    public void setVoltage(double voltage) {
        lifter.setVoltageForMotor1(voltage);
        lifter.setVoltageForMotor2(voltage);
    }
    public void setPosition(double position) {
        lifter.setPositionForMotor1(position);
        lifter.setPositionForMotor2(position);
    }
    public boolean areTheMotorsInPosition(double position) {
        return lifter.isMotor1InPosition(position) && lifter.isMotor2InPosition(position);
    }
    public void updateInputs(LifterInputs lastInputs) {

    }
    public void stopMotor() {
        lifter.stopMotor1();
        lifter.stopMotor2();
    }

    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lifter.setIdleModeForMotor1(mode);
        lifter.setIdleModeForMotor2(mode);
    }


}