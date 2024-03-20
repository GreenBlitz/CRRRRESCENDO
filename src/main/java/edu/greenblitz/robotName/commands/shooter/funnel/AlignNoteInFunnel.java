package edu.greenblitz.robotName.commands.shooter.funnel;

import edu.greenblitz.robotName.commands.arm.roller.runByPower.RollCounterClockwise;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ReverseRunFunnelUntilObjectOut;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AlignNoteInFunnel extends SequentialCommandGroup {

	public AlignNoteInFunnel() {
		super(
				new ReverseRunFunnelUntilObjectOut(),
				new ParallelDeadlineGroup(
						new RunFunnelByVelocity(FunnelConstants.INTAKE_VELOCITY).until(
								() -> Funnel.getInstance().isObjectIn()
						),
						new RollCounterClockwise()
				)

		);
	}

}
