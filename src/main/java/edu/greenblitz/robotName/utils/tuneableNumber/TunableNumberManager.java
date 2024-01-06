package edu.greenblitz.robotName.utils.tuneableNumber;

import edu.greenblitz.robotName.utils.GBSubsystem;

import java.util.HashMap;

public class TunableNumberManager extends GBSubsystem {
    private static TunableNumberManager instance;
    private static HashMap<String, TunableNumber> tunableNumbers;

    private static void init() {
        if (instance == null) {
            instance = new TunableNumberManager();
        }
    }

    public static TunableNumberManager getInstance() {
        init();
        return instance;
    }

    public TunableNumberManager() {
        tunableNumbers = new HashMap<>();
    }

    @Override
    public void periodic() {
        for (TunableNumber tunableNumber : tunableNumbers.values()) {
            tunableNumber.periodic();
        }
    }

    public void addTunableNumber(String tunableNumberKey, TunableNumber tunableNumber) {
        tunableNumbers.put(tunableNumberKey, tunableNumber);
    }

    public TunableNumber getTunableNumberForKey(String tunableNumberKey) {
        return tunableNumbers.get(tunableNumberKey);
    }
}
