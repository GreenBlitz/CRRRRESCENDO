package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.arm.roller.ReverseRunRoller;
import edu.greenblitz.robotName.commands.arm.roller.RunRoller;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.EndEffector.WristUtils.WristConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CatchRollerNote extends SequentialCommandGroup {
    public CatchRollerNote(){
        addCommands(
                new MoveElbowAndWrist(ElbowConstants.PresetPositions.STARTING.ANGLE, WristConstants.PresetPositions.STARTING.ANGLE),
                new ReverseRunRoller()
        );
    }
}
