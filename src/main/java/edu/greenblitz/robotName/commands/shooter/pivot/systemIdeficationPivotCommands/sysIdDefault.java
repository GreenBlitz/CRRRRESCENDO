package edu.greenblitz.robotName.commands.shooter.pivot.systemIdeficationPivotCommands;

import edu.greenblitz.robotName.subsystems.Shooter.Pivot.MotorPivot.SystemIdenficationPivot.SystemIdenficationPivot;
import edu.greenblitz.robotName.utils.GBCommand;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.DoubleSupplier;

import static edu.wpi.first.wpilibj2.command.Commands.run;

public class sysIdDefault extends GBCommand {

	SystemIdenficationPivot pivot;
//
//	public Command joystickDriveCommand(DoubleSupplier output) {
//		pivot = SystemIdenficationPivot.getInstance();
//		return run(()->pivot.setPower().setControl(pivot.getM_Joystick().withOutput(output.getAsDouble())));
//	}
}
