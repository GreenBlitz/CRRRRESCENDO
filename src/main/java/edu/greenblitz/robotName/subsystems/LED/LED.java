package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.commands.LED.BlinkIfInArm;
import edu.greenblitz.robotName.commands.LED.BlinkIfInShooter;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.LED_LENGTH;
import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.LED_PORT;

public class LED extends GBSubsystem {
	private static LED instance;
	private AddressableLED addressableLED;
	private AddressableLEDBuffer addressableLEDBuffer;
	private Timer LEDBlinkTimer;
	private boolean blinkArm = false;
	private boolean blinkShooter = false;
	//private boolean noteOut = false;
	private SendableChooser<Boolean> noteOut;
	private boolean inArm = false;
	private boolean inShooter = false;
	
	
	private LED() {
		this.addressableLED = new AddressableLED(LED_PORT);
		this.addressableLEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
		this.addressableLED.setLength(LED_LENGTH);
		this.addressableLED.start();
		
		LEDBlinkTimer = new Timer();
		noteOut = new SendableChooser<>();
		noteOut.setDefaultOption("False", false);
		noteOut.addOption("True", true);
		SmartDashboard.putData("is object in robot: ", noteOut);
		
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
	
	public void startTimer() {
		LEDBlinkTimer.restart();
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
	
	public void blinkIfInArm() {
		if (LEDBlinkTimer.get() % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
			turnOff(0, LED_LENGTH);
		} else {
			setLEDColor(Color.kDarkBlue, 0, LED_LENGTH);
		}
	}
	
	public void blinkIfInShooter() {
		if (LEDBlinkTimer.get() % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
			turnOff(0, LED_LENGTH);
		} else {
			setLEDColor(Color.kDarkBlue, 0, LED_LENGTH);
		}
	}
	
	public boolean rumble() {
		SmartDashboard.putString("aaaaaa",noteOut.getSelected().toString());
		return noteOut.getSelected();
	}
	
	public void blinkByNotePlace(RobotMode.NotePlaceInRobot place) {
		switch (place) {
			case ARM:
				inArm = true;
				new BlinkIfInArm().schedule();
				break;
			case SHOOTER:
				new BlinkIfInShooter().schedule();
				inShooter = true;
				break;
		}
	}
}







