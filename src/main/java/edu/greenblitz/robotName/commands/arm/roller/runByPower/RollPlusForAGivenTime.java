package edu.greenblitz.robotName.commands.arm.roller.runByPower;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RollPlusForAGivenTime extends ParallelRaceGroup {

    public RollPlusForAGivenTime(double seconds) {
        super(
                new RollPlus(),
                new WaitCommand(seconds)
        );
    }
}