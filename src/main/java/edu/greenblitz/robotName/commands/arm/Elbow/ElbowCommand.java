package edu.greenblitz.robotName.commands.arm.Elbow;

import edu.greenblitz.robotName.subsystems.Arm.Elbow;
import edu.greenblitz.robotName.utils.GBCommand;

public class ElbowCommand extends GBCommand {

    protected Elbow elbow;

    public ElbowCommand(){
        elbow = Elbow.getInstance();
        require(elbow);
    }


}
