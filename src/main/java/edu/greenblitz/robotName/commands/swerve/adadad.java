package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.Arm.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;

public class adadad extends GBCommand {
    private Elbow elbow;

    private double angle;
    public adadad(double goalAngle) {
        elbow = Elbow.getInstance();
        angle = goalAngle;
    }

    @Override
    public void initialize() {
        elbow.setGoalAngle(angle);
    }
}
