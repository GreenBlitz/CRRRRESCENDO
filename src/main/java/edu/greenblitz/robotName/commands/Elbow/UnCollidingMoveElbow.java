package edu.greenblitz.robotName.commands.Elbow;

import edu.greenblitz.robotName.commands.Pivot.MovePivotToPosition;
import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class UnCollidingMoveElbow extends ConditionalCommand {

    public UnCollidingMoveElbow(double goalAngle) {
        super(
                new MovePivotToPosition(PivotConstants.SAFE_ANGLE).andThen(new MoveElbowToPosition(goalAngle)),
                new MoveElbowToPosition(goalAngle),
                () -> Elbow.movingCondition(goalAngle)
        );
    }
}
