package edu.greenblitz.robotName;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathfindHolonomic;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.pathfinding.LocalADStar;
import com.pathplanner.lib.pathfinding.Pathfinder;
import com.pathplanner.lib.pathfinding.Pathfinding;
import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.FMSUtils;
import edu.greenblitz.robotName.utils.LocalADStarAK;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import java.util.List;

public class Robot extends LoggedRobot {

    public enum RobotType {
        ROBOT_NAME,
        SIMULATION,
        REPLAY
    }

    @Override
    public void robotInit() {
        Pathfinding.setPathfinder(new LocalADStar());
        initializeLogger();
        CommandScheduler.getInstance().enable();

        initializeAutoBuilder();

        SwerveChassis.init();
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(MoveByJoysticks.DriveMode.NORMAL));
        SwerveChassis.getInstance().resetAllEncoders();

        OI.getInstance();
    }
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        SmartDashboard.putNumber("x", SwerveChassis.getInstance().getRobotPose().getX());
        SmartDashboard.putNumber("y", SwerveChassis.getInstance().getRobotPose().getY());
        SmartDashboard.putNumber("r", SwerveChassis.getInstance().getRobotPose().getRotation().getDegrees());
    }

    private void initializeAutoBuilder (){
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
                Logger.addDataReceiver(new WPILOGWriter(RobotConstants.ROBORIO_LOG_PATH));
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
