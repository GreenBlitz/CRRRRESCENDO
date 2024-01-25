package edu.greenblitz.robotName.subsystems.arm;

import edu.greenblitz.robotName.subsystems.arm.ArmMechanism.ArmMechanism;

public class Arm {

    public static void init(){
        Elbow.init();
        Wrist.init();
        Roller.init();
        ArmMechanism.init();
    }

}
