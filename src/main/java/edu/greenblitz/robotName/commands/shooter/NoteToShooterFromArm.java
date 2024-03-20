package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollClockwise;
import edu.greenblitz.robotName.commands.arm.roller.runByPower.RunRollerByPower;
import edu.greenblitz.robotName.commands.intake.RunIntakeByVelocityConstant;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoteToShooterFromArm extends SequentialCommandGroup {

    public NoteToShooterFromArm() {
        super(
                new ParallelDeadlineGroup(
                        new RunFunnelByVelocity(FunnelConstants.INTAKE_VELOCITY).until(() -> Funnel.getInstance().isObjectIn()),
                        new RunIntakeByVelocityConstant(),
                        new RunRollerByPower(0.6)
                ),
                new InstantCommand(() -> Roller.getInstance().setObjectOut())
        );
    }


}
