package edu.greenblitz.robotName.commands.arm.roller;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RollClockWiseForAGivenTime extends ParallelRaceGroup {
    public RollClockWiseForAGivenTime(double seconds){
        super(
                new RollClockWise(),
                new WaitCommand(seconds)
        );
    }
}