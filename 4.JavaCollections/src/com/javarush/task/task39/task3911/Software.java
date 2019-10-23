package com.javarush.task.task39.task3911;

import java.util.*;
import java.util.stream.Collectors;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if (!versionHistoryMap.containsKey(rollbackVersion) || rollbackVersion <= 0) return false;
        currentVersion = rollbackVersion;
        versionHistoryMap = versionHistoryMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey() <= rollbackVersion)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return true;
    }
}
