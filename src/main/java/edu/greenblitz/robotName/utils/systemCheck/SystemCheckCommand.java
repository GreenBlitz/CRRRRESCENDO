package edu.greenblitz.robotName.utils.systemCheck;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public interface SystemCheckCommand {
    
    boolean hasFinished();
    
    ParallelRaceGroup raceWith(Command... parallel);
}
