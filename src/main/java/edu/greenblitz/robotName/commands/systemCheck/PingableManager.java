package edu.greenblitz.robotName.commands.systemCheck;

import edu.greenblitz.robotName.utils.systemCheck.IPingable;

import java.util.LinkedList;

public class PingableManager {
    private static PingableManager instance;
    private final LinkedList<edu.greenblitz.robotName.utils.systemCheck.IPingable> pingableList;

    private PingableManager() {
        this.pingableList = new LinkedList<>();
    }

    public static PingableManager getInstance() {
        if (instance == null) {
            instance = new PingableManager();
        }
        return instance;
    }

    public void add(edu.greenblitz.robotName.utils.systemCheck.IPingable pingable) {
        this.pingableList.add(pingable);
    }

    public LinkedList<edu.greenblitz.robotName.utils.systemCheck.IPingable> getPingableList() {
        return this.pingableList;
    }

    public boolean isAllConnected() {
        boolean isAllConnected = true;
        for (IPingable pingable : getPingableList()) {
            isAllConnected &= pingable.isConnected();
        }
        return isAllConnected;
    }
}
