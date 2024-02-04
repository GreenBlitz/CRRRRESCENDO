package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.wpi.first.math.geometry.Rotation2d;

public class DoRotations extends FunnelCommand{

    private Rotation2d rotations;

    public DoRotations(Rotation2d rotations) {
        rotations = rotations;
    }

    @Override
    public void initialize() {
        funnel.resetEncoder(new Rotation2d(0));
    }

    @Override
    public void execute() {
        funnel.moveToPosition(rotations);
    }

    @Override
    public boolean isFinished() {
        return funnel.getPosition() == rotations.getRadians();
    }
}
