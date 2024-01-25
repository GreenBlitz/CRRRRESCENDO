package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.Intake.IntakeConstants;

public class ReverseRunIntake extends IntakeCommand{
    @Override
    public void execute(){
//        intake.setPower(IntakeConstants.POWER_TO_REVERSE_RUN);
    }

    @Override
    public void end(boolean interrupted){
        intake.stop();
    }
}
