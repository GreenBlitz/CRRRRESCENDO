package edu.greenblitz.robotName.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.greenblitz.robotName.subsystems.prototypes.Prototypes;
import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.tuneableNumber.AutoSetterTunableNumber;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumber;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static edu.greenblitz.robotName.subsystems.Dashboard.motorType.SPARK_MAX;
import static edu.greenblitz.robotName.subsystems.Dashboard.motorType.TALON_SRX;

public class Dashboard extends GBSubsystem {
	private static Dashboard instance;
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
	
	
	public static String prototypesTabName = "Prototypes";
	
	public void prototypesDashboard(){
		createMotorsTabs();
	}

	public static String id = "id";
	public static String power = "power";
	public static double DEFAULT_ID = 999;
	public enum motorType{
		SPARK_MAX,
		TALON_SRX,
		TALON_FX
	}
	public static int NUMBER_OF_MOTORS = 2;
	public static ArrayList<SendableChooser<motorType>> types = new ArrayList<>();

	public void createMotorsTabs(){
		for (int i  = 0; i<NUMBER_OF_MOTORS; i++){
			TunableNumber id = new TunableNumber(Dashboard.id+ i, Dashboard.prototypesTabName,DEFAULT_ID);
			TunableNumber power = new TunableNumber(Dashboard.power+ i, Dashboard.prototypesTabName);
			SendableChooser<motorType> type = new SendableChooser<>();
			type.setDefaultOption("SPARK_MAX", motorType.SPARK_MAX);
			type.addOption("TALON_SRX", motorType.TALON_SRX);
			type.addOption("TALON_FX", motorType.TALON_FX);
			Shuffleboard.getTab(prototypesTabName).add(type);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.id+ i, id);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.power+ i, power);
		}
	}
}
