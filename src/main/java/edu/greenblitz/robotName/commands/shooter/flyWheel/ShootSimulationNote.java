package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.shooter.pivot.PivotConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.wpilibj.Timer;
import org.littletonrobotics.junction.Logger;


public class ShootSimulationNote extends GBCommand {

    private Timer timer;

    private double duration;

    private Pose3d shooterPose3D;

    private Rotation2d chassisAngle;

    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();

        shooterPose3D = SwerveChassis.getInstance().getRobotPose3d().plus(
                new Transform3d(PivotConstants.ROBOT_RELATIVE_PIVOT_POSITION, new Rotation3d())
        );

        duration = FlyWheelConstants.SIMULATION_SHOOTING_TIME_SECOND;
        chassisAngle = SwerveChassis.getInstance().getChassisAngle();
    }

    @Override
    public void execute() {
        Rotation2d shooterAngle = Pivot.getInstance().getAngle();

        Translation3d notePosition = new Translation3d(
                chassisAngle.getCos() * shooterAngle.getCos(),
                chassisAngle.getSin() * shooterAngle.getCos(),
                shooterAngle.getSin()
        ).times(timer.get() * FlyWheelConstants.SIMULATION_SHOOTING_SPEED_METERS_PER_SECOND);

        Logger.recordOutput(
            "NoteVisualizer",
            new Pose3d[]{
                    new Pose3d(
                            notePosition.plus(shooterPose3D.getTranslation()),
                            Pivot.getInstance().getSimulationPivotPosition3d().getRotation().plus(shooterPose3D.getRotation())
                    )
            }
        );
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(duration);
    }

    @Override
    public void end(boolean interrupted) {
        Logger.recordOutput("NoteVisualizer", new Pose3d[]{});
    }
}
