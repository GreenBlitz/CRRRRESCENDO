package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.utils.GBSubsystem;

public class Arm extends GBSubsystem {

    private static Arm instance;

    public static void init() {
        if (instance == null)
            instance = new Arm();
    }
    private Arm() {

    }

    @Override
    public void periodic() {
        super.periodic();

    }
}
