package edu.greenblitz.robotName.subsystems.arm.ElbowUtils.MotorElbow.NeoElbow;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.ElbowUtils.ElbowConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;


public class NeoElbowConstants {

    public static final int PID_SLOT = 0;

    public static final PIDObject NEO_PID = new PIDObject().withKp(0.8).withKd(0.3).withKi(0).withMaxPower(1);

    public static final GBSparkMax.SparkMaxConfObject ELBOW_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withPID(NEO_PID)
            .withIdleMode(CANSparkMax.IdleMode.kBrake)
            .withRampRate(RobotConstants.General.RAMP_RATE_VAL)
            .withPositionConversionFactor(ElbowConstants.RELATIVE_POSITION_CONVERSION_FACTOR)
            .withVelocityConversionFactor(ElbowConstants.RELATIVE_VELOCITY_CONVERSION_FACTOR)
            .withCurrentLimit(ElbowConstants.CURRENT_LIMIT);


}
