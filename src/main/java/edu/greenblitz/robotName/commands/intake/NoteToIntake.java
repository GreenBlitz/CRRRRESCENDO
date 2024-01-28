
package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;


public class NoteToIntake extends IntakeCommand {


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
        intake.stop();
    }

}

