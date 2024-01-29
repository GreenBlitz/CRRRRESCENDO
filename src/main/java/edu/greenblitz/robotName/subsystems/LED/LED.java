package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.LED_LENGTH;
import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.LED_PORT;

public class LED extends GBSubsystem {
	private static LED instance;
	private AddressableLED addressableLED;
	private AddressableLEDBuffer addressableLEDBuffer;
	private Timer LEDBlinkTimerOff;
	private Timer LEDBlinkTimerOn;
	private boolean blinkArm = false;
	private boolean blinkShooter = false;
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
				startBlinkingArm();
				inArm = true;
				break;
			case SHOOTER:
				startBlinkingShooter();
				inShooter = true;
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
	
	@Override
	public void periodic() {
		this.addressableLED.setData(addressableLEDBuffer);
		if (blinkArm) {
			if (LEDBlinkTimerOn.get() >= 1) {
				LEDBlinkTimerOn.reset();
				LEDBlinkTimerOn.stop();
				LEDBlinkTimerOff.start();
				setLEDColor(Color.kDarkBlue, 0, LED_LENGTH);
			} else if (LEDBlinkTimerOff.get() >= 0.25) {
				LEDBlinkTimerOff.reset();
				LEDBlinkTimerOff.stop();
				LEDBlinkTimerOn.start();
				turnOff(0, LED_LENGTH);
			} else {
				setLEDColor(Color.kDarkBlue, 0, LED_LENGTH);
			}
		}
		if (blinkShooter) {
			if (LEDBlinkTimerOn.get() >= 0.25) {
				LEDBlinkTimerOn.reset();
				LEDBlinkTimerOn.stop();
				LEDBlinkTimerOff.start();
				setLEDColor(Color.kYellow, 0, LED_LENGTH);
			} else if (LEDBlinkTimerOff.get() >= 0.25) {
				LEDBlinkTimerOff.reset();
				LEDBlinkTimerOff.stop();
				LEDBlinkTimerOn.start();
				turnOff(0, LED_LENGTH);
			} else {
				setLEDColor(Color.kYellow, 0, LED_LENGTH);
			}
		}
		
	}
	
	public enum RobotMode {
		AMP, SPEAKER, LIFTER;
		
		public enum NotePlaceInRobot {
			SHOOTER, ARM;
		}
	}
}



