package edu.greenblitz.robotName;


import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.pathfinding.LocalADStar;
import com.pathplanner.lib.pathfinding.Pathfinding;
import edu.greenblitz.robotName.subsystems.Intake.Intake;
import edu.greenblitz.robotName.subsystems.Lifter.Lifter;
import edu.greenblitz.robotName.subsystems.ArmShooterMechanism.ArmShooterMechanism;
import edu.greenblitz.robotName.subsystems.Limelight.ObjectDetectionLimelight;
import edu.greenblitz.robotName.subsystems.shooter.Pivot.Pivot;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheel;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.wpi.first.wpilibj.DriverStation;
import edu.greenblitz.robotName.commands.swerve.Battery.BatteryLimiter;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.Dashboard;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.RoborioUtils;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

public class Robot extends LoggedRobot {

    public enum RobotType {
        ROBOT_NAME,
        SIMULATION,
        REPLAY
    }

    @Override
    public void robotInit() {
        Pathfinding.setPathfinder(new LocalADStar());
        CommandScheduler.getInstance().enable();
        initializeLogger();
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(MoveByJoysticks.DriveMode.NORMAL));
        Battery.getInstance().setDefaultCommand(new BatteryLimiter());
        initializeSubsystems();
        SwerveChassis.getInstance().resetAllEncoders();
        initializeAutonomousBuilder();
        OI.getInstance();
    }

    public void initializeSubsystems() {
        MultiLimelight.init();
        ObjectDetectionLimelight.init();
//        SwerveChassis.init();
//
//        Pivot.init();
//        Funnel.init();
//        FlyWheel.init();
//
//        Elbow.init();
//        Wrist.init();
//        Roller.init();
//        ArmShooterMechanism.init();
//
//        Lifter.init();
//        Intake.init();
    }

    @Override
    public void teleopInit() {
        Dashboard.getInstance().activateDriversDashboard();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        RoborioUtils.updateCurrentCycleTime();
        ArmShooterMechanism.getInstance().periodic();
    }

    private void initializeAutonomousBuilder() {
        AutoBuilder.configureHolonomic(
                SwerveChassis.getInstance()::getRobotPose,
                SwerveChassis.getInstance()::resetChassisPose,
                SwerveChassis.getInstance()::getRobotRelativeChassisSpeeds,
                SwerveChassis.getInstance()::moveByRobotRelativeSpeeds,
                ChassisConstants.PATH_FOLLOWER_CONFIG,
                () -> FMSUtils.getAlliance() == DriverStation.Alliance.Red,
                SwerveChassis.getInstance()
        );
    }

    private void initializeLogger(){
        NetworkTableInstance.getDefault()
                .getStructTopic("RobotPose", Pose2d.struct).publish();

        NetworkTableInstance.getDefault()
                .getStructTopic("MechanismPoses", Pose3d.struct).publish();

        switch (RobotConstants.ROBOT_TYPE) {
            // Running on a real robot, log to a USB stick
            case ROBOT_NAME:
                try {
                    Logger.addDataReceiver(new WPILOGWriter(RobotConstants.USB_LOG_PATH));
                    System.out.println("initialized Logger, USB");
                } catch (Exception e) {
                    Logger.end();
                    Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SAFE_ROBORIO_LOG_PATH));
                    System.out.println("initialized Logger, roborio");
                }
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


}
