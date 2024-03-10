package edu.greenblitz.robotName.commands.shooter.pivot;

import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.utils.GBCommand;
import org.littletonrobotics.junction.Logger;

public class PivotCommand extends GBCommand {

    protected Pivot pivot;

    public PivotCommand() {
        pivot = Pivot.getInstance();
        require(pivot);
    }
    
    @Override
    public void initialize() {
        super.initialize();
        Logger.recordOutput("pivot/currentCommand", this.getName());
    }
    
    public void end(boolean interrupted) {
        pivot.setCurrentAngle();
        pivot.standInPlace();
    }
}