package edu.greenblitz.robotName.utils;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.greenblitz.robotName.commands.shooter.ShootToSpeakerFromClose;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import org.littletonrobotics.junction.Logger;

public class AutonomousSelector {
	
	private static AutonomousSelector instance;
	
	private final SendableChooser<Command> chooser;
	
	private AutonomousSelector() {
		chooser = AutoBuilder.buildAutoChooser();
		chooser.addOption("shoot from close", new ShootToSpeakerFromClose());
		ShuffleboardTab tab = Shuffleboard.getTab("auto");
		tab.add("autonomous chooser", chooser);
	}
	
	public Command getChosenValue() {
		Logger.recordMetadata("autonomous choose", chooser.getSelected().getName());
		return chooser.getSelected();
	}
	
	public static AutonomousSelector getInstance() {
		if (instance == null) {
			instance = new AutonomousSelector();
		}
		return instance;
	}
}
