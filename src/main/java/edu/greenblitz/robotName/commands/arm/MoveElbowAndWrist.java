package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveElbowAndWrist extends ParallelCommandGroup {

    public MoveElbowAndWrist(Rotation2d elbowAngle, Rotation2d wristAngle) {
        super(
                new MoveElbowToAngle(elbowAngle),
                new MoveWristToAngle(wristAngle)
        );
    }

    public MoveElbowAndWrist(ElbowConstants.PresetPositions elbowAngle, WristConstants.PresetPositions wristAngle) {
        super(
                new MoveElbowToAngle(elbowAngle),
                new MoveWristToAngle(wristAngle)
        );
    }
}