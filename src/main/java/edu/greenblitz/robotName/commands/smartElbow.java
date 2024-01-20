package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.Elbow.moveElbowToPosition;
import edu.greenblitz.robotName.commands.Pivot.movePivotToPosition;
import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class smartElbow extends ConditionalCommand {

    public smartElbow(double goalAngle) {
        super(
                new movePivotToPosition(PivotConstants.SAFE_ANGLE).andThen(new moveElbowToPosition(goalAngle)),
                new moveElbowToPosition(goalAngle),
                () -> Elbow.getInstance().isPassBroder(goalAngle)
        );
    }
}
