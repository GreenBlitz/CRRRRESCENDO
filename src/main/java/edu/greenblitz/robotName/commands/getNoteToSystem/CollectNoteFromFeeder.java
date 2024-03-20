package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.shooter.CollectNoteFromShooterUntilBeamBreakerBreak;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByPower;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ReverseRunFunnelUntilObjectOut;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteFromFeeder extends SequentialCommandGroup {

	public CollectNoteFromFeeder() {
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.FEEDER.ANGLE),
				new CollectNoteFromShooterUntilBeamBreakerBreak(),
				new ParallelDeadlineGroup(
						new ReverseRunFunnelUntilObjectOut(),
						new RunFlyWheelByPower(FlyWheelConstants.COLLECT_FROM_FEEDER_POWER)
				),
				new RunFunnelByVelocity(FunnelConstants.FEEDER_COLLECT_SPEED)
						.until(() -> Funnel.getInstance().isObjectIn())
		);
	}
}
