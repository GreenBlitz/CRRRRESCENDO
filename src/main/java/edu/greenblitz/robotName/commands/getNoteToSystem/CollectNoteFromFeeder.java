package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByPower;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.RunFunnelByPowerUntilCondition;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteFromFeeder extends ParallelCommandGroup {

	public CollectNoteFromFeeder() {
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.FEEDER),
				new SequentialCommandGroup(
						new ParallelDeadlineGroup(
								new RunFunnelByPowerUntilCondition(FunnelConstants.EJECT_POWER, () -> Funnel.getInstance().isObjectIn()),
								new RunFlyWheelByPower(FlyWheelConstants.COLLECT_FROM_FEEDER_POWER)
						),
						new ParallelDeadlineGroup(
								new RunFunnelByPowerUntilCondition(FunnelConstants.EJECT_POWER, () -> !Funnel.getInstance().isObjectIn()),
								new RunFlyWheelByPower(FlyWheelConstants.COLLECT_FROM_FEEDER_POWER)
						),
						new RunFunnelByPowerUntilCondition(0.3, () -> Funnel.getInstance().isObjectIn())
				)
		);
	}
}
