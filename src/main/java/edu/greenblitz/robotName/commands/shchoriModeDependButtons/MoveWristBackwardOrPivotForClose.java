package edu.greenblitz.robotName.commands.shchoriModeDependButtons;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.wrist.MoveWristByButton;
import edu.greenblitz.robotName.commands.shooter.pivot.MovePivotToAngle;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;

public class MoveWristBackwardOrPivotForClose extends ConditionalCommand {

	public MoveWristBackwardOrPivotForClose(){
		super(
				new MovePivotToAngle(PivotConstants.PresetPositions.CLOSE_SHOOTING),
				new MoveWristByButton(false),
				ScoringModeSelector::isSpeakerMode
		);
	}

}
