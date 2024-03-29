package edu.greenblitz.robotName.utils.systemCheck;

import edu.greenblitz.robotName.commands.systemCheck.*;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.limelight.MultiLimelight;
import edu.greenblitz.robotName.subsystems.swerve.chassis.SwerveChassis;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.RoborioUtils;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.Map;

public class SystemCheck extends GBSubsystem {

    private static SystemCheck instance;

    private final SequentialCommandGroup commandGroup;

    private ShuffleboardTab tab;

    private double startingVoltage;

    private GenericEntry batteryStartingVoltageEntry;

    private SystemCheck() {
        this.startingVoltage = batteryStartingVoltageEntry.getDouble(SystemCheckBatteryConstants.DEFAULT_STARTING_VOLTAGE);
        this.commandGroup = new SequentialCommandGroup();
        initDashBoard();
        initPingableDashboard();
        initCheckCommands();
    }

    public static void init() {
        if (instance == null) {
            instance = new SystemCheck();
        }
    }

    public static SystemCheck getInstance() {
        init();
        return instance;
    }

    @Override
    public void periodic() {
        updateStartingVoltage(batteryStartingVoltageEntry.getDouble(SystemCheckBatteryConstants.DEFAULT_STARTING_VOLTAGE));
    }

    private void initDashBoard() {
        this.tab = Shuffleboard.getTab("System check");
        this.tab.addBoolean("is LL connected", () -> MultiLimelight.getInstance().isConnected());
        initBatteryWidget();
        initCANWidget();
    }

    private void initCANWidget() {
        ShuffleboardLayout CANDataList = tab.getLayout("CANBus", BuiltInLayouts.kGrid)
                .withPosition(1, 0).withSize(2, 2).withProperties(Map.of("Label position", "TOP", "Number of columns", 2, "Number of rows", 2));

        CANDataList.addBoolean("is CAN connected", RoborioUtils::isCANConnectedToRoborio)
                .withPosition(0, 0);

        CANDataList.addBoolean("is CAN utilization high:", this::isCANUtilizationHigh)
                .withPosition(0, 1);

        CANDataList.addDouble("CAN utilization %", RoborioUtils::getCANUtilization)
                .withPosition(1, 0);

        CANDataList.addBoolean("is canivore connected", PingableManager :: isCanivoreConnected)
                .withPosition(1, 1);
    }

    private void initBatteryWidget() {
        batteryStartingVoltageEntry = tab.add("set starting voltage", SystemCheckBatteryConstants.DEFAULT_STARTING_VOLTAGE)
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry();

        ShuffleboardLayout batteryDataList = tab.getLayout("System check", BuiltInLayouts.kList)
                .withPosition(0, 0).withSize(1, 5).withProperties(Map.of("Label position", "TOP", "Number of columns", 1, "Number of rows", 5));

        batteryDataList.addDouble("current voltage", () -> Battery.getInstance().getCurrentVoltage())
                .withPosition(0, 0);
        batteryDataList.addDouble("current current", () -> Battery.getInstance().getTotalCurrent())
                .withPosition(0, 1);
        batteryDataList.addDouble("battery inner resistance:", () -> getInnerBatteryResistance())
                .withPosition(0, 2);
        batteryDataList.addDouble("battery voltage drop:", () -> getVoltageDrop())
                .withPosition(0, 3);
        batteryDataList.addBoolean("battery overall good?",
                () -> getInnerBatteryResistance() < SystemCheckBatteryConstants.MAX_INNER_BATTERY_RESISTANCE &&
                        getVoltageDrop() < SystemCheckBatteryConstants.MAX_VOLTAGE_DROP
        ).withPosition(0, 4);
    }

    public void initPingableDashboard() {
        ShuffleboardLayout pingableDataList = tab.getLayout("pingable", BuiltInLayouts.kList)
                .withPosition(SystemCheckConstants.STARTING_X_OF_PINGABLE_WIDGET, SystemCheckConstants.STARTING_Y_OF_PINGABLE_WIDGET)
                .withSize(
                        PingableManager.getInstance().getPingableList().toArray().length / SystemCheckConstants.NUMBER_OF_CELLS_IN_PINGABLE_WIDGET,
                        PingableManager.getInstance().getPingableList().toArray().length + 1
                ).withProperties(Map.of("Label position", "TOP",
                        "Number of columns", PingableManager.getInstance().getPingableList().toArray().length,
                        "Number of rows", PingableManager.getInstance().getPingableList().toArray().length)
                );

        int columns = 1;
        for (IPingable pingable : PingableManager.getInstance().getPingableList()) {
            pingableDataList.addBoolean(
                    pingable.deviceName(),
                    () -> pingable.isConnected()
            ).withPosition(
                    columns / SystemCheckConstants.NUMBER_OF_CELLS_IN_PINGABLE_WIDGET,
                    columns
            );
            columns++;
        }
    }

    public void initCheckCommands() {
        add(
                new CheckWristToAngle(
                        Rotation2d.fromDegrees(SystemCheckConstants.CHECK_WRIST_ANGLE_DEGREES)
                ),
                "wrist to angle"
        );

        add(
                new CheckElbowToAngle(
                        Rotation2d.fromDegrees(SystemCheckConstants.CHECK_ELBOW_ANGLE_DEGREES)
                ),
                "elbow to angle"
        );

        add(
                new CheckPivotToAngle(
                        Rotation2d.fromDegrees(SystemCheckConstants.CHECK_PIVOT_ANGLE_DEGREES)
                ),
                "pivot to angle"
        );

        add(
                new CheckFlywheelByVelocity(),
                "flywheel",
                SystemCheckConstants.CHECK_FLYWHEEL_TIME_SECONDS
        );

        add(
                new CheckLiftUp(),
                "lift up"
        );

        add(
                new CheckRevereLift(),
                "reverse lift"
        );

        add(
                new CheckNoteToIntake(),
                "intake and exit beam-breaker"
        );

        add(
                new CheckNoteToShooter(),
                "funnel beam-breaker"
        );

        add(
                new CheckShooterToArm(),
                "shooter to arm"
        );

        add(
                new CheckRunFunnel(),
                "funnel"
        );

        add(
                new CheckRollClockwise(),
                "roller"
        );
    }

    public double getInnerBatteryResistance() {
        return calculateInnerBatteryResistance();
    }

    public double getStartingVoltage() {
        return startingVoltage;
    }

    private double calculateInnerBatteryResistance() {
        return (startingVoltage - Battery.getInstance().getCurrentVoltage()) / Battery.getInstance().getTotalCurrent();
    }

    public void add(SystemCheckCommand command, String checkName, double checkTime) {
        addToSequentialCommand(command.raceWith(new WaitCommand(checkTime)));
        tab.addBoolean(checkName, () -> command.hasFinished());
    }

    public void add(SystemCheckCommand command, String checkName) {
        add(command, checkName, SystemCheckConstants.DEFAULT_COMMAND_TIME_SECONDS);
    }

    public Command getRunCommands() {
        return commandGroup;
    }

    public boolean isCANUtilizationHigh() {
        return RoborioUtils.getCANUtilization() > SystemCheckConstants.MAX_CAN_UTILIZATION_IN_TESTS;
    }

    private void addToSequentialCommand(Command command) {
        commandGroup.addCommands(command);
    }

    private void updateStartingVoltage(double startingVoltage) {
        this.startingVoltage = startingVoltage;

    }

    public double getVoltageDrop() {
        return getStartingVoltage() - Battery.getInstance().getCurrentVoltage();
    }
}
