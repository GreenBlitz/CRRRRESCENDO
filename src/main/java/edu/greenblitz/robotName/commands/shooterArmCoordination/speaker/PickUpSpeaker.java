package edu.greenblitz.robotName.commands.shooterArmCoordination.speaker;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class PickUpSpeaker extends PivotCommand {

    public PickUpSpeaker() {
        super();
    }
    @Override
    public void initialize() {
        pivot.moveToAngle(PivotConstants.ImportantPlaces.PICK_UP.angle);
    }

    @Override
    public void execute() {
        if (Robot.isSimulation()) {
            pivot.moveToAngle(PivotConstants.ImportantPlaces.PICK_UP.angle);
        }
    }

    public boolean isFinished() {
        return pivot.isAtAngle(PivotConstants.ImportantPlaces.PICK_UP.angle);
    }

}
