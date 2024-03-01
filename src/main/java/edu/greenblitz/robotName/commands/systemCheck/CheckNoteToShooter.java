package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;

public class CheckNoteToShooter extends SystemCheckCommand {

    private Funnel funnel;

    private Intake intake;

    public CheckNoteToShooter() {
        funnel = Funnel.getInstance();
        intake = Intake.getInstance();
        require(intake);
        require(funnel);
    }

    @Override
    public void execute() {
        funnel.rollIn();
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return funnel.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        funnel.stop();
        intake.stop();
    }
}
