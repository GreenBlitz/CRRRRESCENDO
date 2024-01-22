package edu.greenblitz.robotName.subsystems.Arm;

import edu.greenblitz.robotName.utils.GBSubsystem;

public class Arm extends GBSubsystem {

    private static Arm instance;

    private Elbow elbow;

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
    }

    public Elbow getElbow(){
        return elbow;
    }

    public Wrist getWrist(){
        return wrist;
    }

    public void move2AngleBy2Angles(double elbowTargetAngle, double wristTargetAngle){
        elbow.setGoalAngle(elbowTargetAngle);
        wrist.setGoalAngle(wristTargetAngle);
    }

    public boolean isAtGoalAngles(){
        return elbow.isAtGoalAngle() && wrist.isAtGoalAngle();
    }
}
