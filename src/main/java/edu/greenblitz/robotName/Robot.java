package edu.greenblitz.robotName;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.pathfinding.LocalADStar;
import com.pathplanner.lib.pathfinding.Pathfinding;
import edu.greenblitz.robotName.commands.RumbleRomy;
import edu.greenblitz.robotName.commands.auto.InterpolationForAuto;
import edu.greenblitz.robotName.commands.auto.NoteFromIntakeToShooterForAuto;
import edu.greenblitz.robotName.commands.auto.RotateToSpeakerForAuto;
import edu.greenblitz.robotName.commands.intake.*;
import edu.greenblitz.robotName.commands.limelight.ActivateLimelight;
import edu.greenblitz.robotName.commands.limelight.DisableLimelight;
import edu.greenblitz.robotName.commands.shooter.PushNoteToFlyWheel;
import edu.greenblitz.robotName.commands.shooter.ShootToSpeakerFromClose;
import edu.greenblitz.robotName.subsystems.Dashboard;
import edu.greenblitz.robotName.subsystems.LED.LED;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.climber.lifter.Lifter;
import edu.greenblitz.robotName.subsystems.limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.AllianceUtilities;
import edu.greenblitz.robotName.utils.AutonomousSelector;
import edu.greenblitz.robotName.utils.RoborioUtils;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import static edu.greenblitz.robotName.Field.ScoringPositions.*;

public class Robot extends LoggedRobot {

	private Command autonomousCommand;

	public static RobotType getRobotType() {
		RobotType robotType = RobotConstants.ROBOT_TYPE;
		if (isSimulation()) {
			if (robotType.equals(RobotType.REPLAY)) {
				return RobotType.REPLAY;
			}
			return RobotType.SIMULATION;
		} else {
			if (robotType.equals(RobotType.SYNCOPA)) {
				return RobotType.SYNCOPA;
			}
			return RobotType.PEGA_SWERVE;
		}
	}

	@Override
	public void robotInit() {
		Pathfinding.setPathfinder(new LocalADStar());
		CommandScheduler.getInstance().enable();
		initializeLogger();
		initializeSubsystems();
		SwerveChassis.getInstance().resetAngularEncodersByAbsoluteEncoder();
//		Dashboard.getInstance();
		initializeAutonomousBuilder();
		AutonomousSelector.getInstance();
		Pivot.getInstance().resetAngle(PivotConstants.PresetPositions.STARTING.ANGLE);
		Elbow.getInstance().resetAngle(ElbowConstants.MINIMUM_ANGLE);
		OI.init();
	}

	public void initializeSubsystems() {
		ScoringModeSelector.init();
		MultiLimelight.init();
		SwerveChassis.init();

		Pivot.init();
		Funnel.init();
		FlyWheel.init();

		Elbow.init();
		Wrist.init();
		Roller.init();

		Lifter.init();
		Intake.init();

//		LED.init();
	}


	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
//		Dashboard.getInstance().activateDriversDashboard();
		new RumbleRomy(OI.getInstance().getMainJoystick(),OI.getInstance().getSecondJoystick()).schedule();
	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		RoborioUtils.updateCurrentCycleTime();
		SmartDashboard.putString("mode", ScoringModeSelector.getScoringMode().name());
		SmartDashboard.putBoolean("is object in arm", Roller.getInstance().isObjectIn());
		Logger.recordOutput("ScoringMode", ScoringModeSelector.getScoringMode().name());
		Logger.recordOutput("RED_MID_CLIMB_POSITION", RED_MID_CLIMB_POSITION);
		Logger.recordOutput("RED_UPPER_CLIMB_POSITION", RED_UPPER_CLIMB_POSITION);
		Logger.recordOutput("RED_LOWER_CLIMB_POSITION", RED_LOWER_CLIMB_POSITION);
		Logger.recordOutput("BLUE_LOWER_CLIMB_POSITION", BLUE_LOWER_CLIMB_POSITION);
		Logger.recordOutput("BLUE_MID_CLIMB_POSITION", BLUE_MID_CLIMB_POSITION);
		Logger.recordOutput("BLUE_UPPER_CLIMB_POSITION", BLUE_UPPER_CLIMB_POSITION);
	}

	private void initializeAutonomousBuilder() {
		NamedCommands.registerCommand("push note to flyWheel", new PushNoteToFlyWheel().raceWith(new WaitCommand(3)));
		NamedCommands.registerCommand("rotate to speaker", new RotateToSpeakerForAuto());
		NamedCommands.registerCommand("note to intake", new NoteToIntake());
		NamedCommands.registerCommand("note from intake to shooter", new NoteFromIntakeToShooterForAuto());
		NamedCommands.registerCommand("close shoot start", new ShootToSpeakerFromClose());
		NamedCommands.registerCommand("interpolation", new InterpolationForAuto());
		NamedCommands.registerCommand("disable limelight", new DisableLimelight());
		NamedCommands.registerCommand("activate limelight", new ActivateLimelight());
		AutoBuilder.configureHolonomic(
				SwerveChassis.getInstance()::getRobotPose2d,
				(pose) -> SwerveChassis.getInstance().resetChassisPose(AllianceUtilities.AlliancePose2d.fromBlueAlliancePose(pose)),
				SwerveChassis.getInstance()::getChassisSpeeds,
				SwerveChassis.getInstance()::moveByRobotRelativeSpeeds,
				ChassisConstants.PATH_FOLLOWER_CONFIG,
				() -> !AllianceUtilities.isBlueAlliance(),
				SwerveChassis.getInstance()
		);
	}

	private void initializeLogger() {
		NetworkTableInstance.getDefault()
				.getStructTopic("RobotPose", Pose2d.struct).publish();

		NetworkTableInstance.getDefault()
				.getStructTopic("MechanismPoses", Pose3d.struct).publish();
		switch (getRobotType()) {
			// Running on a real robot, log to a USB stick
			case SYNCOPA -> {
				try {
					Logger.addDataReceiver(new WPILOGWriter(RobotConstants.USB_LOG_PATH));
					System.out.println("initialized Logger, USB");
				} catch (Exception e) {
					Logger.end();
					Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SAFE_ROBORIO_LOG_PATH));
					System.out.println("initialized Logger, roborio");
				}
				Logger.addDataReceiver(new NT4Publisher());
			}
			// Replaying a log, set up replay source
			case REPLAY -> {
				setUseTiming(false); // Run as fast as possible
				String logPath = LogFileUtil.findReplayLog();
				Logger.setReplaySource(new WPILOGReader(logPath));
				Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_simulation")));
			}
			default -> {
				Logger.addDataReceiver(new NT4Publisher());
				Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SIMULATION_LOG_PATH));
			}
		}
		Logger.start();
	}

	@Override
	public void autonomousInit() {
		SwerveChassis.getInstance().resetAngularEncodersByAbsoluteEncoder();
		autonomousCommand = AutonomousSelector.getInstance().getChosenValue();
		if (autonomousCommand != null){
			autonomousCommand.schedule();
		}
	}


	public enum RobotType {
		SYNCOPA,
		SIMULATION,
		PEGA_SWERVE,
		REPLAY
	}
}