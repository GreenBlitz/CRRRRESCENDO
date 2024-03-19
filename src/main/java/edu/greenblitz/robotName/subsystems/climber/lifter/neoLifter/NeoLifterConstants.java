package edu.greenblitz.robotName.subsystems.climber.lifter.neoLifter;

import com.revrobotics.CANSparkBase;
import com.revrobotics.SparkLimitSwitch;
import edu.greenblitz.robotName.subsystems.climber.lifter.LifterConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoLifterConstants {

    public static final int MOTOR_ID = 4;

    public static final GBSparkMax.SparkMaxConfObject CONFIG =
            new GBSparkMax.SparkMaxConfObject()
                    .withPID(new PIDObject(6, 0, 0))
                    .withIdleMode(CANSparkBase.IdleMode.kBrake)
            .withPositionConversionFactor(1 / LifterConstants.GEAR_RATIO)
            .withIdleMode(CANSparkBase.IdleMode.kBrake);

    public static final SimpleMotorFeedforward FEED_FORWARD = new SimpleMotorFeedforward(1, 0, 0);

    public static final SparkLimitSwitch.Type FORWARD_LIMIT_SWITCH_TYPE = SparkLimitSwitch.Type.kNormallyClosed;

    public static final SparkLimitSwitch.Type BACKWARD_LIMIT_SWITCH_TYPE = SparkLimitSwitch.Type.kNormallyClosed;

    public static final boolean IS_BACKWARD_LIMIT_SWITCH_ENABLED = false;

    public static final boolean IS_FORWARD_LIMIT_SWITCH_ENABLED = false;

    public static final int PID_SLOT = 0;
}