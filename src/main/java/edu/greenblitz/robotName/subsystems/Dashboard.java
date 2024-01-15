package edu.greenblitz.robotName.subsystems;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.subsystems.Limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;

import java.util.Map;

public class Dashboard extends GBSubsystem {

    private static Dashboard instance;

    public MechanismLigament2d armWidget;

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

    private boolean driverDashboardInitiated = false;
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
        driversTab.addDouble("limelight sum x",()->MultiLimelight.getInstance().getAveragePos().getX());
        driversTab.addDouble("limelight sum y",()->MultiLimelight.getInstance().getAveragePos().getY());
        driversTab.addBoolean("limelight arr", ()->MultiLimelight.getInstance().getAll2DEstimates().contains(0));
        driversTab.addDouble("3d limelight y",()->MultiLimelight.getInstance().getAll3DEstimates().get(0).get().getFirst().getY());
        driversTab.addDouble("3d limelight x",()->MultiLimelight.getInstance().getAll3DEstimates().get(0).get().getFirst().getX());
        driversTab.addDouble("3d limelight z",()->MultiLimelight.getInstance().getAll3DEstimates().get(0).get().getFirst().getZ());

        //ready to place
        //todo check if at place and arm in pos
    }



}