package edu.greenblitz.robotName.utils.hid;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is in charge of making the Joystick easy to assign things to buttons and invert the axes of the joystick.
 * The class uses joystick as the main joystick, and uses button values and enums in order to assign thing to those buttons.
 *
 * @see Joystick
 * @see JoystickButton
 */
public class SmartJoystick {
	private static final double DEADZONE = 0.05;
	public final JoystickButton A,
			B,
			X,
			Y,
			L1,
			R1,
			START,
			BACK,
			L3,
			R3;
	public final POVButton POV_UP,
			POV_RIGHT,
			POV_DOWN,
			POV_LEFT;
	private final double deadzone;
	private Joystick joystick;
	
	/**
	 * This constructor constructs the joystick based on the joystick port we give it.
	 *
	 * @param joystick_port The port of the joystick.
	 * @param deadzone      values if the stick below it will count as 0.
	 */
	public SmartJoystick(int joystick_port, double deadzone) {
		this(new Joystick(joystick_port), deadzone);
	}
	
	/**
	 * This constructor constructs the joystick based on the joystick port we give it.
	 *
	 * @param joystick_port The port of the joystick.
	 */
	public SmartJoystick(int joystick_port) {
		this(new Joystick(joystick_port));
	}
	
	/**
	 * This constructor uses a joystick and assigns it button values and numbers.
	 *
	 * @param stick    The joystick object.
	 * @param deadzone values if the stick below it will count as 0.
	 */
	public SmartJoystick(Joystick stick, double deadzone) {
		this.deadzone = deadzone;
		joystick = stick;
		A = new JoystickButton(joystick, 1);
		B = new JoystickButton(joystick, 2);
		X = new JoystickButton(joystick, 3);
		Y = new JoystickButton(joystick, 4);
		L1 = new JoystickButton(joystick, 5);
		R1 = new JoystickButton(joystick, 6);
		BACK = new JoystickButton(joystick, 7);
		START = new JoystickButton(joystick, 8);
		L3 = new JoystickButton(joystick, 9);
		R3 = new JoystickButton(joystick, 10);
		POV_UP = new POVButton(joystick, 0);
		POV_RIGHT = new POVButton(joystick, 90);
		POV_DOWN = new POVButton(joystick, 180);
		POV_LEFT = new POVButton(joystick, 270);
	}
	
	public SmartJoystick(Joystick stick) {
		this(stick, DEADZONE);
	}
	
	private double deadzone(double power) {
		if (Math.abs(power) < deadzone) return 0;
		return (Math.abs(power) - deadzone) / (1 - deadzone) * Math.signum(power);
	}
	
	/**
	 * This function binds a joystick using a joystick object.
	 *
	 * @param stick This stick object which we bind the joystick to.
	 */
	public void bind(Joystick stick) {
		joystick = stick;
	}
	
	/**
	 * This function binds a joystick using a joystick port.
	 *
	 * @param port The port of the joystick which we bind the joystick to.
	 */
	public void bind(int port) {
		bind(new Joystick(port));
	}
	
	/**
	 * This function returns the axis based on an axis number.
	 *
	 * @param raw_axis The axis number we want to return.
	 * @return A joystick axis based off of the joystick axis number.
	 */
	public double getRawAxis(int raw_axis) {
		if (joystick == null) return 0;
		return joystick.getRawAxis(raw_axis);
	}
	
	/**
	 * This function returns a joystick
	 *
	 * @return The joystick used in this class.
	 */
	public Joystick getRawJoystick() {
		return joystick;
	}
	
	public double getAxisValue(Axis axis) {
		if (axis != Axis.LEFT_TRIGGER && axis != Axis.RIGHT_TRIGGER)
			return deadzone(axis.getValue(this));
		return axis.getValue(this);
	}
	
	/**
	 * Rumbles the controller at a certain side
	 *
	 * @param left  rumble side (false for left)
	 * @param power normalized rumble [0, 1]
	 */
	public void rumble(boolean left, double power) {
		joystick.setRumble(left ? GenericHID.RumbleType.kLeftRumble : GenericHID.RumbleType.kRightRumble, power);
	}
	
	public enum Axis {
		LEFT_X(0, false),
		LEFT_Y(1, true),
		LEFT_TRIGGER(2, false),
		RIGHT_TRIGGER(3, false),
		RIGHT_X(4, false),
		RIGHT_Y(5, true);
		
		private final int axis;
		private int inverted;
		
		Axis(int axis, boolean isInverted) {
			this.axis = axis;
			setInverted(isInverted);
		}
		
		public void setInverted(boolean isInverted) {
			inverted = isInverted ? -1 : 1;
		}
		
		public double getValue(SmartJoystick stick) {
			return inverted * stick.getRawAxis(axis);
		}
	}
}