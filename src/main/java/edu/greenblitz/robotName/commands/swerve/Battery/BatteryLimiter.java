package edu.greenblitz.robotName.commands.swerve.Battery;

import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.DriverStation;

public class BatteryLimiter extends GBCommand {

    private static final int WINDOW_SIZE = 50;
    private Battery battery;
    private LinearFilter voltageFilter;


    public BatteryLimiter() {
        battery = Battery.getInstance();
        require(battery);
        voltageFilter = LinearFilter.movingAverage(WINDOW_SIZE);
    }

    @Override
    public void initialize() {
        for (int i = 0; i < WINDOW_SIZE; i++) {
            voltageFilter.calculate(battery.getCurrentVoltage());
        }
    }

    @Override
    public void execute() {
        double currentAverageVoltage = voltageFilter.calculate(battery.getCurrentVoltage());
        if (currentAverageVoltage <= battery.getMinimumVoltage()
                && DriverStation.getMatchType() == DriverStation.MatchType.None) {
            throw new java.lang.RuntimeException("Battery is low");
        }
    }
}
