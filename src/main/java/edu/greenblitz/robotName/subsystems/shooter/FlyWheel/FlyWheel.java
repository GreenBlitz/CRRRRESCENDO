package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class FlyWheel extends GBSubsystem {
    private static FlyWheel instance;
    private IFlyWheel flyWheel;
    private FlyWheelInputsAutoLogged flyWheelInputs;
    private boolean isPreparedToShoot;

    private FlyWheel() {
        flyWheel = FlyWheelFactory.create();
        flyWheelInputs = new FlyWheelInputsAutoLogged();
        flyWheel.updateInputs(flyWheelInputs);
    }

    public static void init() {
        if (instance == null) {
            instance = new FlyWheel();
        }
    }

    public static FlyWheel getInstance() {
        init();
        return instance;
    }

    public void setPower(double leftPower, double rightPower) {
        flyWheel.setPower(rightPower, leftPower);
    }

    public void setVoltage(double leftVoltage, double rightVoltage) {
        flyWheel.setVoltage(rightVoltage, leftVoltage);
    }

    public void setVelocity(double leftVelocity, double rightVelocity) {
        flyWheel.setVelocity(rightVelocity, leftVelocity);
    }

    public void stop() {
        flyWheel.setPower(0, 0);
    }

    public void setPreparedToShoot(boolean isPreparedToShoot) {
        this.isPreparedToShoot = isPreparedToShoot;
    }

    public double getVelocity() {
        return flyWheelInputs.velocity;
    }

    public double getCurrent() {
        return flyWheelInputs.outputCurrent;
    }

    public double getVoltage() {
        return flyWheelInputs.appliedOutput;
    }


    public boolean isAtVelocity(double velocity) {
        return Math.abs(getVelocity() - velocity) < FlyWheelConstants.EPSILON_RPM;
    }

    @Override
    public void periodic() {
        flyWheel.updateInputs(flyWheelInputs);
        Logger.processInputs("FlyWheel", flyWheelInputs);
    }
}
