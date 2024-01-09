package edu.greenblitz.robotName.commands.swerve.Battery;

import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import static com.sun.org.apache.bcel.internal.util.Args.require;

public class BatteryLimiter extends GBCommand {

    private static final int LEN_OF_AVG = 50;
    private static final double USAGE_MIN_LEVEL = 2;
    private Battery battery;
    private LinearFilter voltageFilter;


    public BatteryLimiter() {
        voltageFilter = LinearFilter.movingAverage(LEN_OF_AVG);
        battery = Battery.getInstance();
        require(battery);
    }

    @Override
    public void initialize() {
        for (int i = 0; i < LEN_OF_AVG; i++) {
            voltageFilter.calculate(battery.getCurrentVoltage());
        }
    }

    @Override
    public void execute() {
        double currentAverageVoltage;

        if(battery.getTotalCurrent() <= USAGE_MIN_LEVEL) {
            currentAverageVoltage = voltageFilter.calculate(battery.getCurrentVoltage());


            if (currentAverageVoltage <= battery.getMinimumVoltage()
                    && DriverStation.getMatchType() == DriverStation.MatchType.None) {
                CommandScheduler.getInstance().cancelAll();
                CommandScheduler.getInstance().disable();
                throw new java.lang.RuntimeException("Battery is low");
            }
        }
    }
}
