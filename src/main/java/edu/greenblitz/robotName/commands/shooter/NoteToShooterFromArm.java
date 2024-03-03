package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollCounterClockwise;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class NoteToShooterFromArm extends ParallelDeadlineGroup {

    public NoteToShooterFromArm(){
        super(
                new RunFunnelByVelocity(FunnelConstants.INTAKE_VELOCITY).until(() -> Funnel.getInstance().isObjectIn()),
                new RollCounterClockwise()
        );
    }


}
