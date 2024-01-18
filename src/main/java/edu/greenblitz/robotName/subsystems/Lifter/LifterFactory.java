package edu.greenblitz.robotName.subsystems.Lifter;

import edu.greenblitz.robotName.Robot;

public class LifterFactory {

    public static ILifter create(){
        switch (Robot.RobotType.ROBOT_NAME){
            case ROBOT_NAME -> {
                return new NeoLifter();
            }
            case SIMULATION -> {
                return new SimulationLifter();
            }
        }
        return new LifterReply();
    }

}
