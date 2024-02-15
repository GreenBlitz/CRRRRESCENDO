package edu.greenblitz.robotName.subsystems;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import java.util.Map;

public class Dashboard extends GBSubsystem {

    private static Dashboard instance;
    private boolean driverDashboardInitiated = false;


    public static Dashboard getInstance() {
        init();
        return instance;
    }

    public static void init() {
        if (instance == null) {
            instance = new Dashboard();
        }
    }

    private Dashboard() {
        openDriversDashboard();
    }

    /**
     *  activate on robot-init
     * */
    public void openDriversDashboard(){
        Shuffleboard.getTab("Drivers");
    }

    /**
     *  activate not on robot-init, has to be activated in auto-init or teleop-init
     * */
    public void activateDriversDashboard() {
        if (driverDashboardInitiated) return;

        driverDashboardInitiated = true;
        ShuffleboardTab driversTab = Shuffleboard.getTab("Drivers");

        //arm states

        //pose

        ShuffleboardLayout robotPoseWidget = driversTab.getLayout("Robot pose", BuiltInLayouts.kList)
                .withPosition(0, 2).withSize(1, 2).withProperties(Map.of("Label position", "TOP"));
        robotPoseWidget.addDouble("X", () -> SwerveChassis.getInstance().getRobotPose().getX());
        robotPoseWidget.addDouble("Y", () -> SwerveChassis.getInstance().getRobotPose().getY());
        robotPoseWidget.addDouble("Rotation", () -> SwerveChassis.getInstance().getRobotPose().getRotation().getDegrees());
        //battery
        driversTab.addDouble("Battery", () -> Battery.getInstance().getCurrentVoltage())
                .withPosition(9, 3);


        //field
        driversTab.add("Field", SwerveChassis.getInstance().getField()).withPosition(5, 2).withSize(3, 2);
        driversTab.addDouble("std devs",()->MultiLimelight.getInstance().getDynamicStdDevs(0));
    }



}