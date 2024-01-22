package edu.greenblitz.robotName.commands.lifter;

import edu.greenblitz.robotName.subsystems.Lifter.Lifter;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class LifterCommand extends GBCommand {
    protected Lifter lifter;

    public LifterCommand(){
        lifter = Lifter.getInstance();
        require(lifter);
    }
}
