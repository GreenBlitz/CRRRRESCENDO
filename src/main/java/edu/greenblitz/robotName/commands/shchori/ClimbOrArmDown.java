package edu.greenblitz.robotName.commands.shchori;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToClimb;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.climbing.ClimbUp;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class ClimbOrArmDown extends ConditionalCommand {

    public ClimbOrArmDown(){
        super(
                new ClimbUp(),
                new MoveElbowAndWristToSafe(),
                ScoringModeSelector::isClimbMode
        );
    }

}
