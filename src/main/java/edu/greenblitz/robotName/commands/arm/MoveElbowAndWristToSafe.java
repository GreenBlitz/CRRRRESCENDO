package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;

public class MoveElbowAndWristToSafe extends MoveElbowAndWrist {

    public MoveElbowAndWristToSafe() {
        super(
                ElbowConstants.PresetPositions.SAFE,
                WristConstants.PresetPositions.SAFE
        );
    }
}