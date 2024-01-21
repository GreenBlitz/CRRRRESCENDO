package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class Lifter extends GBSubsystem {

    private static Lifter instance;
    private ILifter lifter;
    private ProfiledPIDController pid1;
    private ProfiledPIDController pid2;

    private Lifter() {
        lifter = LifterFactory.create();
        pid1 = new ProfiledPIDController(LifterConstants.PID_KP,LifterConstants.PID_KI,LifterConstants.PID_KD,new TrapezoidProfile.Constraints(LifterConstants.MAX_VELOCITY, LifterConstants.MAX_ACCELERATION));
        pid2 = new ProfiledPIDController(LifterConstants.PID_KP,LifterConstants.PID_KI,LifterConstants.PID_KD,new TrapezoidProfile.Constraints(LifterConstants.MAX_VELOCITY, LifterConstants.MAX_ACCELERATION));
    }
    public static Lifter getInstance() {
        if(instance == null) {
            instance = new Lifter();
        }
        return instance;
    }
    public void getToPoseByPID(double pos) {
        lifter.setPowerForMotor1(pid1.calculate(lifter.getPositionForMotor1(),pos));
        lifter.setPowerForMotor2(pid2.calculate(lifter.getPositionForMotor2(),pos));
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