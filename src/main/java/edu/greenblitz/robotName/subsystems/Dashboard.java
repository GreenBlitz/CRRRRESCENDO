package edu.greenblitz.robotName.subsystems;

import edu.greenblitz.robotName.utils.GBSubsystem;
import edu.greenblitz.robotName.utils.tuneableNumber.AutoSetterTunableNumber;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumber;
import edu.greenblitz.robotName.utils.tuneableNumber.TunableNumberManager;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

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
		ShuffleboardTab prototypesTab = Shuffleboard.getTab(Dashboard.prototypesTabName);

		createMotorsTabs();
	}

	public static String id = "id";
	public static String power = "power";
	public static String type = "type";

	public void createMotorsTabs(){
		for (int i  = 0; i<4; i++){
			TunableNumber id = new TunableNumber(Dashboard.id+ i, Dashboard.prototypesTabName);
			TunableNumber power = new TunableNumber(Dashboard.power+ i, Dashboard.prototypesTabName);
			TunableNumber type = new TunableNumber(Dashboard.type+ i, Dashboard.prototypesTabName);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.id+ i, id);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.power+ i, power);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.type+ i, type);
		}
	}
}
