package edu.greenblitz.robotName.subsystems.Shooter.Funnel.NeoFunnel;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class NeoFunnelConstants {
    public static final int FUNNEL_ID = 0;

    public static final SparkMaxLimitSwitch.Type SWITCH_TYPE = SparkMaxLimitSwitch.Type.kNormallyClosed;

    public static double DEBOUNCE_TIME_FOR_LIMIT_SWITCH = 0.05;
    
    public static int BEAM_BREAKER_CHANNEL = 0;

    public static int CURRENT_LIMIT = 1;

    public static final GBSparkMax.SparkMaxConfObject FUNNEL_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VAL)
            .withCurrentLimit(CURRENT_LIMIT);

}
