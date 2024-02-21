package edu.greenblitz.robotName.commands.shooter.flyWheel;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.subsystems.shooter.FlyWheel.FlyWheelConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;

public class RunFlyWheelBySpeakerInterpolationVelocity extends RunFlyWheelByVelocity {

    public RunFlyWheelBySpeakerInterpolationVelocity() {
        super(0);
    }

    @Override
    public void initialize() {
        changeVelocity(
                FlyWheelConstants.SHOOTING_VELOCITY_INTERPOLATOR.get(
                        SwerveChassis.getInstance().getRobotPose2d().getTranslation()
                                .getDistance(FieldConstants.MIDDLE_OF_SPEAKER_POSITION.toTranslation2d())
                )
        );
        super.initialize();
    }
}