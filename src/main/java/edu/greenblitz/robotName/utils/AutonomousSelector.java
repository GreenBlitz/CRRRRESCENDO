package edu.greenblitz.robotName.utils;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class AutonomousSelector {
	static private AutonomousSelector instance; //i did some shenanigan with the static private hehe
	private SendableChooser<Command> chooser;
	
	private AutonomousSelector(){
		AutoBuilder.configureHolonomic(
				SwerveChassis.getInstance() :: getRobotPose,
				SwerveChassis.getInstance() :: resetChassisPose,
				SwerveChassis.getInstance() :: getRobotRelativeChassisSpeeds,
				SwerveChassis.getInstance() :: moveByRobotRelativeSpeeds,
				ChassisConstants.Autonomous.HOLONOMIC_PATH_FOLLOWER_CONFIG,
				() -> DriverStation.getAlliance().isPresent() && DriverStation.getAlliance().get() == DriverStation.Alliance.Red,
				SwerveChassis.getInstance()
		);

//      m_chooser.addOption("auto name", new PathPlannerAuto("auto name");
		chooser = AutoBuilder.buildAutoChooser();
		ShuffleboardTab tab = Shuffleboard.getTab("auto");
		tab.add("autonomous chooser", chooser);
	}

	public Command getChosenValue (){
		if(chooser.getSelected() == null){return new InstantCommand();}
		else {
			return chooser.getSelected();
		}
	}

	public static AutonomousSelector getInstance () {
		if (instance == null) {
			instance = new AutonomousSelector();
		}
		return instance;
	}

	public enum AutonomousPaths{

		NONE(new InstantCommand());


		public Command autonomousCommand;
		private AutonomousPaths (Command autonomousCommands){
			autonomousCommand = autonomousCommands;
		}
	}

//	private static Command getPathTCommand (String path){
//		return PathFollowerBuilder.getInstance().followPath(path);
//	}
}
