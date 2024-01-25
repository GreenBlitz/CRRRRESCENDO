package edu.greenblitz.robotName.commands.arm.elbow;

import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;

public class ElbowCommand extends GBCommand {

    protected Elbow elbow;

    public ElbowCommand(){
        elbow = Elbow.getInstance();
        require(elbow);
    }


}