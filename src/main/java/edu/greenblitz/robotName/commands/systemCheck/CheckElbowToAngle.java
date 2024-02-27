package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.wpi.first.math.geometry.Rotation2d;

public class CheckElbowToAngle extends SystemCheckCommand {

    protected Elbow elbow;

    private Rotation2d targetAngle;

    public CheckElbowToAngle(Rotation2d targetAngle) {
        elbow = Elbow.getInstance();
        require(elbow);
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {
        elbow.moveToAngle(targetAngle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            elbow.moveToAngle(targetAngle);
        }
    }

    @Override
    public boolean isFinished() {
        return elbow.isAtAngle(targetAngle);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        elbow.setCurrentAngle();
        elbow.standInPlace();
    }
}
