package edu.greenblitz.robotName.subsystems.LED;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

public class LED extends GBSubsystem {
    private static LED instance;
    private AddressableLED addressableLED;
    private AddressableLEDBuffer addressableLEDBuffer;


    private LED() {
        this.addressableLED = new AddressableLED(LEDConstants.LED_PORT);
        this.addressableLEDBuffer = new AddressableLEDBuffer(LEDConstants.LED_LENGTH);
        this.addressableLED.setLength(LEDConstants.LED_LENGTH);
        this.addressableLED.start();
    }
    public static LED getInstance() {
        init();
        return instance;
    }

    public static void init(){
        if (instance == null) {
            instance = new LED();
        }
    }

    public void setColor (Color color, Section section){
        setColor(color,section);
    }
    public void setColor (Color color,int startIndex,int endIndex){
        for (int i = startIndex; i < endIndex; i++) {
            setColor(color,i);
        }
    }
    public void setColor(Color color, int index) {
        this.addressableLEDBuffer.setLED(index, color);
    }
    public void turnOff (int index){
        setColor(new Color(0,0,0), index);
    }
    public void turnOff (int startIndex,int endIndex){
        for (int i = startIndex; i < endIndex; i++) {
            turnOff(i);
        }
    }
    public void turnOff (Section section){
        turnOff(section.startIndex(),section.endIndex());
    }

    @Override
    public void periodic() {
        this.addressableLED.setData(addressableLEDBuffer);
    }

}
