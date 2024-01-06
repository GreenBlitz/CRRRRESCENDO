package edu.greenblitz.robotName.utils.tuneableNumber;

import java.util.function.Consumer;

public class AutoSetterTunableNumber extends TunableNumber {
    private Consumer<Double> valueSettingFunction;

    public AutoSetterTunableNumber(String widgetTitle, String shuffleBoardTabTitle, Consumer<Double> valueSettingFunction) {
        super(widgetTitle, shuffleBoardTabTitle);
        this.valueSettingFunction = valueSettingFunction;
    }


    @Override
    public void periodic() {
        if (hasChanged()) {
            super.setValueByDashboard();
            valueSettingFunction.accept(value);
        }
    }

}
