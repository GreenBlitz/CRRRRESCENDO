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
	public static String numberOfMotors = "NumberOfMotors";
	
	public void prototypesDashboard(){
		ShuffleboardTab prototypesTab = Shuffleboard.getTab(Dashboard.prototypesTabName);
		
		AutoSetterTunableNumber numberOfMotors = new AutoSetterTunableNumber(
				Dashboard.numberOfMotors,
				Dashboard.prototypesTabName,
				aDouble -> createMotorsTabs(aDouble)
				);
		TunableNumberManager.getInstance().addTunableNumber(Dashboard.numberOfMotors, numberOfMotors);
		
		
		//check
		prototypesTab.addDouble("Motor1Check", () ->
				TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id+0).getValue());
		prototypesTab.addDouble("Motor2Check", () ->
				TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id+1).getValue());
		prototypesTab.addDouble("Motor3Check", () ->
				TunableNumberManager.getInstance().getTunableNumberForKey(Dashboard.id+2).getValue());
		
	}
	public static String id = "id";
	public static String power = "power";
	public static String type = "type";
	public void createMotorsTabs(double numberOfMotors){
		for (int i  = 0; i<numberOfMotors; i++){
			TunableNumber id = new TunableNumber(Dashboard.id+ i, Dashboard.prototypesTabName);
			TunableNumber power = new TunableNumber(Dashboard.power+ i, Dashboard.prototypesTabName);
			TunableNumber type = new TunableNumber(Dashboard.type+ i, Dashboard.prototypesTabName);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.id+ i, id);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.power+ i, power);
			TunableNumberManager.getInstance().addTunableNumber(Dashboard.type+ i, type);
		}
	}
	
	@Override
	public void periodic() {
		super.periodic();
	}
}
