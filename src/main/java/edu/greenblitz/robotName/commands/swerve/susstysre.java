package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.Arm.Wrist;
import edu.greenblitz.robotName.utils.GBCommand;

public class susstysre extends GBCommand {
    private Wrist wrist;

    private double angle;
    public susstysre(double goalAngle) {
        wrist = Wrist.getInstance();
        angle = goalAngle;
    }

    @Override
    public void initialize() {
        wrist.setGoalAngle(angle);
    }
}
