package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;

public abstract class GBSubsystem implements Subsystem {
	
	
	public GBSubsystem() {
		CommandScheduler.getInstance().registerSubsystem(this);
	}
	
	@Override
	public void periodic() {
		Subsystem.super.periodic();
		if(getCurrentCommand() != null) {
			SmartDashboard.putString(getClass().getName(), getCurrentCommand().getName());
		}
	}
}
