package edu.greenblitz.robotName.subsystems.Shooter;

import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.Mechanism.ShooterMechanism;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;

public class Shooter {

    public static void init(){
        Pivot.init();
        FlyWheel.init();
        ShooterMechanism.init();
    }

}
