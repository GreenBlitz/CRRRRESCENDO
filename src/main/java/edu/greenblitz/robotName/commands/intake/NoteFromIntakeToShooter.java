package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
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
        funnel.setVelocity(FunnelConstants.INTAKE_VELOCITY);
        intake.setVelocity(FunnelConstants.INTAKE_VELOCITY * FunnelConstants.FUNNEL_TO_INTAKE_SPEED_CONVERSION);
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