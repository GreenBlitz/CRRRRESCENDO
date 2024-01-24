package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.subsystems.Arm.ArmMechanism.ArmMechanism;

public class Arm {

    public static void init(){
        Elbow.init();
        Wrist.init();
        Roller.init();
        ArmMechanism.init();
    }

}
