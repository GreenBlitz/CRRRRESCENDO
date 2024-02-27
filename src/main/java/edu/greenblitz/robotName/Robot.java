package edu.greenblitz.robotName;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.pathfinding.LocalADStar;
import com.pathplanner.lib.pathfinding.Pathfinding;
import edu.greenblitz.robotName.commands.intake.CollectNoteFromGround;
import edu.greenblitz.robotName.commands.intake.NoteFromIntakeToShooter;
import edu.greenblitz.robotName.commands.intake.NoteToIntake;
import edu.greenblitz.robotName.commands.intake.NoteToShooter;
import edu.greenblitz.robotName.commands.shooter.MoveShooterToAngle;
import edu.greenblitz.robotName.commands.shooter.ShootFromInFunnel;
import edu.greenblitz.robotName.commands.shooter.ShootToSpeakerFromClose;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.commands.shooter.shootingState.GoToShootingStateAndShoot;
import edu.greenblitz.robotName.shootingStateService.ShootingPositionConstants;
import edu.greenblitz.robotName.subsystems.Dashboard;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.intake.IntakeConstants;
import edu.greenblitz.robotName.subsystems.lifter.Lifter;
import edu.greenblitz.robotName.subsystems.limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.AutonomousSelector;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.greenblitz.robotName.utils.RoborioUtils;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

public class Robot extends LoggedRobot {

    public enum RobotType {
        SYNCOPA,
        SIMULATION,
        PEGA_SWERVE,
        REPLAY
    }

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
		initializeAutonomousBuilder();
		initializeSubsystems();
		SwerveChassis.getInstance().resetAngularEncodersByAbsoluteEncoder();
		Dashboard.getInstance();
		OI.init();
		Pivot.getInstance().resetAngle(PivotConstants.PresetPositions.STARTING.ANGLE);
	}

    public void initializeSubsystems() {
        AutonomousSelector.getInstance();
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
    }

	@Override
	public void teleopInit() {
		Dashboard.getInstance().activateDriversDashboard();
	}

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        RoborioUtils.updateCurrentCycleTime();

    }

    private void initializeAutonomousBuilder() {
        NamedCommands.registerCommand("shoot", new ShootFromInFunnel());
        NamedCommands.registerCommand("close shoot", new ShootToSpeakerFromClose());
        NamedCommands.registerCommand("grip",(new CollectNoteFromGround()));
        AutoBuilder.configureHolonomic(
                SwerveChassis.getInstance()::getRobotPose2d,
                SwerveChassis.getInstance()::resetChassisPosition,
                SwerveChassis.getInstance()::getRobotRelativeChassisSpeeds,
                SwerveChassis.getInstance()::moveByRobotRelativeSpeeds,
                ChassisConstants.PATH_FOLLOWER_CONFIG,
                () -> FMSUtils.getAlliance() == DriverStation.Alliance.Red,
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
            case SYNCOPA:
                try {
                    Logger.addDataReceiver(new WPILOGWriter(RobotConstants.USB_LOG_PATH));
                    System.out.println("initialized Logger, USB");
                } catch (Exception e) {
                    Logger.end();
                    Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SAFE_ROBORIO_LOG_PATH));
                    System.out.println("initialized Logger, roborio");
                }
                Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SAFE_ROBORIO_LOG_PATH));
				Logger.addDataReceiver(new NT4Publisher());
                break;
            // Replaying a log, set up replay source
            case REPLAY:
                setUseTiming(false); // Run as fast as possible
                String logPath = LogFileUtil.findReplayLog();
                Logger.setReplaySource(new WPILOGReader(logPath));
                Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_simulation")));
                break;
            case SIMULATION:
            default:
                Logger.addDataReceiver(new NT4Publisher());
                Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SIMULATION_LOG_PATH));
                break;
        }
        Logger.start();
    }

    @Override
    public void autonomousInit() {
        AutonomousSelector.getInstance().getChosenValue().schedule();
    }
}