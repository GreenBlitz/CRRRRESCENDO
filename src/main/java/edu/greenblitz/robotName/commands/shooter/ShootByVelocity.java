package edu.greenblitz.robotName.commands.shooter;

public class ShootByVelocity extends ShooterCommand{

	private double EPSILON = 50;
	private double SHOOTING_SPEED_TIME = 1;

	private double inShootingSpeed;
	private double velocity;

	public ShootByVelocity(double velocity){
		this.velocity = velocity;
	}
	public boolean isFlywheelInVelocity (){
		return Math.abs(shooter.getVelocity() - velocity) < EPSILON;
	}

	@Override
	public void initialize() {
		inShootingSpeed = 0;
	}

	@Override
	public void execute() {
		shooter.setVelocity(velocity);

		if(isFlywheelInVelocity()){
			inShootingSpeed++;
		}else{
			inShootingSpeed = 0;
		}


		shooter.setPreparedToShoot(inShootingSpeed > SHOOTING_SPEED_TIME);
	}

	@Override
	public void end(boolean interrupted) {
		shooter.stop();
	}
}
