package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveArmToIntakePosition extends ParallelCommandGroup {

    public MoveArmToIntakePosition(){
        super(
                new MoveElbowAndWrist(ElbowConstants.PresetPositions.TRANSFER, WristConstants.PresetPositions.TRANSFER)
        );
    }
}
