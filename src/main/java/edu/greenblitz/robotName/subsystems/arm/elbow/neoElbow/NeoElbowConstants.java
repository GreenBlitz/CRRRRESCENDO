package edu.greenblitz.robotName.subsystems.arm.elbow.neoElbow;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.arm.elbow.ElbowConstants;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;

public class NeoElbowConstants {

    public static final int MOTOR_ID = 5;
    
    public static final boolean IS_INVERTED = true;

    public static final double kS = 0;

    public static final double kV = 0;

    public static final double kG = 0.17;

    public static final double kA = 0;

    public static final ArmFeedforward ELBOW_FEEDFORWARD = new ArmFeedforward(kS, kG, kV, kA);

    public static final int PID_SLOT = 0;

    public static final PIDObject PID = new PIDObject().withKp(2).withKd(0).withKi(0).withMaxPower(0.5);

    public static final GBSparkMax.SparkMaxConfObject ELBOW_CONFIG_OBJECT = new GBSparkMax.SparkMaxConfObject()
            .withPID(PID)
            .withIdleMode(CANSparkMax.IdleMode.kCoast)
            .withRampRate(RobotConstants.General.RAMP_RATE_VALUE)
            .withPositionConversionFactor(ElbowConstants.GEAR_RATIO)
            .withVelocityConversionFactor(ElbowConstants.GEAR_RATIO)
            .withCurrentLimit(ElbowConstants.CURRENT_LIMIT)
            .withInverted(IS_INVERTED);
}