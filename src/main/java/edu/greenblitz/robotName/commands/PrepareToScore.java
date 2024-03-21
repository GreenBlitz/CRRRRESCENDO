package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.arm.MoveElbowAndWristWithRunFunnel;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByPowerConstant;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityConstant;
import edu.greenblitz.robotName.commands.shooter.flyWheel.RunFlyWheelByVelocityUntilInterrupted;
import edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle.MoveByJoystickAndRotateToAmp;
import edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle.MoveByJoystickAndRotateToSpeaker;
import edu.greenblitz.robotName.commands.swerve.MoveyJoystickWithAngle.MoveByJoystickAndRotateToStage;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.subsystems.arm.wrist.WristConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.wpi.first.wpilibj2.command.*;

public class PrepareToScore extends ProxyCommand {

    public PrepareToScore() {
        super(PrepareToScore::getCommand);
    }

    public static Command getCommand() {
        if (ScoringModeSelector.isSpeakerMode()) {
            return new ParallelCommandGroup(
                    new MoveByJoystickAndRotateToSpeaker(ChassisConstants.DRIVE_MODE),
                    new RunFlyWheelByVelocityUntilInterrupted(FlyWheelConstants.SHOOTING_VELOCITY)
            );
        } else if (ScoringModeSelector.isAmpMode()){
            return new ParallelCommandGroup(
                    new MoveByJoystickAndRotateToAmp(ChassisConstants.DRIVE_MODE),
                    new SequentialCommandGroup(
                            new isRobotAtAngle(FieldConstants.BLUE_AMP_ANGLE),
                            new MoveElbowAndWristWithRunFunnel(
                                    ElbowConstants.PresetPositions.SCORE,
                                    WristConstants.PresetPositions.SCORE
                            )
                    )

            );
        } else if (ScoringModeSelector.isClimbMode()) {
            return new MoveByJoystickAndRotateToStage();
        }
        else return new InstantCommand();
    }

}
