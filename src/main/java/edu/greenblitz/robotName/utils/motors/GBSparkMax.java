package edu.greenblitz.robotName.utils.motors;


import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import edu.greenblitz.robotName.utils.PIDObject;

public class GBSparkMax extends CANSparkMax {
	
	
	/**
	 * Create a new object to control a SPARK MAX motor Controller
	 *
	 * @param deviceId The device ID.
	 * @param type     The motor type connected to the controller. Brushless motor wires must be connected
	 *                 to their matching colors and the hall sensor must be plugged in. Brushed motors must be
	 *                 connected to the Red and Black terminals only.
	 */
	public GBSparkMax(int deviceId, MotorType type) {
		super(deviceId, type);
	}
	
	
	/**
	 * configs the motor settings using SparkMaxConfObject
	 *
	 * @param conf configObject, uses builder
	 */
	public void config(SparkMaxConfObject conf) {
		super.restoreFactoryDefaults();
		super.setSmartCurrentLimit(conf.getCurrentLimit());
		configPID(conf.getPidObject());
		super.getEncoder().setPositionConversionFactor(conf.getPositionConversionFactor());
		super.getEncoder().setVelocityConversionFactor(conf.getVelocityConversionFactor());
		super.setClosedLoopRampRate(conf.getRampRate());
		super.setOpenLoopRampRate(conf.getRampRate());
		super.setInverted(conf.isInverted());
		super.setIdleMode(conf.getIdleMode());
		
	}
	
	
	public void configPID(PIDObject pidObject) {
		super.getPIDController().setP(pidObject.getKp());
		super.getPIDController().setI(pidObject.getKi());
		super.getPIDController().setD(pidObject.getKd());
		super.getPIDController().setFF(pidObject.getKf());
		super.getPIDController().setIZone(pidObject.getIZone());
		super.getPIDController().setOutputRange(-pidObject.getMaxPower(), pidObject.getMaxPower());
	}
	public REVLibError setSoftLimit(SoftLimitDirection direction, double limit) {
		return super.setSoftLimit(direction,(float) limit);
	}
	
	/**
	 * inner conf class
	 * usage example:
	 * this.motor = new GBSparkMax(id, CANSparkMaxLowLevel.MotorType.kBrushless);
	 * motor.config(new GBSparkMax.SparkMaxConfObject()
	 * .withInverted(true) //whether the motor should be flipped
	 * .withCurrentLimit(40) // the max current to allow should be inline with the fuse
	 * .withIdleMode(CANSparkMax.IdleMode.kCoast) // trying to force brake is harmful for the motor
	 * .withRampRate(General.RAMP_RATE_VAL) // prevents the motor from drawing to much when rapidly changing speeds
	 * .withVoltageComp(General.VOLTAGE_COMP_VAL) // makes for more reproducible results
	 * .withPositionConversionFactor(1)
	 * .withVelocityConversionFactor(1)
	 * .withPID(new PIDObject(0.0003, 0.0000003, 0).withIZone(300)
	 * );
	 */



	
	public static class SparkMaxConfObject {
		
		private PIDObject pidObject = new PIDObject(0, 0, 0);
		
		private int currentLimit = 0;
		private double rampRate = 0;
		private boolean inverted = false;
		private IdleMode idleMode = IdleMode.kBrake;
		
		private double positionConversionFactor = 1;
		
		private double velocityConversionFactor = 1;
		
		
		//no parameters, usage for external config functions
		public SparkMaxConfObject() {
		
		}
		
		public SparkMaxConfObject(SparkMaxConfObject other) {
			this.pidObject = new PIDObject(other.pidObject);
			this.currentLimit = other.currentLimit;
			this.rampRate = other.rampRate;
			this.inverted = other.inverted;
			this.idleMode = other.idleMode;
			this.positionConversionFactor = other.positionConversionFactor;
			this.velocityConversionFactor = other.velocityConversionFactor;
		}
		
		public boolean isInverted() {
			return inverted;
		}
		
		public int getCurrentLimit() {
			return currentLimit;
		}
		
		public double getRampRate() {
			return rampRate;
		}
		
		public IdleMode getIdleMode() {
			return idleMode;
		}
		
		public double getPositionConversionFactor() {
			return positionConversionFactor;
		}
		
		public double getVelocityConversionFactor() {
			return velocityConversionFactor;
		}
		
		
		public SparkMaxConfObject withVelocityConversionFactor(double velocityConversionFactor) {
			this.velocityConversionFactor = velocityConversionFactor;
			return this;
		}
		
		public SparkMaxConfObject withPositionConversionFactor(double positionConversionFactor) {
			this.positionConversionFactor = positionConversionFactor;
			return this;
		}
		
		public SparkMaxConfObject withPID(PIDObject pidObject) {
			this.pidObject = pidObject;
			return this;
		}
		
		public SparkMaxConfObject withCurrentLimit(int currentLimit) {
			this.currentLimit = currentLimit;
			return this;
		}
		
		public SparkMaxConfObject withRampRate(double rampRate) {
			this.rampRate = rampRate;
			return this;
		}
		
		public SparkMaxConfObject withInverted(Boolean inverted) {
			this.inverted = inverted;
			return this;
		}
		
		public SparkMaxConfObject withIdleMode(IdleMode idleMode) {
			this.idleMode = idleMode;
			return this;
		}
		
		public PIDObject getPidObject() {
			return pidObject;
		}


	}
	
}
