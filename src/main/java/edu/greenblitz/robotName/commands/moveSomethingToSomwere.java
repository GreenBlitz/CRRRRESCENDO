//package edu.greenblitz.robotName.commands;
//
//import edu.greenblitz.robotName.subsystems.arm.Elbow;
//import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
//import edu.greenblitz.robotName.utils.GBCommand;
//import edu.wpi.first.math.geometry.Rotation2d;
//
//public class moveSomethingToSomewhere extends GBCommand {
//    Funnel funnel;
//    public moveSomethingToSomewhere() {
//        funnel = Funnel.getInstance();
//    }
//
//    @Override
//    public void initialize() {
//
//    }
//
//    @Override
//    public void execute() {
//        funnel.rollIn();
//    }
//
//    @Override
//    public boolean isFinished(){
//        return funnel.isObjectIn();
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        funnel.stop();
//    }
//}
