package edu.greenblitz.robotName.commands.intake;

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
