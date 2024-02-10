package edu.greenblitz.robotName.subsystems.Intake.neoIntake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoIntakeConstants {

    public static final int INTAKE_ID = 12;

    public static final int ENTRANCE_BEAM_BREAKER_CHANNEL = 0;

    public static final int EXIT_BEAM_BREAKER_CHANNEL = 1;

    public static final double ENTRANCE_DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.1;

    public static final double EXIT_DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.1;

    public static final SparkMaxLimitSwitch.Type ENTRANCE_BEAM_BREAKER_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed ;

    public static final SparkMaxLimitSwitch.Type EXIT_BEAM_BREAKER_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed ;

    public static final boolean IS_ENTRANCE_BEAM_BREAKER_ACTIVE = false;
    public static final boolean IS_EXIT_BEAM_BREAKER_ACTIVE = false;

    public static final int CURRENT_LIMIT = 40;

    public static final GBSparkMax.SparkMaxConfObject INTAKE_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
            .withCurrentLimit(CURRENT_LIMIT);

}
