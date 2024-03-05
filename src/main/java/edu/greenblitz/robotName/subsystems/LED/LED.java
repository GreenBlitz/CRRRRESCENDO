package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.arm.roller.Roller;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;

import static edu.greenblitz.robotName.subsystems.LED.LEDConstants.*;

public class LED extends GBSubsystem {

	private Color currentColor;

	private static LED instance;

	private AddressableLED addressableLED;

	private AddressableLEDBuffer addressableLEDBuffer;

	private Timer LEDBlinkTimer;

	private boolean wasNoteInRobot;

	private Timer actionTimer;

	private LED() {
		this.addressableLED = new AddressableLED(LED_PORT);
		this.addressableLEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
		this.addressableLED.setLength(LED_LENGTH);
		this.addressableLED.start();

		LEDBlinkTimer = new Timer();
		actionTimer = new Timer();
		LEDBlinkTimer.restart();

		currentColor = SPEAKER_MODE_COLOR;
		wasNoteInRobot = false;
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

	public boolean getWasNoteInRobot() {
		return wasNoteInRobot;
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
		currentColor = getColorByMode();
		this.addressableLED.setData(addressableLEDBuffer);
	}

	public void setColorByMode() {
		setLEDColor(currentColor, LEDConstants.ALL_LEDS);
	}

	public Color getColorByMode() {
		if (ScoringModeSelector.isAmpMode()) {
			return LEDConstants.AMP_MODE_COLOR;
		} else {
			return LEDConstants.SPEAKER_MODE_COLOR;
		}
	}

	public boolean isNoteInRobot() {
		return (Intake.getInstance().isObjectIn()
				|| Funnel.getInstance().isObjectIn()
				|| Roller.getInstance().isObjectIn());
	}

	public void updateNoteState() {
		wasNoteInRobot = isNoteInRobot();
	}

	public void blink(Color color) {
		if ((LEDBlinkTimer.get()) % (LEDConstants.BLINK_DURATION * 2) >= LEDConstants.BLINK_DURATION) {
			LED.getInstance().turnOff(LEDConstants.ALL_LEDS);
		} else {
			LED.getInstance().setLEDColor(color, LEDConstants.ALL_LEDS);
		}
	}

	public void  rumble() {
		OI.getInstance().getSecondJoystick().rumble(LEDConstants.RUMBLE_LEFT_MOTOR, LEDConstants.RUMBLE_POWER);
		OI.getInstance().getMainJoystick().rumble(LEDConstants.RUMBLE_LEFT_MOTOR, LEDConstants.RUMBLE_POWER);
	}

	public void stopRumble() {
		OI.getInstance().getSecondJoystick().rumble(LEDConstants.RUMBLE_LEFT_MOTOR, 0);
	}

	public void restartTimerByNoteState() {
		if (getWasNoteInRobot() != isNoteInRobot()) {
			actionTimer.restart();
		}
	}

	public void blinkOrRumbleByNoteState() {
		if (isNoteInRobot() && (actionTimer.get() <= LEDConstants.BLINKING_TIME)) {
			blink(getColorByMode());
		} else if (actionTimer.get() <= LEDConstants.RUMBLE_TIME) {
			rumble();
		} else {
			stopRumble();
		}
	}

	public void setColorByNoteState() {
		if (!isNoteInRobot()) {
			setColorByMode();
		}
	}
}