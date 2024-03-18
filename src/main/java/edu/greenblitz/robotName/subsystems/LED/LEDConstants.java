package edu.greenblitz.robotName.subsystems.LED;

import edu.wpi.first.wpilibj.util.Color;

public class LEDConstants {
	public static final int LED_LENGTH = 50;
 
	public static final int FRONT_RIGHT_LED_PORT = 8;


	public static final int FRONT_LEFT_LED_PORT = 0;

	public static final int BACK_RIGHT_LED_PORT = 6;

	public static final int BACK_LEFT_LED_PORT = 9;

	public static final double BLINK_DURATION = 0.5;
 
	public static final Section FIRST_SECTION = new Section(1, 1);
 
	public static final Color AMP_MODE_COLOR = Color.kDeepPink;
 
	public static final Color SPEAKER_MODE_COLOR = Color.kBlue;
 
	public static final Section ALL_LEDS = new Section(0, LED_LENGTH);
 
	public static final double BLINKING_TIME = 3.0;
 
	public static final double RUMBLE_POWER = 0.8;
 
	public static final boolean RUMBLE_LEFT_MOTOR = true;
 
	public static final double RUMBLE_TIME = 1.0;
	
}
