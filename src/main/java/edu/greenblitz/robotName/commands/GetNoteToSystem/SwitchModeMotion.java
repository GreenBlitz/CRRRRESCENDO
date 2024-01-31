package edu.greenblitz.robotName.commands.GetNoteToSystem;

import edu.greenblitz.robotName.commands.arm.MoveElbowAndWrist;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.arm.Elbow;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.PivotConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.ScoringMode;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class SwitchModeMotion {

    public static Supplier<Command> getCommand(ScoringMode mode, BooleanSupplier isNoteInOtherSystem){
        if (isNoteInOtherSystem.getAsBoolean()){
            return () -> new ParallelCommandGroup(new SetScoringMode(mode), new TransferNote()).asProxy();
        }
        else {
            return () -> new ParallelCommandGroup(new SetScoringMode(mode), MoveShooterToAngle.getCommand(PivotConstants.PresetPositions.PICK_UP.ANGLE)).asProxy();
        }
    }

}
