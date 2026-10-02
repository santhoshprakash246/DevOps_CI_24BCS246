package com.bloodbank;

import java.util.HashMap;
import java.util.Map;

public class BloodBankService {
    private final Map<String, Integer> bloodStock = new HashMap<>();

    public void addBloodStock(String bloodGroup, int units) {
        String normalizedGroup = normalizeBloodGroup(bloodGroup);
        if (units <= 0) {
            throw new IllegalArgumentException("Units to add must be greater than zero.");
        }

        bloodStock.merge(normalizedGroup, units, Integer::sum);
    }

    public int getAvailableUnits(String bloodGroup) {
        return bloodStock.getOrDefault(normalizeBloodGroup(bloodGroup), 0);
    }

    public boolean issueBlood(String bloodGroup, int units) {
        String normalizedGroup = normalizeBloodGroup(bloodGroup);
        if (units <= 0) {
            throw new IllegalArgumentException("Units to issue must be greater than zero.");
        }

        int availableUnits = bloodStock.getOrDefault(normalizedGroup, 0);
        if (availableUnits < units) {
            return false;
        }

        bloodStock.put(normalizedGroup, availableUnits - units);
        return true;
    }

    private String normalizeBloodGroup(String bloodGroup) {
        if (bloodGroup == null || bloodGroup.isBlank()) {
            throw new IllegalArgumentException("Blood group must not be empty.");
        }
        return bloodGroup.trim().toUpperCase();
    }
}
