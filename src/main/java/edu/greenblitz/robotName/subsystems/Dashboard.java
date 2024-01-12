package edu.greenblitz.robotName.subsystems;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumber;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.util.ArrayList;

public class Dashboard extends GBSubsystem {
    private static Dashboard instance;
    public final static int NUMBER_OF_MOTORS = 2;
    public static String prototypesTabTitle = "Prototypes";
    public static String id = "id";
    public static String power = "power";
    public static double DEFAULT_ID = 999;
    public static ArrayList<SendableChooser<motorType>> types = new ArrayList<>();

    public enum motorType {
        SPARK_MAX,
        TALON_SRX,
        TALON_FX
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

    private Dashboard() {
        prototypesDashboard();
    }

    public void prototypesDashboard() {
        createMotorsWidgets();
    }

    public void createMotorsWidgets() {
        for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
            TunableNumber id = new TunableNumber(Dashboard.id + i, Dashboard.prototypesTabTitle, DEFAULT_ID);
            TunableNumber power = new TunableNumber(Dashboard.power + i, Dashboard.prototypesTabTitle);
            SendableChooser<motorType> type = new SendableChooser<>();
            type.setDefaultOption("SPARK_MAX", motorType.SPARK_MAX);
            type.addOption("TALON_SRX", motorType.TALON_SRX);
            type.addOption("TALON_FX", motorType.TALON_FX);
            types.add(i, type);
            Shuffleboard.getTab(prototypesTabTitle).add(type);
            TunableNumberManager.getInstance().addTunableNumber(Dashboard.id + i, id);
            TunableNumberManager.getInstance().addTunableNumber(Dashboard.power + i, power);
        }
    }
}
