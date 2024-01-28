package edu.greenblitz.robotName.subsystems.Intake.neoIntake;

import com.revrobotics.SparkMaxLimitSwitch;

public class NeoIntakeConstants {

    public static final int INTAKE_ID = 0;

    public static final int ENTRANCE_BEAM_BREAKER_CHANNEL = 0;

    public static final int EXIT_BEAM_BREAKER_CHANNEL = 0;

    public static final double ENTRANCE_DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 1.0;

    public static final double EXIT_DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 1.0;

    public static final SparkMaxLimitSwitch.Type ENTRANCE_BEAM_BREAKER_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed ;

    public static final SparkMaxLimitSwitch.Type EXIT_BEAM_BREAKER_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed ;

}
