package com.bloodbank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BloodBankServiceTest {
    private BloodBankService bloodBankService;

    @BeforeEach
    void setUp() {
        bloodBankService = new BloodBankService();
    }

    @Test
    void addsBloodStock() {
        bloodBankService.addBloodStock("A+", 5);

        assertEquals(5, bloodBankService.getAvailableUnits("A+"));
    }

    @Test
    void checksAvailableStockWithoutCaseSensitivity() {
        bloodBankService.addBloodStock("o-", 3);

        assertEquals(3, bloodBankService.getAvailableUnits("O-"));
    }

    @Test
    void issuesAvailableBloodAndReducesStock() {
        bloodBankService.addBloodStock("B+", 6);

        assertTrue(bloodBankService.issueBlood("B+", 2));
        assertEquals(4, bloodBankService.getAvailableUnits("B+"));
    }

    @Test
    void doesNotIssueUnavailableBlood() {
        bloodBankService.addBloodStock("AB+", 1);

        assertFalse(bloodBankService.issueBlood("AB+", 2));
        assertEquals(1, bloodBankService.getAvailableUnits("AB+"));
    }
}
