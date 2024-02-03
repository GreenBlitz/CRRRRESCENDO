package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.utils.GetObjectAngleRelativeToRobot;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class RotateToObject extends SwerveCommand{
	private Rotation2d poseAngle;
	GetObjectAngleRelativeToRobot objectPose;
	Command rotateCommand;

	public RotateToObject() {
		poseAngle = new Rotation2d(objectPose.getAsDouble());
		rotateCommand = new RotateToAngle(poseAngle);
	}

	@Override
	public void initialize() {
		rotateCommand.schedule();
	}
}
