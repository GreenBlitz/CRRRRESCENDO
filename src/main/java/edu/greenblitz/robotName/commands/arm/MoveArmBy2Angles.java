package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveArmBy2Angles extends ParallelCommandGroup {
    public MoveArmBy2Angles(Rotation2d elbowAngle, Rotation2d wristAngle) {
        super(
                new MoveElbowToAngle(elbowAngle),
                new MoveWristToAngle(wristAngle)
        );
    }
}
