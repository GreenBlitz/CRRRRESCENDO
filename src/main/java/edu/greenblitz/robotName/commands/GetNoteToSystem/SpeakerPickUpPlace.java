package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.Robot;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;

public class SpeakerPickUpPlace extends PivotCommand {
    //TODO - ADD THINGS TO PREVENT COLLABORATION

    public SpeakerPickUpPlace() {
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
