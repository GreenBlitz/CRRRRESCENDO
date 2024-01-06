package edu.greenblitz.robotName.utils;

public class PitchRollAdder {
    public static double add(double pitch, double roll){
        if (pitch == 0){
            return roll;
        }
        double a = 1;
        double b = Math.tan(roll)/Math.tan(pitch);
        double c = Math.abs(1/Math.tan(pitch));

        double addedAngle = Math.acos((c)/Math.sqrt(a*a + b*b + c*c));
        return addedAngle;
    }



}
