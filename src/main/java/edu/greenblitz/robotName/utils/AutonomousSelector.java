package edu.greenblitz.robotName.utils;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.PathPlannerLogging;
import edu.greenblitz.robotName.commands.shooter.ShootToSpeakerFromClose;
import edu.greenblitz.robotName.subsystems.swerve.chassis.ChassisConstants;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class AutonomousSelector {

    private static AutonomousSelector instance;

    private final SendableChooser<Command> chooser;

    private AutonomousSelector() {
        chooser = AutoBuilder.buildAutoChooser();
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
