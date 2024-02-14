package edu.greenblitz.robotName.commands.systemCheck;

public interface IPingable {
    boolean isConnected();

    String deviceName();
}
