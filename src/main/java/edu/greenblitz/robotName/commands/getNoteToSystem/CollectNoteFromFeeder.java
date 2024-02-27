package edu.greenblitz.robotName.commands.getNoteToSystem;

import edu.greenblitz.robotName.commands.shooter.CollectNoteFromShooterUntilBeamBreakerBreak;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByPower;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ForwardRunFunnelUntilObjectIn;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ReverseRunFunnelUntilObjectOut;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.RunFunnelByPowerUntilCondition;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectNoteFromFeeder extends SequentialCommandGroup {

	public CollectNoteFromFeeder(){
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.FEEDER.ANGLE),
				new CollectNoteFromShooterUntilBeamBreakerBreak(),
				new ReverseRunFunnelUntilObjectOut().deadlineWith(new RunFlyWheelByPower(-0.1)),
				new RunFunnelByVelocity(120).until(() -> Funnel.getInstance().isObjectIn())
		);
	}
}
