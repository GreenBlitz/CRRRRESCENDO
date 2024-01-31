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
	private boolean noteOut = false;
	private boolean inArm = false;
	private boolean inShooter = false;
	private boolean rumble = false;
	private boolean blinkArm = false;
	private boolean blinkShooter = false;
	private boolean isBlinking = false;
	
	
	private LED() {
		this.addressableLED = new AddressableLED(LED_PORT);
		this.addressableLEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
		this.addressableLED.setLength(LED_LENGTH);
		this.addressableLED.start();
		LEDBlinkTimerOff = new Timer();
		LEDBlinkTimerOn = new Timer();
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
	
	@Override
	public void periodic() {
		this.addressableLED.setData(addressableLEDBuffer);
		if (blinkArm) {
			if (LEDBlinkTimerOn.get() >= 0.25) {
				LEDBlinkTimerOn.reset();
				LEDBlinkTimerOff.start();
				setLEDColor(Color.kDarkBlue, 0, LED_LENGTH);
			} else if (LEDBlinkTimerOff.get() >= 0.25) {
				LEDBlinkTimerOff.reset();
				LEDBlinkTimerOn.start();
				turnOff(0, LED_LENGTH);
			} else {
				setLEDColor(Color.kDarkBlue, 0, LED_LENGTH);
			}
		}
		
		
	}
	
	public enum RobotMode {
		AMP, SPEAKER, LIFTER;
		
		public enum NotePlaceInRobot {
			SHOOTER, ARM;
		}
	}
	
	public void colorByMode(RobotMode mode) {
		
		switch (mode) {
			case AMP:
				setLEDColor(Color.kBlue, 0, LED_LENGTH);
				break;
			case SPEAKER:
				setLEDColor(Color.kYellow, 0, LED_LENGTH);
		}
	}
	
	public void blinkByNotePlace(RobotMode.NotePlaceInRobot place) {
		switch (place) {
			case ARM:
				LEDBlinkTimerOn.start();
//				blinkIfInShooter();
				startBlinkingArm();
				isBlinking = true;
				inArm = true;
				while (isBlinking){
					if (LEDBlinkTimerOn.get() == 1) {
						stopBlinkingArm();
						LEDBlinkTimerOn.reset();
						LEDBlinkTimerOff.reset();
					}
				}
				break;
			case SHOOTER:
				LEDBlinkTimerOn.start();
//				blinkIfInShooter();
				startBlinkingShooter();
				isBlinking = true;
				inShooter = true;
				if (blinkShooter) {
					if (LEDBlinkTimerOn.get() > 0.25) {
						LEDBlinkTimerOn.reset();
						LEDBlinkTimerOn.stop();
						LEDBlinkTimerOff.start();
						setLEDColor(Color.kYellow, 0, LED_LENGTH);
					} else if (LEDBlinkTimerOff.get() > 0.25) {
						LEDBlinkTimerOff.reset();
						LEDBlinkTimerOff.stop();
						LEDBlinkTimerOn.start();
						turnOff(0, LED_LENGTH);
					} else {
						setLEDColor(Color.kYellow, 0, LED_LENGTH);
					}
				}
				while (isBlinking){
					if (LEDBlinkTimerOn.get() == 10) {
						stopBlinkingShooter();
						LEDBlinkTimerOn.reset() ;
						LEDBlinkTimerOff.reset();
					}
				}
				break;
		}
	}
	
	public boolean rumble() {
		if ((inArm && noteOut)) {
			return rumble = true;
		}
		if ((inShooter && noteOut)) {
			return rumble = true;
		}
		return false;
	}
	
	
	public void startBlinkingArm() {
		blinkArm = true;
	}
	
	public void stopBlinkingArm() {
		blinkArm = false;
	}
	
	public void startBlinkingShooter() {
		blinkShooter = true;
	}
	
	public void stopBlinkingShooter() {
		blinkShooter = false;
	}

//	public void blinkIfInArm() {
//		Timer timer = new Timer();
//		timer.start();
//		while (timer.get() <= 10) {
//			if (LEDBlinkTimerOn.get() >= 0.25) {
//				System.out.println(LEDBlinkTimerOn.get());
//				LEDBlinkTimerOn.reset();
//				LEDBlinkTimerOff.start();
//				  (Color.kBlue, 0, LED_LENGTH);
//			} else if (LEDBlinkTimerOff.get() >= 0.25) {
//				LEDBlinkTimerOff.reset();
//				LEDBlinkTimerOn.start();
//				setLEDColor(Color.kBlue, 0, LED_LENGTH);
//			} else {
//				stopBlink();
//			}
//		}
//	}

//	public void blinkIfInShooter() {
//		Timer timer = new Timer();
//		while (timer.get() <= 1) {
//			if (LEDBlinkTimerOn.get() >= 0.25) {
//				LEDBlinkTimerOn.reset();
//				LEDBlinkTimerOn.stop();
//				LEDBlinkTimerOff.start();
//				setLEDColor(Color.kYellow, 0, LED_LENGTH);
//			} else if (LEDBlinkTimerOff.get() >= 0.25) {
//				LEDBlinkTimerOff.reset();
//				LEDBlinkTimerOff.stop();
//				LEDBlinkTimerOn.start();
//				setLEDColor(Color.kYellow, 0, LED_LENGTH);
//			} else {
//				stopBlink();
//			}
//		}
//	}
}



