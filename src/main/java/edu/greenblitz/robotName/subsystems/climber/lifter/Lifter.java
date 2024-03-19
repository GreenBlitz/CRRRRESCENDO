package edu.greenblitz.robotName.subsystems.climber.lifter;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import org.littletonrobotics.junction.Logger;

public class Lifter extends GBSubsystem {
    private static Lifter instance;
    private ILifter lifter;
    private LifterInputsAutoLogged lifterInputs;
    private double maxCurrent;

    private Lifter() {
        lifter = LifterFactory.create();
        lifterInputs = new LifterInputsAutoLogged();
        lifter.updateInputs(lifterInputs);
        resetEncoderPosition();
        maxCurrent = 0;
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
        super.periodic();
        lifter.updateInputs(lifterInputs);
        Logger.processInputs("Climbing/Lifter", lifterInputs);
        Logger.recordOutput("Climbing/Lifter", getLifterPose3d());
        if (getCurrent() > maxCurrent){
            maxCurrent = getCurrent();
        }
    }

    public void goToPosition(double targetPosition) {
        lifter.goToPosition(targetPosition);
    }

    public void setPower(double power) {
        lifter.setPower(power);
    }

    public void setVoltage(double voltage) {
        lifter.setVoltage(voltage);
    }

    public void resetEncoderPosition(double position) {
        lifter.resetEncoder(position);
    }

    public void resetEncoderPosition() {
        resetEncoderPosition(LifterConstants.ENCODER_POSITION_WHEN_RESET);
    }

    public void stop() {
        lifter.stop();
    }

    public void setIdleMode(CANSparkMax.IdleMode mode) {
        lifter.setIdleMode(mode);
    }

    public boolean isSwitchPressed() {
        return lifterInputs.isBackwardSwitchPressed;
    }

    public boolean isAtPosition(double targetPosition) {
        return Math.abs(targetPosition - lifterInputs.position) < LifterConstants.TOLERANCE;
    }

    public boolean hasPassedPositionWhenExtendLifter(double targetPosition){
        return lifterInputs.position >= targetPosition;
    }

    public boolean hasPassedPositionWhenRetractLifter(double targetPosition){
        return lifterInputs.position <= targetPosition;
    }


    public double getPosition() {
        return lifterInputs.position;
    }

    public double getCurrent() {
        return lifterInputs.outputCurrent;
    }

    public double getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrentToZero(){
        maxCurrent = 0;
    }


    public Pose3d getLifterPose3d() {
        return new Pose3d(
                LifterConstants.ROBOT_RELATIVE_LIFTER_POSITION,
                new Rotation3d(
                        -lifterInputs.position,
                        0,
                        0
                ).plus(LifterConstants.ROBOT_RELATIVE_LIFTER_ROTATION)
        );
    }
}