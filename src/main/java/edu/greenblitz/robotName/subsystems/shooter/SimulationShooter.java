package edu.greenblitz.robotName.subsystems.shooter;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class SimulationShooter implements IShooter{
    FlywheelSim shooterSim;

    public SimulationShooter(){
        shooterSim = new FlywheelSim(
                ShoooterConstants
        )
    }

    @Override
    public void updateInputs(ShooterInputsAutoLogged inputs) {

    }



}
