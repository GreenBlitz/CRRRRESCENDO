package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollClockwise;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.wpi.first.math.geometry.Rotation2d;

public class RunByPowerAndCountRotations extends RollClockwise {

    private Rotation2d rotationCounter;

    public RunByPowerAndCountRotations(Rotation2d rotationCounter){
        super();
        this.rotationCounter = rotationCounter;
    }

    @Override
    public void initialize() {
        roller.resetEncoder(new Rotation2d(0));
    }

    @Override
    public boolean isFinished() {
        return Math.abs(roller.getAngle().getRotations()) > Math.abs(rotationCounter.getRotations());
    }
}
