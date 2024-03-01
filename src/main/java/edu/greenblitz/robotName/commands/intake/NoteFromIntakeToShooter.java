package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;

public class NoteFromIntakeToShooter extends GBCommand {

    private Funnel funnel;

    private Intake intake;

    public NoteFromIntakeToShooter() {
        funnel = Funnel.getInstance();
        intake = Intake.getInstance();
        require(intake);
        require(funnel);
    }

    @Override
    public void initialize() {
        funnel.setVelocity(175 * (4 / 5.0));
        intake.setVelocity(175);
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