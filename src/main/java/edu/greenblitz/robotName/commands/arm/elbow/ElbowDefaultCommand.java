package edu.greenblitz.robotName.commands.arm.elbow;

public class ElbowDefaultCommand extends ElbowCommand{

    @Override
    public void execute() {
        elbow.standInPlace();
    }
}
