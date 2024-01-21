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
//      chooser.addOption("auto name", new PathPlannerAuto("auto name");
		chooser = AutoBuilder.buildAutoChooser();
		ShuffleboardTab tab = Shuffleboard.getTab("auto");
		tab.add("autonomous chooser", AutoBuilder.buildAutoChooser());
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

}
