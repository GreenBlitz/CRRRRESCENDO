package edu.greenblitz.robotName.commands.Elbow;

public class moveElbowToPosition extends ElbowCommand {

    private final double elbowAngle;
    public moveElbowToPosition(double goalAngle) {
        super();
        elbowAngle = goalAngle;
    }

    @Override
    public void initialize() {
        elbow.setGoalAngle(elbowAngle);
    }


}
