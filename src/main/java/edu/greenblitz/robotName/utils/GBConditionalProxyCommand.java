package edu.greenblitz.robotName.utils;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

import java.util.function.BooleanSupplier;

public class GBConditionalProxyCommand extends ProxyCommand {

    public GBConditionalProxyCommand(Command onTrue, Command onFalse, BooleanSupplier condition) {
        super(() -> getCommand(onTrue,onFalse,condition));
    }

    private static Command getCommand(Command onTrue, Command onFalse, BooleanSupplier condition) {
        if (condition.getAsBoolean())
            return onTrue;
        return onFalse;
    }
}
