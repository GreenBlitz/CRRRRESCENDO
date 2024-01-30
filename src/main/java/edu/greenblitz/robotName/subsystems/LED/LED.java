package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;

import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.LED_LENGTH;
import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.LED_PORT;

public class LED extends GBSubsystem {
	private static LED instance;
	private AddressableLED addressableLED;
	private AddressableLEDBuffer addressableLEDBuffer;
	private Timer LEDBlinkTimerOff;
	private Timer LEDBlinkTimerOn;
	private boolean blinkIfInArm = false;
	private boolean blinkIfInShooter = false;
	private boolean noteOut = false;
	private boolean inArm = false;
	private boolean inShooter = false;
	private boolean rumble = false;
	
	private LED() {
		this.addressableLED = new AddressableLED(LED_PORT);
		this.addressableLEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
		this.addressableLED.setLength(LED_LENGTH);
		this.addressableLED.start();
		LEDBlinkTimerOff = new Timer();
		LEDBlinkTimerOn = new Timer();
		LEDBlinkTimerOn.start();
		LEDBlinkTimerOff.reset();
		LEDBlinkTimerOn.reset();
		
	}
	
	public static LED getInstance() {
		init();
		return instance;
	}
	
	public static void init() {
		if (instance == null) {
			instance = new LED();
		}
	}
	
	public void setLEDColor(Color color, Section section) {
		setLEDColor(color, section.startIndex(), section.endIndex());
	}
	
	public void setLEDColor(Color color, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			setLEDColor(color, i);
		}
	}
	
	public void setLEDColor(Color color, int index) {
		this.addressableLEDBuffer.setLED(index, color);
	}
	
	public void colorByMode(RobotMode mode) {
		
		switch (mode) {
			case AMP:
				setLEDColor(Color.kYellow, 0, LED_LENGTH);
				break;
			case SPEAKER:
				setLEDColor(Color.kRed, 0, LED_LENGTH);
		}
	}
	
	public void blinkByNotePlace(RobotMode.NotePlaceInRobot place) {
		switch (place) {
			case ARM:
				blink();
				break;
			case SHOOTER:
				blink();
		}
	}
	
	public boolean rumble() {
//		if ((inArm && noteOut)) {
//			return rumble = true;
//		}
//		if ((inShooter && noteOut)) {
//			return rumble = true;
//		}
//		return false;
		return rumble = true;
	}
	
	public void turnOff(int index) {
		setLEDColor(new Color(0, 0, 0), index);
	}
	
	public void turnOff(int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			turnOff(i);
		}
	}
	
	public void turnOff(Section section) {
		turnOff(section.startIndex(), section.endIndex());
	}
	
	public void blink() {
		Timer timer = new Timer();
		while (timer.get() < 1) {
			if (LEDBlinkTimerOn.get() >= 0.25) {
				LEDBlinkTimerOn.reset();
				LEDBlinkTimerOn.stop();
				LEDBlinkTimerOff.start();
			} else if (LEDBlinkTimerOff.get() >= 0.25) {
				LEDBlinkTimerOff.reset();
				LEDBlinkTimerOff.stop();
				LEDBlinkTimerOn.start();
			}
			if ((LEDBlinkTimerOff.get() == 1) || (LEDBlinkTimerOn.get() == 1)) {
				stopBlinkingArm();
			}
		}
	}
	@Override
	public void periodic() {
		this.addressableLED.setData(addressableLEDBuffer);
		}
public enum RobotMode {
	AMP, SPEAKER, LIFTER;
	
	public enum NotePlaceInRobot {
		SHOOTER, ARM;
	}
}
}



