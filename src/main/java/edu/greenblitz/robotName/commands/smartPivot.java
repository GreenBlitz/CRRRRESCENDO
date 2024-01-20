package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.commands.Elbow.ElbowCommand;
import edu.greenblitz.robotName.commands.Elbow.moveElbowToPosition;
import edu.greenblitz.robotName.commands.Pivot.movePivotToPosition;
import edu.greenblitz.robotName.subsystems.Elbow.Elbow;
import edu.greenblitz.robotName.subsystems.Elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class smartPivot extends ConditionalCommand {
    public smartPivot(double angle) {
        super(
                new moveElbowToPosition(ElbowConstants.FORWARD_ANGLE_LIMIT).andThen(new movePivotToPosition(angle)),
                new movePivotToPosition(angle),
                () -> Elbow.getInstance().isAtAngle(ElbowConstants.BRODER)
        );
    }
}