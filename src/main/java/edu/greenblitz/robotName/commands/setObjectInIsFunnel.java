package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class setObjectInIsFunnel extends InstantCommand {

    boolean isFunnel;
    public setObjectInIsFunnel(boolean isFunnel) {
        this.isFunnel = isFunnel;
    }

    @Override
    public void initialize() {
        if (isFunnel)
            Funnel.getInstance().toggleObjectInside();
        else
            Roller.getInstance().toggleObjectInside();
    }
}
