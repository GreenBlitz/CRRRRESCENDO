package edu.greenblitz.robotName.utils.motors;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.greenblitz.robotName.utils.PIDObject;

public class GBFalcon extends TalonFX {
	
	/**
	 * Constructor
	 *
	 * @param deviceNumber [0,62]
	 */
	public GBFalcon(int deviceNumber) {
		super(deviceNumber);
	}
	
	/**
	 * configs the motor settings using FalconConfObject
	 *
	 * @param conf configObject, uses builder
	 */
	public void config(FalconConfObject conf) {
		configFactoryDefault();
		super.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, conf.getCurrentLimit(), conf.getCurrentLimit(), 0));
		super.configClosedloopRamp(conf.getRampRate());
		super.configOpenloopRamp(conf.getRampRate());
		super.setInverted(conf.isInverted());
		super.configSelectedFeedbackCoefficient(conf.getConversionFactor());
		super.configVoltageCompSaturation(conf.getVoltageCompSaturation());
		super.setNeutralMode(conf.getNeutralMode());
		configPID(conf.pidObject);
	}
	
	public void configPID(PIDObject pidObject) {
		super.config_kP(0, pidObject.getKp());
		super.config_kI(0, pidObject.getKi());
		super.config_kD(0, pidObject.getKd());
		super.config_kF(0, pidObject.getKf());
		super.config_IntegralZone(0, pidObject.getIZone());
		super.configClosedLoopPeakOutput(0, pidObject.getMaxPower());
	}
	
	/**
	 * @see GBSparkMax.SparkMaxConfObject
	 */
	public static class FalconConfObject {
		private PIDObject pidObject = new PIDObject(0, 0, 0);
		private int currentLimit = 0;
		private double rampRate = 0;
		private boolean inverted = false;
		private double ConversionFactor = 1;
		private double voltageCompSaturation = 0;
		private NeutralMode neutralMode = NeutralMode.Brake;
		
		public FalconConfObject(FalconConfObject other) {
			this.pidObject = new PIDObject(other.pidObject);
			this.currentLimit = other.currentLimit;
			this.rampRate = other.rampRate;
			this.inverted = other.inverted;
			this.neutralMode = other.neutralMode;
			this.voltageCompSaturation = other.voltageCompSaturation;
		}
		
		public FalconConfObject() {
		
		}
		
		public int getCurrentLimit() {
			return currentLimit;
		}
		
		public double getRampRate() {
			return rampRate;
		}
		
		public boolean isInverted() {
			return inverted;
		}
		
		public double getConversionFactor() {
			return ConversionFactor;
		}
		
		public double getVoltageCompSaturation() {
			return voltageCompSaturation;
		}
		
		public NeutralMode getNeutralMode() {
			return neutralMode;
		}
		
		public FalconConfObject withNeutralMode(NeutralMode neutralMode) {
			this.neutralMode = neutralMode;
			return this;
		}
		
		public FalconConfObject withConversionFactor(double velocityConversionFactor) {
			this.ConversionFactor = velocityConversionFactor;
			return this;
		}
		
		public FalconConfObject withPID(PIDObject pidObject) {
			this.pidObject = pidObject;
			return this;
		}
		
		public FalconConfObject withCurrentLimit(int currentLimit) {
			this.currentLimit = currentLimit;
			return this;
		}
		
		public FalconConfObject withRampRate(double rampRate) {
			this.rampRate = rampRate;
			return this;
		}
		
		public FalconConfObject withInverted(Boolean inverted) {
			this.inverted = inverted;
			return this;
		}
		
		
		public FalconConfObject withVoltageCompSaturation(double voltageCompSaturation) {
			this.voltageCompSaturation = voltageCompSaturation;
			return this;
		}
		
		public PIDObject getPidObject() {
			return pidObject;
		}
		
		
	}
}
