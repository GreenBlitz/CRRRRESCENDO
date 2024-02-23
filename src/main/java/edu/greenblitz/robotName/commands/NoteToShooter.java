package edu.greenblitz.robotName.commands;

import edu.greenblitz.robotName.subsystems.intake.Intake;
import edu.greenblitz.robotName.subsystems.shooter.funnel.Funnel;
import edu.greenblitz.robotName.subsystems.shooter.funnel.FunnelConstants;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NoteToShooter extends GBCommand {

    private Funnel funnel;

    private Intake intake;
    
    private Timer timer;

    public NoteToShooter() {
        funnel = Funnel.getInstance();
        intake = Intake.getInstance();
        require(intake);
        require(funnel);
        timer = new Timer();
    }
    
    @Override
    public void initialize() {
        timer.restart();
    }
    
    @Override
    public void execute() {
        if (timer.get() > FunnelConstants.NOTE_TO_SHOOTER_DELAY_TIME) {
            funnel.rollIn();
        }
        intake.rollIn();
    }

    @Override
    public boolean isFinished() {
        return funnel.isObjectIn();
    }

    @Override
    public void end(boolean interrupted) {
        funnel.stop();
        intake.stop();
    }
}