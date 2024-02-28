package edu.greenblitz.robotName.subsystems.lifter;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;

public class LifterConstants {

    public static final Translation3d ROBOT_RELATIVE_LIFTER_POSITION = new Translation3d(-0.24, 0, 0.35);

    public static final Rotation3d ROBOT_RELATIVE_LIFTER_ROTATION = new Rotation3d(0, Math.PI, -Math.PI / 2);

    public static final Rotation2d ENCODER_POSITION_WHEN_RESET = Rotation2d.fromRotations(0);

    public static final double POWER_TO_OPEN_SOLENOID = 0;

    public static final double POWER_TO_CLOSE_SOLENOID = 1;

    public static final double POWER_TO_HOLD_SOLENOID = 0.2;

    public static final double SECONDS_TO_CLOSE_SOLENOID = 0.7;

    public static final Rotation2d TOLERANCE = Rotation2d.fromDegrees(4);

    public static final Rotation2d LIFTER_RETRACTED_POSITION = Rotation2d.fromDegrees(0);

    public static final Rotation2d LIFTER_EXTENDED_POSITION = Rotation2d.fromDegrees(135);

    public static final Rotation2d FORWARD_LIMIT = Rotation2d.fromDegrees(180);

    public static final Rotation2d BACKWARD_LIMIT = Rotation2d.fromDegrees(-10);

    public static final Rotation2d STARTING_ANGLE = Rotation2d.fromDegrees(0);

    public static final double LENGTH_OF_LIFTER = 0.45;

    public static final double LIFTER_MASS_KG = 7;

    public static final CANSparkMax.IdleMode IDLE_MODE = CANSparkMax.IdleMode.kBrake;
}