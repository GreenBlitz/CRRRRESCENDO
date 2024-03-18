package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.Field;
import edu.greenblitz.robotName.commands.arm.roller.ReleaseNoteFromRollerToAmp;
import edu.greenblitz.robotName.subsystems.arm.elbow.Elbow;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.arm.wrist.Wrist;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.AllianceUtilities;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.Pose2d;

public class ReleaseNoteFromArmOnReady extends GBCommand {

    private boolean isElbowReady;

    private boolean isWristReady;

    private boolean isRobotInPlace;

    private boolean isNoteInRoller;

    private Pose2d ampScoringPose;


    @Override
    public void initialize() {
        super.initialize();
        ampScoringPose = AllianceUtilities.isBlueAlliance() ? Field.ScoringPositions.BLUE_AMP_SCORE_POSITION : Field.ScoringPositions.RED_AMP_SCORE_POSITION;
    }

    @Override
    public void execute() {
        isElbowReady = Elbow.getInstance().isAtAngle(ElbowConstants.PresetPositions.SCORE.ANGLE);
        isWristReady = Wrist.getInstance().isAtAngle(WristConstants.PresetPositions.SCORE.ANGLE);
        isRobotInPlace = SwerveChassis.getInstance().isAtPose(ampScoringPose);
        isNoteInRoller = Roller.getInstance().isObjectIn();
    }

    @Override
    public boolean isFinished() {
        return isElbowReady &&  isWristReady && isRobotInPlace && isNoteInRoller;
    }

    @Override
    public void end(boolean interrupted) {
        new ReleaseNoteFromRollerToAmp().schedule();
    }
}
