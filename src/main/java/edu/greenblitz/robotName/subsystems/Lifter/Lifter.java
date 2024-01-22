package edu.greenblitz.robotName.subsystems.Lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class Lifter extends GBSubsystem {
    private static Lifter instance;
    private ILifter lifter;
    private LifterInputsAutoLogged lifterInputs;
    private ProfiledPIDController pid;

    private Lifter() {
        lifter = LifterFactory.create();
        lifterInputs = new LifterInputsAutoLogged();
        pid = new ProfiledPIDController(LifterConstants.PID_KP,LifterConstants.PID_KI,LifterConstants.PID_KD,new TrapezoidProfile.Constraints(LifterConstants.MAX_VELOCITY, LifterConstants.MAX_ACCELERATION));
    }
    public static Lifter getInstance() {
        if(instance == null) {
            instance = new Lifter();
        }
        return instance;
    }
    @Override
    public void periodic() {
        if(lifter.isSwitchPressed()) {
            resetEncoderTo(0);
        }
        lifter.updateInputs(lifterInputs);
    }
    public void getToPoseByPID(double pos) {
        lifter.setPower(pid.calculate(lifterInputs.position,pos));
    }
    public void setPower(double power) {
        lifter.setPower(power);
    }

    public void setVoltage(double voltage) {
        lifter.setVoltage(voltage);
    }
    public void resetEncoderTo(double position) {
        lifter.resetEncoderTo(position);
    }
    public boolean isMotorInPosition(double position) {
        return lifter.isMotorInPosition(position);
    }
    public void stopMotor() {
        lifter.stopMotor();
    }
    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lifter.setIdleMode(mode);
    }
}