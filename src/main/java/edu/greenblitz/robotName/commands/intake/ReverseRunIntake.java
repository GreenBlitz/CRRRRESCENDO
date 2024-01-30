package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;

public class ReverseRunIntake extends IntakeCommand{
    @Override
    public void execute(){
        intake.rollOut();
    }

    @Override
    public void end(boolean interrupted){
        intake.stop();
    }
}
