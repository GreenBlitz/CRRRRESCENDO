package edu.greenblitz.robotName.utils.systemCheck;

import java.util.LinkedList;

public class PingableManager {

    private static PingableManager instance;

    private final LinkedList<IPingable> pingableList;

    private static boolean isCanivoreConnected;

    private PingableManager() {
        this.pingableList = new LinkedList<>();
        isCanivoreConnected = false;
    }

    public static void init() {
        if (instance == null) {
            instance = new PingableManager();
        }
    }

    public static PingableManager getInstance() {
        init();
        return instance;
    }

    public void add(IPingable pingable) {
        add(pingable, false);
    }

    public void add(IPingable pingable, boolean isCanivore) {
        pingableList.add(pingable);
        isCanivoreConnected |= isCanivore;
    }

    public LinkedList<IPingable> getPingableList() {
        return pingableList;
    }

    public static boolean isCanivoreConnected() {
        return isCanivoreConnected;
    }

    public boolean isAllConnected() {
        boolean isAllConnected = true;
        for (IPingable pingable : getPingableList()) {
            isAllConnected &= pingable.isConnected();
        }
        return isAllConnected;
    }
}
