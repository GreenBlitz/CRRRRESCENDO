package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.commands.LED.BlinkIfInArm;
import edu.greenblitz.robotName.commands.LED.BlinkIfInShooter;
import edu.greenblitz.robotName.commands.LED.Rumble;
import edu.greenblitz.robotName.subsystems.Intake.Intake;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
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
	private Timer LEDBlinkTimer;
	private boolean didNoteExitRobot;
	private boolean noteInRobot;
	
	private LED() {
		this.addressableLED = new AddressableLED(LED_PORT);
		this.addressableLEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
		this.addressableLED.setLength(LED_LENGTH);
		this.addressableLED.start();
		LEDBlinkTimer = new Timer();
		didNoteExitRobot = false;
		noteInRobot = false;
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
	
	public void restartTimer() {
		LEDBlinkTimer.restart();
	}
	
	public double getTimerTime() {
		return LEDBlinkTimer.get();
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
		blinkByNotePlace();
		noteMode();
		rumbleIfNoteOut();
	}
	
	public void colorByMode() {
		if (ScoringModeSelector.isAmpMode()) {
			setLEDColor(LEDConstants.AMP_MODE_COLOR, LEDConstants.ALL_LED);
		} else {
			setLEDColor(LEDConstants.SHOOTER_MODE_COLOR, LEDConstants.ALL_LED);
		}
	}
	
	public void blinkIfInArm() {
		if (LEDBlinkTimer.get() % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
			turnOff(LEDConstants.ALL_LED);
		} else {
			setLEDColor(LEDConstants.AMP_MODE_COLOR, LEDConstants.ALL_LED);
		}
	}
	
	public void blinkIfInShooter() {
		if (LEDBlinkTimer.get() % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
			turnOff(LEDConstants.ALL_LED);
		} else {
			setLEDColor(LEDConstants.SHOOTER_MODE_COLOR, LEDConstants.ALL_LED);
		}
	}
	
	public void blinkByNotePlace() {
		if (noteInRobot) {
			if (Funnel.getInstance().isObjectIn()) {
				new BlinkIfInShooter().schedule();
			} else if (Roller.getInstance().isObjectInside()) {
				new BlinkIfInArm().schedule();
			}
		}
	}
	
	public void noteMode() {
		if (Intake.getInstance().getExitBeamBreakerValue()
				|| Intake.getInstance().getEntranceBeamBreakerValue()
				|| Funnel.getInstance().isObjectIn()
				|| Roller.getInstance().isObjectInside()) {
			noteInRobot = true;
		}
		if (!(Intake.getInstance().getExitBeamBreakerValue())
				&& !(Intake.getInstance().getEntranceBeamBreakerValue())
				&& !(Funnel.getInstance().isObjectIn())
				&& !(Roller.getInstance().isObjectInside())) {
			didNoteExitRobot = true;
		} else {
			didNoteExitRobot = false;
		}
	}
	
	public void rumbleIfNoteOut() {
		if (noteInRobot) {
			if (didNoteExitRobot) {
				new Rumble().schedule();
				noteInRobot = false;
			}
		}
	}
}