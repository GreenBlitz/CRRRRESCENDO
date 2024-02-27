package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.CollectNote;
import edu.greenblitz.robotName.commands.intake.*;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
import edu.greenblitz.robotName.commands.shooter.flyWheel.ShootSimulationNote;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.MoveRobotToShootingPosition;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class OI {

	private static OI instance;

	private final SmartJoystick mainJoystick;

	private final SmartJoystick secondJoystick;

	private final SmartJoystick thirdJoystick;

	private final SmartJoystick fourthJoystick;

	private OI() {
		mainJoystick = new SmartJoystick(RobotConstants.Joystick.MAIN);
		secondJoystick = new SmartJoystick(RobotConstants.Joystick.SECOND);
		thirdJoystick = new SmartJoystick(RobotConstants.Joystick.THIRD);
		fourthJoystick = new SmartJoystick(RobotConstants.Joystick.FOURTH);

		initButtons();
		initializeDefaultCommands();
	}

	public static void init() {
		if (instance == null) {
			instance = new OI();
		}
	}

	public static OI getInstance() {
		init();
		return instance;
	}

	public SmartJoystick getMainJoystick() {
		return mainJoystick;
	}

	public SmartJoystick getSecondJoystick() {
		return secondJoystick;
	}

	public SmartJoystick getThirdJoystick() {
		return thirdJoystick;
	}

	public SmartJoystick getFourthJoystick() {
		return fourthJoystick;
	}

	public void initButtons() {
		romyButtons();
		shchoriButtons();
	}

	public void romyButtons() {
		mainJoystick.R1.whileTrue(new CollectNote());
		mainJoystick.L1.whileTrue(new MoveRobotToShootingPosition(ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE));
		mainJoystick.Y.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetPoseByVision()));

		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
	}

	public void shchoriButtons() {
		secondJoystick.BACK.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 1)));
		secondJoystick.START.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 0)));

		//Intake
		secondJoystick.B.whileTrue(new RunIntakeByPower(-0.4));

		//FlyWheel Run
		secondJoystick.L1.whileTrue(new RunFlyWheelByVelocity(100));

		//Pivot Poses
		secondJoystick.POV_UP.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.RIGHT_STAGE.ANGLE));
		secondJoystick.POV_LEFT.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.PODIUM.ANGLE));
		secondJoystick.POV_DOWN.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE));
		secondJoystick.POV_RIGHT.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.FEEDER.ANGLE));
		secondJoystick.R1.whileTrue(new MovePivotByJoystick(secondJoystick, SmartJoystick.Axis.LEFT_Y));

		//Funnel
		Funnel.getInstance().setDefaultCommand(new RunFunnelByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_Y));
	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;
		usedJoystick.A.whileTrue(new NoteFromIntakeToShooter());
		usedJoystick.B.whileTrue(new RunIntakeByJoystick(usedJoystick));
		usedJoystick.X.whileTrue(new RunFunnelByJoystick(usedJoystick, SmartJoystick.Axis.RIGHT_Y));
		usedJoystick.Y.whileTrue(new RunFlyWheelByJoystick(usedJoystick));
		fourthJoystick.Y.whileTrue(new ShootSimulationNote());
	}

	public void initializeDefaultCommands() {
		Battery.getInstance().setDefaultCommand(new BatteryLimiter());
//		Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
//		Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
		Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
	}
}