package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class AutonomousSelector {
	static private AutonomousSelector instance; //i did some shenanigan with the static private hehe
	private SendableChooser<AutonomousPaths> chooser = new SendableChooser<>();
	
	private AutonomousSelector(){
		//         m_chooser.addOption(auto name, path name );

		ShuffleboardTab tab = Shuffleboard.getTab("auto");
		tab.add("autonomous chooser", chooser);
	}

	public AutonomousPaths getChosenValue (){
		if(chooser.getSelected() == null){return AutonomousPaths.NONE;}
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
