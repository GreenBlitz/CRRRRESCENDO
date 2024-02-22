package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.NoteToShooter;
import edu.greenblitz.robotName.commands.PanicMode;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.ScoreToAmp;
import edu.greenblitz.robotName.commands.arm.roller.RunRollerByJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringMode;
import edu.greenblitz.robotName.commands.intake.RunIntakeByJoystick;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByJoystick;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocity;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingState;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;

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

		secondJoystick.B.whileTrue(new NoteToShooter());
		secondJoystick.Y.whileTrue(new MovePivotByJoystick(secondJoystick));
		secondJoystick.X.whileTrue(new RunFlyWheelByVelocity(3000));
		secondJoystick.A.whileTrue(new RunFunnelByJoystick(secondJoystick));

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
		secondJoystick.START.whileTrue(new PanicMode());
		secondJoystick.BACK.whileTrue(new ToggleScoringMode());
		secondJoystick.A.whileTrue(new ScoreToAmp());
		secondJoystick.B.whileTrue(new MoveElbowAndWristToSafe());
		secondJoystick.X.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.PICK_UP.ANGLE));
		secondJoystick.Y.whileTrue(new MoveShooterToAngle(PivotConstants.PresetPositions.TRANSFER.ANGLE));
		secondJoystick.POV_DOWN.whileTrue(new CollectNoteToScoringMode());

		mainJoystick.A.whileTrue(new GoToShootingState(ShootingPositionConstants.OPTIMAL_SHOOTING_ZONE));
	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;
		usedJoystick.A.whileTrue(new NoteToShooter());
		usedJoystick.B.whileTrue(new RunIntakeByJoystick(usedJoystick));
		usedJoystick.X.whileTrue(new RunFunnelByJoystick(usedJoystick));
		usedJoystick.Y.whileTrue(new RunFlyWheelByJoystick(usedJoystick));
		fourthJoystick.Y.whileTrue(new ShootSimulationNote());
	}

	public void initializeDefaultCommands() {
		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
//		Battery.getInstance().setDefaultCommand(new BatteryLimiter());
//		Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
//		Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
//		Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
	}
}