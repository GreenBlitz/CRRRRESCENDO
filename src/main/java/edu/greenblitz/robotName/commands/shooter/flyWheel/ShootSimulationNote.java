package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.DeferredCommand;
import org.littletonrobotics.junction.Logger;

import java.util.Set;

public class ShootSimulationNote extends DeferredCommand {

    public ShootSimulationNote() {
        super(() -> simulateNote(), Set.of());
    }

    private static GBCommand simulateNote() {
        return new GBCommand() {

            private Timer timer;

            private double duration;

            private Pose3d swervePose3D;

            @Override
            public void initialize() {
                timer = new Timer();
                timer.start();

                swervePose3D = SwerveChassis.getInstance().getRobotPose3d();

                duration = swervePose3D.getTranslation().getDistance(FieldConstants.MIDDLE_OF_SPEAKER_POSITION)
                        / FlyWheelConstants.SIMULATION_SHOOTING_SPEED_METERS_PER_SECOND;
            }

            @Override
            public void execute() {
                Logger.recordOutput(
                        "NoteVisualizer",
                        new Pose3d[] {
                                swervePose3D.interpolate(
                                        new Pose3d(FieldConstants.MIDDLE_OF_SPEAKER_POSITION, new Rotation3d()), timer.get() / duration
                                )
                        });
            }

            @Override
            public boolean isFinished() {
                return timer.hasElapsed(duration);
            }

            @Override
            public void end(boolean interrupted) {
                Logger.recordOutput("NoteVisualizer", new Pose3d[] {});
            }

        };
    }
}
