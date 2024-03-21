package edu.greenblitz.robotName.commands.shooter;

import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByPower;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ReverseRunFunnelUntilObjectOut;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.greenblitz.robotName.utils.GBCommand;

public class CollectNoteFromShooterUntilBeamBreakerBreak extends GBCommand {

	private Funnel funnel;

	private FlyWheel flyWheel;

	public CollectNoteFromShooterUntilBeamBreakerBreak(){
		funnel = Funnel.getInstance();
		require(funnel);
		flyWheel = FlyWheel.getInstance();
		require(flyWheel);
	}

	@Override
	public void initialize() {
		flyWheel.setVelocity(FlyWheelConstants.FEEDER_COLLECT_VELOCITY, FlyWheelConstants.FEEDER_COLLECT_VELOCITY);
	}

	@Override
	public void execute() {
		funnel.setPower(-0.1);
	}

	@Override
	public boolean isFinished() {
		return funnel.isObjectIn();
	}

	@Override
	public void end(boolean interrupted) {
		funnel.stop();
		flyWheel.stop();
	}
}
