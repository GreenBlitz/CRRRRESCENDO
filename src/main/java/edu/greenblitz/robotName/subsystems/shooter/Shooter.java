package edu.greenblitz.robotName.subsystems.shooter;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;

public class Shooter {

    public static void init(){
        Pivot.init();
        FlyWheel.init();
    }

}
