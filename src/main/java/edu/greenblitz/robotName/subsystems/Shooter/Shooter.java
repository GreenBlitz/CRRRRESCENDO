package edu.greenblitz.robotName.subsystems.Shooter;

import edu.greenblitz.robotName.subsystems.Shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.Shooter.Mechanism.ShooterMechanism;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.Pivot;

public class Shooter {

    public static void init(){
        Pivot.init();
        FlyWheel.init();
        ShooterMechanism.init();
    }

}
