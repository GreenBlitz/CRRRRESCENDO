package edu.greenblitz.robotName.commands.Elbow;

public class MoveElbowToPosition extends ElbowCommand {

    private final double elbowAngle;
    public MoveElbowToPosition(double goalAngle) {
        super();
        elbowAngle = goalAngle;
    }

    @Override
    public void initialize() {
        elbow.setGoalAngle(elbowAngle);
    }


}
