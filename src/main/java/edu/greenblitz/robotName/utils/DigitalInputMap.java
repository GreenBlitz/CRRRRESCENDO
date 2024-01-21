package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj.DigitalInput;

public class DigitalInputMap extends GBSubsystem{
    private static final int NUMBER_OF_PORTS = 10;

    private static DigitalInputMap instance;
    private final DigitalInput[] digitalInputs;

    private DigitalInputMap() {



        digitalInputs = new DigitalInput[NUMBER_OF_PORTS];
        for (int i = 0; i < NUMBER_OF_PORTS; i++) {
            digitalInputs[i] = new DigitalInput(i);
        }

    }

    public static DigitalInputMap getInstance() {
        if (instance == null) {
            instance = new DigitalInputMap();
            instance.register();
        }
        return instance;
    }

    public DigitalInput getDigitalInput(int port) {
        return digitalInputs[port];
    }

    public boolean getValue(int port) {
        return digitalInputs[port].get();
    }

    @Override
    public void periodic() {

    }

}
