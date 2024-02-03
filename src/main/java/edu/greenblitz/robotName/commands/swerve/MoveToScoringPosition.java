package edu.greenblitz.robotName.commands.swerve;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import java.util.function.BooleanSupplier;

public class MoveToScoringPosition extends ConditionalCommand {

    public MoveToScoringPosition(BooleanSupplier condition) {
        super( new MoveToSpeaker(), new MoveToAmp(), condition);
    }
}
