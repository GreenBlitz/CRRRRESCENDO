package edu.greenblitz.robotName.subsystems.LED;

import edu.wpi.first.wpilibj.util.Color;

public class LEDConstants {
	public static final int LED_LENGTH = 500;
 
	public static final int RIGHT_RIGHT_LED_PORT = 0;

	public static final int RIGHT_LEFT_LED_PORT = 1;

	public static final int LEFT_RIGHT_LED_PORT = 2;

	public static final int LEFT_LEFT_LED_PORT = 3;

	public static final double BLINK_DURATION = 0.5;
 
	public static final Section FIRST_SECTION = new Section(1, 1);
 
	public static final Color AMP_MODE_COLOR = Color.kDeepPink;
 
	public static final Color SPEAKER_MODE_COLOR = Color.kYellow;
 
	public static final Section ALL_LEDS = new Section(0, LED_LENGTH);
 
	public static final double BLINKING_TIME = 5.0;
 
	public static final double RUMBLE_POWER = 0.5;
 
	public static final boolean RUMBLE_LEFT_MOTOR = true;
 
	public static final double RUMBLE_TIME = 1.0;
	
}
