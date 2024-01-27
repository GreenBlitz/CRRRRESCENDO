package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveArmBy2Angles;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class SwitchBetweenSystemsPlace extends ParallelCommandGroup {
    //TODO - ENUM SHIT IS VERY UGLY
    public SwitchBetweenSystemsPlace() {
        super(
                new MoveArmBy2Angles(ElbowConstants.ImportantPlaces.TRANSFER.angle, WristConstants.ImportantPlaces.TRANSFER.angle),
                new MovePivotToAngle(PivotConstants.ImportantPlaces.TRANSFER.angle)
        );
    }
}
