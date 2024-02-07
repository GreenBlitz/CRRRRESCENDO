package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.wpi.first.math.geometry.Rotation2d;

public class RunFunnelByRotations extends FunnelCommand{

    private Rotation2d rotations;

    public RunFunnelByRotations(Rotation2d rotations) {
        this.rotations = rotations;
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
