package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;

public class CheckShooterToArm extends SystemCheckCommand {

    private Funnel funnel;

    private Roller roller;

    public CheckShooterToArm() {
        roller = Roller.getInstance();
        require(roller);
        funnel = Funnel.getInstance();
        require(funnel);
    }

    @Override
    public void execute() {
            funnel.rollOut();
            roller.rollClockwise();
    }

    @Override
    public boolean isFinished() {
        return roller.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        funnel.stop();
        roller.stop();
    }
}
