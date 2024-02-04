package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.utils.GetObjectAngleRelativeToRobot;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class RotateToObject extends RotateToAngle {

	public RotateToObject() {
		super(new Rotation2d(GetObjectAngleRelativeToRobot.getObjectAngle()));
	}}
