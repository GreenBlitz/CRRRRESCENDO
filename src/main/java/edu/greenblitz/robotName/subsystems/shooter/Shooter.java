package edu.greenblitz.robotName.subsystems.shooter;

import edu.greenblitz.robotName.utils.GBSubsystem;
import org.littletonrobotics.junction.Logger;

public class Shooter extends GBSubsystem {
    private Shooter instance;
    private final IShooter shooter;
    private final ShooterInputsAutoLogged shooterInputs = new ShooterInputsAutoLogged();

    public Shooter(){
        shooter = ShooterFactory.create();
        shooter.updateInputs(shooterInputs);
    }

    public Shooter getInstance(){
        if(instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    public void setPower(double power){
        shooter.setPower(power);
    }

    @Override
    public void periodic() {
        shooter.updateInputs(shooterInputs);
        Logger.getInstance().processInputs("Shooter", shooterInputs);
    }
}
