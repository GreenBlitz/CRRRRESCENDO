package edu.greenblitz.robotName.subsystems.shooter.FlyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheelInputsAutoLogged;
import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class FlyWheel extends GBSubsystem {
    private static FlyWheel instance;
    private final IFlyWheel flyWheel;
    private final FlyWheelInputsAutoLogged flyWheelInputs;
    private boolean isPreparedToShoot;

    private FlyWheel() {
        flyWheel = FlyWheelFactory.create();
        flyWheelInputs = new FlyWheelInputsAutoLogged();
        flyWheel.updateInputs(flyWheelInputs);
    }

    public static FlyWheel getInstance() {
        if (instance == null) {
            instance = new FlyWheel();
        }
        return instance;
    }

    public void setPower(double power) {
        flyWheel.setPower(power);
    }

    public void setVelocity(double velocity) {
        flyWheel.setVelocity(velocity);
    }

    public void stop() {
        flyWheel.setPower(0);
    }

    public void setPreparedToShoot(boolean isPreparedToShoot) {
        this.isPreparedToShoot = isPreparedToShoot;
    }

    public double getVelocity() {
        return flyWheelInputs.velocity;
    }
    public double getCurrent (){
        return flyWheelInputs.outputCurrent;
    }
    public double getVoltage(){
        return flyWheelInputs.appliedOutput;
    }

    @Override
    public void periodic() {
        flyWheel.updateInputs(flyWheelInputs);
        Logger.getInstance().processInputs("FlyWheel", flyWheelInputs);
    }
}
