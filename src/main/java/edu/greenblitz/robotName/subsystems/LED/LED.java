package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.Intake.Intake;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.shooter.Funnel.Funnel;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import java.util.function.Supplier;

import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.*;

public class LED extends GBSubsystem {
	
	private Color currentColor;
	private static LED instance;
	private AddressableLED addressableLED;
	private AddressableLEDBuffer addressableLEDBuffer;
	private Timer LEDBlinkTimer;
	private boolean noteInRobot;
	
	private LED() {
		this.addressableLED = new AddressableLED(LED_PORT);
		this.addressableLEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
		this.addressableLED.setLength(LED_LENGTH);
		this.addressableLED.start();
		LEDBlinkTimer = new Timer();
		noteInRobot = false;
		currentColor = SPEAKER_MODE_COLOR;
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
	
	public double getLEDBlinkTimer() {
		return LEDBlinkTimer.get();
	}
	public void stopLEDBlinkTimer() {
		LEDBlinkTimer.stop();
	}
	
	public void restartTimer() {
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
		currentColor = getColorByMode();
	}
	
	public void setColorByMode() {
		setLEDColor(currentColor, LEDConstants.ALL_LED);
	}
	
	public Color getColorByMode() {
		if (ScoringModeSelector.isAmpMode()) {
			return LEDConstants.AMP_MODE_COLOR;
		} else {
			return LEDConstants.SPEAKER_MODE_COLOR;
		}
	}
	
	public boolean shouldBlink() {
		if (Intake.getInstance().getExitBeamBreakerValue()
				|| Intake.getInstance().getEntranceBeamBreakerValue()
				|| Funnel.getInstance().isObjectIn()
				|| Roller.getInstance().isObjectInside()) {
			noteInRobot = true;
			return true;
		}
		return false;
	}
	
	public boolean didNoteExitRobot() {
		if (noteInRobot) {
			if (!(Intake.getInstance().getExitBeamBreakerValue())
					&& !(Intake.getInstance().getEntranceBeamBreakerValue())
					&& !(Funnel.getInstance().isObjectIn())
					&& !(Roller.getInstance().isObjectInside())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean shouldRubmle() {
		if (didNoteExitRobot()) {
			return true;
		}
		return false;
	}
	
	public void blink(Color color) {
		while (getLEDBlinkTimer() < LEDConstants.BLINKING_TIME) {
			if ((LED.getInstance().getLEDBlinkTimer()) % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
				LED.getInstance().turnOff(LEDConstants.ALL_LED);
			} else {
				LED.getInstance().setLEDColor(color, LEDConstants.ALL_LED);
			}
		}
	}
	public void blink(double time, Color color) {
		while (time < LEDConstants.BLINKING_TIME) {
			if (time % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
				LED.getInstance().turnOff(LEDConstants.ALL_LED);
			} else {
				LED.getInstance().setLEDColor(color, LEDConstants.ALL_LED);
			}
		}
	}
	public void blink(double time){
		SmartDashboard.putString("Aaaaaaaaa", ":aaaaaaaaa");
		blink(time, currentColor);
	}
	
}