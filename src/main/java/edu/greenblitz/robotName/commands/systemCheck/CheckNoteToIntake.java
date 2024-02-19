package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.subsystems.intake.Intake;

public class CheckNoteToIntake extends SystemCheckCommand {

    protected Intake intake;

    public CheckNoteToIntake() {
        intake = Intake.getInstance();
        require(intake);
    }

    @Override
    public void execute() {
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return intake.getExitBeamBreakerValue();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        intake.stop();
    }
}
