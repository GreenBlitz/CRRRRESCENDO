package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.LED.UpdateLEDStateDefaultCommand;
import edu.greenblitz.robotName.commands.PrepareToScore;
import edu.greenblitz.robotName.commands.ScoreOnReady;
import edu.greenblitz.robotName.commands.arm.elbow.ElbowDefaultCommand;
import edu.greenblitz.robotName.commands.arm.elbow.MoveElbowByJoystick;
import edu.greenblitz.robotName.commands.arm.roller.MoveNoteInRoller;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToTrap;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristToAngle;
import edu.greenblitz.robotName.commands.arm.wrist.WristDefaultCommand;
import edu.greenblitz.robotName.commands.climbing.CloseAndThenHoldSolenoid;
import edu.greenblitz.robotName.commands.climbing.lifter.MoveLifterByJoystick;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteFromFeeder;
import edu.greenblitz.robotName.commands.getNoteToSystem.CollectNoteToScoringModeWithPivotForJoystick;
import edu.greenblitz.robotName.commands.intake.RunIntakeByPower;
import edu.greenblitz.robotName.commands.shchoriModeDependButtons.ClimbOrCollectFromFeeder;
import edu.greenblitz.robotName.commands.shchoriModeDependButtons.GetReadyForClimbOrArmToSafe;
import edu.greenblitz.robotName.commands.shchoriModeDependButtons.MoveWristBackwardOrPivotForClose;
import edu.greenblitz.robotName.commands.shchoriModeDependButtons.MoveWristForwardOrPivotForPodium;
import edu.greenblitz.robotName.commands.shooter.flyWheel.FlyWheelDefaultCommand;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotByJoystick;
import edu.greenblitz.robotName.commands.shooter.pivot.PivotDefaultCommand;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle.MoveByJoystickAndRotateToStage;
import edu.greenblitz.robotName.commands.switchMode.SetScoringMode;
import edu.greenblitz.robotName.commands.switchMode.ToggleScoringMode;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;


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
		SmartJoystick usedJoystick = mainJoystick;

		//Collect Note
		usedJoystick.R1.whileTrue(new CollectNoteToScoringModeWithPivotForJoystick());

		//Auto Aim For Speaker Or Amp
		usedJoystick.L2.whileTrue(new PrepareToScore());

		//Shoot
		usedJoystick.L2_HARD.whileTrue(new ScoreOnReady());

		usedJoystick.R2.whileTrue(new MoveByJoystickAndRotateToStage());

		//Reset Robot Pose
		usedJoystick.Y.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().resetPoseByVision()));
		usedJoystick.POV_DOWN.onTrue(new InstantCommand(() -> SwerveChassis.getInstance().hardResetPoseByVision()));

		//Climbing
		usedJoystick.A.whileTrue(new ReleaseNoteFromRollerToTrap());//Release note from roller and take wrist back
		usedJoystick.B.whileTrue(new MoveWristToAngle(WristConstants.PresetPositions.SCORE_TRAP));

		//disable and enable the limelight led
		usedJoystick.BACK.onTrue(new InstantCommand(() -> MultiLimelight.getInstance().setLimelightsLedOff()));
		usedJoystick.START.onTrue(new InstantCommand(() -> MultiLimelight.getInstance().setLimelightsLedOn()));

		SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(ChassisConstants.DRIVE_MODE));
	}

	public void shchoriButtons() {
		SmartJoystick usedJoystick = secondJoystick;

		//Scoring Mode Change - With Idle mode sets
		usedJoystick.START.onTrue(new ToggleScoringMode());//Doing Transfer Also
		usedJoystick.BACK.onTrue(new SetScoringMode(ScoringMode.CLIMB));

		//Speaker Mode -> Move Pivot to Angle : CLIMB, AMP -> Hand Control Wrist
		usedJoystick.POV_UP.whileTrue(new MoveWristForwardOrPivotForPodium());
		usedJoystick.POV_DOWN.whileTrue(new MoveWristBackwardOrPivotForClose());

		//Note-Roller Hand Control
		usedJoystick.POV_RIGHT.whileTrue(new MoveNoteInRoller(true));
		usedJoystick.POV_LEFT.whileTrue(new MoveNoteInRoller(false));

		//Hand Control Arm
		usedJoystick.R2.whileTrue(new MoveElbowByJoystick(usedJoystick, SmartJoystick.Axis.RIGHT_TRIGGER, true));
		usedJoystick.L2.whileTrue(new MoveElbowByJoystick(usedJoystick, SmartJoystick.Axis.LEFT_TRIGGER, false));

		//No Object In Arm
		usedJoystick.X.onTrue(new InstantCommand(() -> Roller.getInstance().setObjectOut()));

		//Intake Reverse Roll
		usedJoystick.B.whileTrue(new RunIntakeByPower(-0.5));

		//Climb Mode -> Climbing : AMP or SPEAKER -> Collect Note From Feeder
		usedJoystick.Y.whileTrue(new ClimbOrCollectFromFeeder());

		//Climb Mode -> Lifter Up, Note To Arm, Move Arm a bit Up : AMP or SPEAKER -> Move Arm To Safe
		usedJoystick.A.whileTrue(new GetReadyForClimbOrArmToSafe()
						.andThen(new RunCommand(() -> secondJoystick.rumble(true, 0.8))
								.raceWith(new WaitCommand(2))
						)
						.andThen(new RunCommand(() -> secondJoystick.rumble(true, 0))
								.raceWith(new WaitCommand(2))
						)
		);

		//Run FlyWheel For Shooting
		usedJoystick.L1.whileTrue(new RunFlyWheelByVelocityUntilInterrupted(FlyWheelConstants.SHOOTING_VELOCITY, usedJoystick));

		//Funnel Joystick Control
		usedJoystick.R1.whileTrue(new RunFunnelByJoystick(usedJoystick, SmartJoystick.Axis.RIGHT_Y));

		//Hand Control On Lifter
		usedJoystick.LEFT_Y_AXIS.whileTrue(
				new ConditionalCommand(
						new SequentialCommandGroup(
								new CloseAndThenHoldSolenoid(),
								new MoveLifterByJoystick(usedJoystick)
						),
						new MoveLifterByJoystick(usedJoystick),
						() -> (usedJoystick.getAxisValue(SmartJoystick.Axis.LEFT_Y) > 0)
				)
		);
	}

	public void thirdJoystickButtons() {
		SmartJoystick usedJoystick = thirdJoystick;
	}

	public void fourthJoystickButtons() {
		SmartJoystick usedJoystick = fourthJoystick;
		usedJoystick.B.whileTrue(new CollectNoteFromFeeder());
		usedJoystick.R1.onTrue(new InstantCommand(() -> SmartDashboard.putNumber("Pivot angleee", Pivot.getInstance().getAngle().getDegrees())));
		usedJoystick.Y.whileTrue(new MovePivotByJoystick(usedJoystick, SmartJoystick.Axis.LEFT_Y));
	}

	public void initializeDefaultCommands() {
//		Battery.getInstance().setDefaultCommand(new BatteryLimiter());
		Elbow.getInstance().setDefaultCommand(new ElbowDefaultCommand());
		Wrist.getInstance().setDefaultCommand(new WristDefaultCommand());
		Pivot.getInstance().setDefaultCommand(new PivotDefaultCommand());
		LED.getInstance().setDefaultCommand(new UpdateLEDStateDefaultCommand());
		FlyWheel.getInstance().setDefaultCommand(new FlyWheelDefaultCommand());
	}
}