package edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands;

import edu.greenblitz.robotName.commands.shooter.pivot.PivotCommand;
import edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot.SystemIdenficationPivot;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class sysIdQuasistatic extends GBCommand {

	SystemIdenficationPivot pivot;

//	public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
//		pivot = SystemIdenficationPivot.getInstance();
//		return pivot.getM_SysIdRoutine().quasistatic(direction);
//	}

}
