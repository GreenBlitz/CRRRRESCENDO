package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.utils.GBSubsystem;

public class Arm extends GBSubsystem {

    private static Arm instance;

    private Elbow elbow;

    private Roller roller;

    private Wrist wrist;


    public static void init() {
        if (instance == null)
            instance = new Arm();
    }

    public static Arm getInstance() {
        return instance;
    }

    private Arm() {
        elbow = Elbow.getInstance();
        wrist = Wrist.getInstance();
        roller = Roller.getInstance();
    }


    public void move2AngleBy2Angles(double elbowTargetAngle, double wristTargetAngle){
        elbow.setGoalAngle(elbowTargetAngle);
        wrist.setGoalAngle(wristTargetAngle);
    }
}
