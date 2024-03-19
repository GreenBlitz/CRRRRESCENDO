package edu.greenblitz.robotName.commands.climbing.lifter;

import edu.greenblitz.robotName.subsystems.climber.lifter.Lifter;
import edu.greenblitz.robotName.utils.GBCommand;

public abstract class LifterCommand extends GBCommand {

    protected Lifter lifter;

    public LifterCommand() {
        lifter = Lifter.getInstance();
    }
}