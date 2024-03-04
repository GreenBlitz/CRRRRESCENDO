package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteFromFeeder;
import edu.greenblitz.robotName.commands.intake.NoteToShooterWithoutArm;
import edu.greenblitz.robotName.commands.intake.NoteToShooterForJoystick;
import edu.greenblitz.robotName.commands.intake.RunIntakeByPower;
import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.funnel.runByPowerUntilCondition.ForwardRunFunnelUntilObjectIn;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.RotateToPoint;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.shootingStateService.ShootingStateCalculations;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.modules.Comc;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.Set;
import java.util.function.DoubleSupplier;

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
		mainJoystick.R1.whileTrue(new NoteToShooterForJoystick());
		mainJoystick.L1.whileTrue(new CollectNoteFromFeeder());
		mainJoystick.Y.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetPoseByVision()));

		mainJoystick.R1.whileTrue(
				new MoveByJoysticks(MoveByJoysticks.DriveMode.NORMAL,() -> new RotateToPoint(() -> FieldConstants.MIDDLE_OF_RED_SPEAKER_POSITION.toTranslation2d()).getAsDouble()));
		mainJoystick.X.whileTrue(new Comc());

		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
	}

	public void shchoriButtons() {
		//Rumble
		secondJoystick.BACK.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 1)));
		secondJoystick.START.whileTrue(new InstantCommand(() -> secondJoystick.rumble(true, 0)));

		//Intake
		secondJoystick.B.whileTrue(new RunIntakeByPower(-0.4));
		secondJoystick.X.whileTrue(new RunIntakeByPower(0.5));

		//FlyWheel Run
		secondJoystick.L1.whileTrue(new RunFlyWheelByVelocityUntilInterrupted(100, secondJoystick));

		//Pivot Poses
		secondJoystick.POV_UP.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.RIGHT_STAGE.ANGLE));
		secondJoystick.POV_LEFT.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.PODIUM.ANGLE));
		secondJoystick.POV_DOWN.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE));
		secondJoystick.POV_RIGHT.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.FEEDER.ANGLE));
		secondJoystick.R1.whileTrue(new MovePivotByJoystick(secondJoystick, SmartJoystick.Axis.LEFT_Y));

		//Funnel
		secondJoystick.R1.whileTrue(new RunFunnelByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_Y));
		secondJoystick.Y.whileTrue(new ForwardRunFunnelUntilObjectIn());
	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;
		usedJoystick.R1.whileTrue(new NoteToShooterWithoutArm());
		usedJoystick.L1.whileTrue(new RunFunnelByJoystick(usedJoystick, SmartJoystick.Axis.RIGHT_Y));

	}

	public void fourthJoystickButtons() {
		SmartJoystick usedJoystick = fourthJoystick;
		usedJoystick.B.whileTrue(new CollectNoteFromFeeder());
		usedJoystick.R1.onTrue(new InstantCommand(() -> SmartDashboard.putNumber("Pivot angleee", Pivot.getInstance().getAngle().getDegrees())));
		usedJoystick.Y.whileTrue(new MovePivotByJoystick(usedJoystick, SmartJoystick.Axis.LEFT_Y));
	}

	public void initializeDefaultCommands() {
		Battery.getInstance().setDefaultCommand(new BatteryLimiter());
		Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
		Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
		Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
	}
}