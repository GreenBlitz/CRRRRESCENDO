package edu.greenblitz.robotName.subsystems.shooter;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.math.filter.Debouncer;
import org.littletonrobotics.junction.Logger;

public class Shooter extends GBSubsystem {
    private static Shooter instance;
    private final IShooter shooter;
    private final ShooterInputsAutoLogged shooterInputs;
    private boolean isPreparedToShoot;

    private Shooter() {
        shooter = ShooterFactory.create();
        shooterInputs = new ShooterInputsAutoLogged();
        shooter.updateInputs(shooterInputs);
    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    public void setPower(double power) {
        shooter.setPower(power);
    }

    public void setVelocity(double velocity) {
        shooter.setVelocity(velocity);
    }

    public void stop() {
        shooter.setPower(0);
    }

    public void setPreparedToShoot(boolean isPreparedToShoot) {
        this.isPreparedToShoot = isPreparedToShoot;
    }

    public double getVelocity() {
        return shooterInputs.velocity;
    }
    public double getCurrent (){
        return shooterInputs.outputCurrent;
    }
    public double getVoltage(){
        return shooterInputs.appliedOutput;
    }

    @Override
    public void periodic() {
        shooter.updateInputs(shooterInputs);
        Logger.getInstance().processInputs("Shooter", shooterInputs);
    }
}
