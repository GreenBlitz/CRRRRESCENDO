package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.Robot;
import edu.wpi.first.math.geometry.Rotation2d;

public class RunRollerByRotations extends RollerCommand{

    private Rotation2d rotations;

    public RunRollerByRotations(Rotation2d rotations) {
        this.rotations = rotations;
    }

    @Override
    public void initialize() {
        roller.resetEncoder(new Rotation2d(0));
        roller.moveToPosition(rotations);
    }

    @Override
    public void execute() {
        System.out.println("Aaaaaaaaaaaaaa");
        if (Robot.isSimulation()){
            roller.moveToPosition(rotations);
        }
    }

//    @Override
//    public boolean isFinished() {
//        return roller.isAtAngle(rotations);
//    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}