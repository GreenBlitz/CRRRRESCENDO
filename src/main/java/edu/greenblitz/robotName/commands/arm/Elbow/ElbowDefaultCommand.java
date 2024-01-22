package edu.greenblitz.robotName.commands.arm.Elbow;

public class ElbowDefaultCommand extends ElbowCommand{

    @Override
    public void execute() {
        elbow.standInPlace();
    }
}
