package edu.greenblitz.robotName.commands.Pivot;

import edu.greenblitz.robotName.commands.Elbow.MoveElbowToPosition;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Pivot.Pivot;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class UnCollidingMovePivot extends ConditionalCommand {
    public UnCollidingMovePivot(double angle) {
        super(
                new MoveElbowToPosition(ElbowConstants.FORWARD_ANGLE_LIMIT).andThen(new MovePivotToPosition(angle)),
                new MovePivotToPosition(angle),
                Pivot::movingCondition
        );
    }
}