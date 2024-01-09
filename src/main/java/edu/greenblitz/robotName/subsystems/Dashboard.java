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
	
	
	static String prototypesTabName = "Prototypes";
	static String numberOfMotors = "NumberOfMotors";
	
	public void prototypesDashboard(){
		ShuffleboardTab prototypesTab = Shuffleboard.getTab(Dashboard.prototypesTabName);
		
		AutoSetterTunableNumber numberOfMotors = new AutoSetterTunableNumber(
				Dashboard.numberOfMotors,
				Dashboard.prototypesTabName,
				aDouble -> createMotorsTabs(aDouble)
				);
		TunableNumberManager.getInstance().addTunableNumber(Dashboard.numberOfMotors, numberOfMotors);
		
		
	}
	
	public void createMotorsTabs(double numberOfMotors){
		for (int i  = 0; i<numberOfMotors; i++){
			TunableNumber id = new TunableNumber("id"+ i, Dashboard.prototypesTabName);
			TunableNumber power = new TunableNumber("power"+ i, Dashboard.prototypesTabName);
			TunableNumber type = new TunableNumber("type"+ i, Dashboard.prototypesTabName);
			TunableNumberManager.getInstance().addTunableNumber("id"+ i, id);
			TunableNumberManager.getInstance().addTunableNumber("power"+ i, power);
			TunableNumberManager.getInstance().addTunableNumber("type"+ i, type);
		}
	}
	
	@Override
	public void periodic() {
		super.periodic();
	}
}
