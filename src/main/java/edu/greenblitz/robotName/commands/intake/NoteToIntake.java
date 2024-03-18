package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NoteToIntake extends IntakeCommand {
    
    @Override
    public void execute() {
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return intake.isObjectIn() || Funnel.getInstance().isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }
}