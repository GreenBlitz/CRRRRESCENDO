package edu.greenblitz.robotName.commands.arm;

import edu.greenblitz.robotName.commands.shooter.funnel.RunFunnelByVelocity;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class MoveElbowAndWristWithRunFunnel extends ParallelDeadlineGroup {
	public MoveElbowAndWristWithRunFunnel(Rotation2d elbowAngle, Rotation2d wristAngle) {
		super(
				new MoveElbowAndWrist(elbowAngle, wristAngle),
				new ConditionalCommand(
						new InstantCommand(),
						new RunFunnelByVelocity(-30),
						() -> Funnel.getInstance().isObjectIn()
				)
		);
	}
	
	public MoveElbowAndWristWithRunFunnel(ElbowConstants.PresetPositions elbowAngle, WristConstants.PresetPositions wristAngle) {
		super(
				new MoveElbowAndWrist(elbowAngle, wristAngle),
				new ConditionalCommand(
						new InstantCommand(),
						new RunFunnelByVelocity(-45),
						() -> Funnel.getInstance().isObjectIn()
				)
		);
	}
}
