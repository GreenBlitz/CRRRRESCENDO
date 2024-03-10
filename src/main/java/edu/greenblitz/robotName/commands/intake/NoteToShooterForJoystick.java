package edu.greenblitz.robotName.commands.intake;

public class NoteToShooterForJoystick extends IntakeCommand {

    @Override
    public void execute() {
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return intake.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        if (!interrupted) {
            new NoteFromIntakeToShooterWithArm().schedule();
        }
    }

}

