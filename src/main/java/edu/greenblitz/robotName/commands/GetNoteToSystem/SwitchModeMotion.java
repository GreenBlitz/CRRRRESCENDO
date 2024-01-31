package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class SwitchModeMotion extends GBCommand {

    private Command selectedCommand;
    private ScoringMode scoringMode;
    private BooleanSupplier isNoteInOtherSystem;

    public SwitchModeMotion(ScoringMode mode, BooleanSupplier isNoteInOtherSystem) {
        this.isNoteInOtherSystem = isNoteInOtherSystem;
        this.scoringMode = mode;
    }

    @Override
    public void initialize() {
        new SetScoringMode(scoringMode);
        if (isNoteInOtherSystem.getAsBoolean()){
            selectedCommand = new TransferNote();
        }
        else {
            selectedCommand = new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE);
        }
        selectedCommand.schedule();
    }

    @Override
    public boolean isFinished() {
        return selectedCommand.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        selectedCommand.end(interrupted);
    }
}
