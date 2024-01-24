package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lifter extends GBSubsystem {
    private static Lifter instance;
    private ILifter lifter;
    private LifterInputs lifterInputs;
    private ProfiledPIDController pid;

    private Lifter() {
        lifter = LifterFactory.create();
        lifterInputs = new LifterInputs();
        lifter.updateInputs(lifterInputs);
        pid = new ProfiledPIDController(LifterConstants.PID_KP, LifterConstants.PID_KI, LifterConstants.PID_KD, new TrapezoidProfile.Constraints(LifterConstants.MAX_VELOCITY, LifterConstants.MAX_ACCELERATION));
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
        if (lifterInputs.isSwitchPressed) {
            resetEncoder(0);
        }
        SmartDashboard.putBoolean("isSwitchPresed", lifterInputs.isSwitchPressed);
    }

    public void goToPositionByPID(double pos) {
        lifter.setPower(pid.calculate(lifterInputs.position, pos));
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

    public boolean isMotorAtPosition(double position) {
        lifter.setDestination(position);
        lifter.updateInputs(lifterInputs);
        return lifterInputs.isMotorAtPosition;
    }

    public void stopMotor() {
        lifter.stopMotor();
    }

    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lifter.setIdleMode(mode);
    }
}