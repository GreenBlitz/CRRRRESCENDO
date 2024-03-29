package edu.greenblitz.robotName.subsystems.arm.roller.neoRoller;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;

public class NeoRollerConstants {

    public static final int MOTOR_ID = 22;

    public static final double NOTE_IN_CURRENT = 18;

    public static int CURRENT_LIMIT = 40;

    public static double GEAR_RATIO = 1 / 6.0;

    public static boolean IS_INVERTED = true;

    public static PIDObject PID = new PIDObject().withKp(0.65).withKd(0).withMaxPower(1).withFF(0.4);

    public static final GBSparkMax.SparkMaxConfObject ROLLER_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
            .withCurrentLimit(CURRENT_LIMIT)
            .withPositionConversionFactor(GEAR_RATIO)
            .withPID(PID)
            .withInverted(IS_INVERTED);
}