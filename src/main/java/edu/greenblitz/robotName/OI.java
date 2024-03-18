package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.LED.UpdateLEDStateDefaultCommand;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristToSafe;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristWithRunFunnel;
import edu.greenblitz.robotName.commands.arm.ScoreToTrap;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowByJoystick;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowToAngle;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteInRoller;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToAmp;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToTrap;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristByButton;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.climbing.CloseAndThenHoldSolenoid;
import edu.greenblitz.robotName.commands.climbing.getLifterReady;
import edu.greenblitz.robotName.commands.climbing.lifter.MoveLifterByJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteFromFeeder;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringMode;
import edu.greenblitz.robotName.commands.getNoteToSystem.TransferNote;
import edu.greenblitz.robotName.commands.intake.RunIntakeByPower;
import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.shooter.flyWheel.ShootSimulationNote;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.RotateByScoringMode;
import edu.greenblitz.robotName.commands.swerve.battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

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
		//Collect Note
		// mainJoystick.R1.whileTrue(new CollectNoteToScoringModeWithPivotForJoystick());
		mainJoystick.L1.whileTrue(new RotateByScoringMode());
		mainJoystick.POV_DOWN.whileTrue(new CollectNoteFromFeeder());

		//Reset Robot Angle
		mainJoystick.Y.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetChassisPose()));

		//note in roller
		mainJoystick.A.whileTrue(new ReleaseNoteFromRollerToTrap());
		mainJoystick.B.whileTrue(new MoveWristToAngle(WristConstants.PresetPositions.SCORE_TRAP));

		//Intake
		mainJoystick.R2.whileTrue(new RunIntakeByPower(0.5));

		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
	}

	public void shchoriButtons() {
		//ScoringMode
		secondJoystick.START.onTrue(new ToggleScoringMode());

		//Arm
		secondJoystick.BACK.onTrue(new InstantCommand(() -> Roller.getInstance().setObjectOut()));
		secondJoystick.A.onTrue(new ReleaseNoteFromRollerToAmp());
		secondJoystick.B.whileTrue(
				new MoveElbowAndWristWithRunFunnel(
						ElbowConstants.PresetPositions.SCORE,
						WristConstants.PresetPositions.SCORE
				)
		);
		secondJoystick.X.whileTrue(new MoveElbowAndWristToSafe());

		//FlyWheel Run
		secondJoystick.L1.whileTrue(new RunFlyWheelByVelocityUntilInterrupted(FlyWheelConstants.SHOOTING_VELOCITY, secondJoystick));

		//Pivot Poses
		secondJoystick.POV_UP.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.RIGHT_STAGE.ANGLE));
		secondJoystick.POV_LEFT.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.PODIUM.ANGLE));
		secondJoystick.POV_DOWN.whileTrue(new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING.ANGLE));
		secondJoystick.R2.whileTrue(new MovePivotByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_X));

		//Funnel
		secondJoystick.R1.whileTrue(new RunFunnelByJoystick(secondJoystick, SmartJoystick.Axis.RIGHT_Y).alongWith(new ShootSimulationNote()));


		//Fully collect
		secondJoystick.Y.whileTrue(new CollectNoteToScoringMode());
	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;

		//Wrist Control
		usedJoystick.POV_UP.whileTrue(new MoveWristByButton(true));
		usedJoystick.POV_DOWN.whileTrue(new MoveWristByButton(false));

		//Note-Roller Control
		usedJoystick.POV_RIGHT.whileTrue(new MoveNoteInRoller(true));
		usedJoystick.POV_LEFT.whileTrue(new MoveNoteInRoller(false));

		usedJoystick.Y.whileTrue(new ScoreToTrap());//do this after everything is in position and dont stop until note is inside

		//Scoring Mode Change
		usedJoystick.START.whileTrue(new SetScoringMode(ScoringMode.CLIMB)
				.andThen((new TransferNote()).raceWith(new WaitCommand(3.0))
						.andThen(new MoveElbowToAngle(Rotation2d.fromDegrees(-25)))
						.andThen(new MoveWristToAngle(Rotation2d.fromDegrees(270)))
				).alongWith(new getLifterReady()));
		//this first and long
		usedJoystick.BACK.whileTrue(new SetScoringMode(ScoringMode.AMP)
		);

		//Arm Control
		usedJoystick.R1.whileTrue(new MoveElbowByJoystick(usedJoystick, SmartJoystick.Axis.RIGHT_X));//move to above chassis and stop also this needs to push chain

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
		LED.getInstance().setDefaultCommand(new UpdateLEDStateDefaultCommand());
		FlyWheel.getInstance().setDefaultCommand(new FlyWheelDefaultCommand());
	}
}