package edu.greenblitz.robotName.commands.arm.roller;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RollClockwiseForAGivenTime extends ParallelRaceGroup {

    public RollClockwiseForAGivenTime(double seconds){
        super(
                new RollClockwise(),
                new WaitCommand(seconds)
        );
    }
}