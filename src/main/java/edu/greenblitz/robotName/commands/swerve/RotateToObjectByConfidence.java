package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.subsystems.Limelight.ObjectDetectionLimelight;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class RotateToObjectByConfidence extends ConditionalCommand {

	public RotateToObjectByConfidence() {
		super(new RotateToObject(), new WaitCommand(0), () -> ObjectDetectionLimelight.getInstance().getTargetConfidence());
	}
}
