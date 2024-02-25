package edu.greenblitz.robotName.subsystems.arm.roller.neoRoller;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoRollerConstants {

    public static final int MOTOR_ID = 9;

    public static int CURRENT_LIMIT = 40;

    public static final GBSparkMax.SparkMaxConfObject ROLLER_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
            .withCurrentLimit(CURRENT_LIMIT);
}