package edu.greenblitz.robotName.commands.arm.roller;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RollInForAGivenTime extends ParallelRaceGroup {
    public RollInForGivenTime(double seconds){
        super(
                new RollIn(),
                new WaitCommand(seconds)
        );
    }
}