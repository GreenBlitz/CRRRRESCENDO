package edu.greenblitz.robotName.utils;

import edu.greenblitz.robotName.utils.GBPathFinding.GBAutoBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class AutonomousSelector {

    private static AutonomousSelector instance;

    private final SendableChooser<Command> chooser;

    private AutonomousSelector() {
        chooser = GBAutoBuilder.buildAutoChooser();
        ShuffleboardTab tab = Shuffleboard.getTab("auto");
        tab.add("autonomous chooser", chooser);
    }

    public Command getChosenValue() {
        return chooser.getSelected();
    }

    public static AutonomousSelector getInstance() {
        if (instance == null) {
            instance = new AutonomousSelector();
        }
        return instance;
    }
}
