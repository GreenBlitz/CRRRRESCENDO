package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj.Timer;

public class RoborioUtils {
    private static double lastTime;
    private static double currentTime;

    public static void updateCurrentCycleTime(){
        lastTime = currentTime;
        currentTime = Timer.getFPGATimestamp();
    }

    public static double getCurrentRoborioCycle (){
        return lastTime - currentTime;
    }
}
