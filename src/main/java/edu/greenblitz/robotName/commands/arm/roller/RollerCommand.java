package edu.greenblitz.robotName.commands.arm.roller;

import edu.greenblitz.robotName.subsystems.arm.Roller;
import edu.greenblitz.robotName.utils.GBCommand;

public class RollerCommand extends GBCommand {
    protected Roller roller;

    public RollerCommand(){
        roller = Roller.getInstance();
        require(roller);
    }
}
