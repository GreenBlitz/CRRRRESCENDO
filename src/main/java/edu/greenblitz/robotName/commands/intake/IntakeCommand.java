package edu.greenblitz.robotName.commands.intake;

import edu.greenblitz.robotName.subsystems.Intake.Intake;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class IntakeCommand extends GBCommand {
     protected Intake intake;

     public IntakeCommand(){
         intake = Intake.getInstance();
         require(intake);
     }
}
