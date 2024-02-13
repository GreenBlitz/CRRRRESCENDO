package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;

public class NoteToShooter extends GBCommand {

    private Funnel funnel;

    private Intake intake;

    public NoteToShooter() {
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
        funnel.stop();
        intake.stop();
    }
}