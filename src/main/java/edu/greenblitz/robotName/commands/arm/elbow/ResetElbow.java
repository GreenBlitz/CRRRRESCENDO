package edu.greenblitz.robotName.commands.arm.elbow;

import edu.wpi.first.math.geometry.Rotation2d;

public class ResetElbow extends ElbowCommand {


    @Override
    public void execute() {
        elbow.setMotorVoltage(0.5);
    }

    @Override
    public boolean isFinished() {
        return elbow.getCurrent() > 15;
    }

    @Override
    public void end(boolean interrupted) {
        elbow.resetAngle(Rotation2d.fromDegrees(0));
        elbow.setPower(0);
    }
}
