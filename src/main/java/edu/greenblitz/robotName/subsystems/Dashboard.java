package edu.greenblitz.robotName.subsystems;

import edu.greenblitz.robotName.FieldConstants;
import edu.greenblitz.robotName.ScoringModeSelector;
import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.pivot.Pivot;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Map;

public class Dashboard extends GBSubsystem {

    private static Dashboard instance;

    private boolean driverDashboardInitiated;

    private Dashboard() {
        driverDashboardInitiated = false;
        openDriversDashboard();
    }

    public static Dashboard getInstance() {
        init();
        return instance;
    }

    public static void init() {
        if (instance == null) {
            instance = new Dashboard();
        }
    }

    /**
     * activate on robot-init
     */
    public void openDriversDashboard() {
        Shuffleboard.getTab("Drivers");
        swerveDashboard();
    }

    /**
     * activate not on robot-init, has to be activated in auto-init or teleop-init
     */
    public void activateDriversDashboard() {
        if (driverDashboardInitiated) return;

        driverDashboardInitiated = true;
        ShuffleboardTab driversTab = Shuffleboard.getTab("Drivers");

        ShuffleboardLayout robotPoseWidget = driversTab.getLayout("Robot pose", BuiltInLayouts.kList)
                .withPosition(0, 2).withSize(1, 2).withProperties(Map.of("Label position", "TOP"));
        robotPoseWidget.addDouble("X", () -> SwerveChassis.getInstance().getRobotPose2d().getX());
        robotPoseWidget.addDouble("Y", () -> SwerveChassis.getInstance().getRobotPose2d().getY());
        robotPoseWidget.addDouble("Rotation", () -> SwerveChassis.getInstance().getRobotPose2d().getRotation().getDegrees());
        SmartDashboard.putNumber("pivot angle", Pivot.getInstance().getAngle().getDegrees());
        
        //battery
        driversTab.addDouble("Battery", () -> Battery.getInstance().getCurrentVoltage())
                .withPosition(9, 3);
        driversTab.addBoolean("bb", () -> Intake.getInstance().isObjectIn());

        //field
        driversTab.add("Field", SwerveChassis.getInstance().getField()).withPosition(5, 2).withSize(3, 2);
        driversTab.addString("state", () -> ScoringModeSelector.getScoringMode().toString());
    }

    public void swerveDashboard() {
        ShuffleboardTab swerveTab = Shuffleboard.getTab("Swerve");
        for (SwerveChassis.Module module : SwerveChassis.Module.values()) {
            swerveTab.addDouble(module + "-angle", () -> Math.IEEEremainder(SwerveChassis.getInstance().getModuleAngle(module).getDegrees(), 360))
                    .withSize(2, 1).withPosition(module.ordinal() * 2, 0);
            swerveTab.addDouble(module + "-absolute-angle", () -> SwerveChassis.getInstance().getModuleAbsoluteEncoderAngle(module).getDegrees())
                    .withSize(2, 1).withPosition(module.ordinal() * 2, 1);
            swerveTab.addDouble(module + "-linear-distance", () -> SwerveChassis.getInstance().getSwerveModulePositions()[module.ordinal()].distanceMeters)
                    .withSize(2, 1).withPosition(module.ordinal() * 2, 2);
        }
        swerveTab.addDouble("robot-angle", () -> SwerveChassis.getInstance().getChassisAngle().getDegrees())
                .withSize(1, 1).withPosition(0, 3);
        swerveTab.addDouble("pigeon-angle", () -> SwerveChassis.getInstance().getGyroAngle().getDegrees())
                .withSize(1, 1).withPosition(0, 3);
    }

}