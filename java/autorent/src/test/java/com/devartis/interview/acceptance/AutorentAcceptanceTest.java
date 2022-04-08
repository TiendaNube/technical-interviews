package com.devartis.interview.acceptance;

import com.devartis.interview.AutorentSystem;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static com.devartis.interview.utils.TestUtils.*;
import static org.junit.Assert.assertEquals;

public class AutorentAcceptanceTest {

    private AutorentSystem autorent;
    private LocalDateTime aMondayPickupDay = LocalDateTime.of(2015, 4, 6, 12, 0, 0, 0);
    private LocalDateTime aFridayPickupDay = aMondayPickupDay.plusDays(4);
    private LocalDateTime aSaturdayPickupDay = aFridayPickupDay.plusDays(1);

    @Before
    public void setUp() {
        autorent = createSystemUsingDataFromTheExerciseAssignment();
    }

    @Test
    public void costOfReturningDusterForTwoDaysOnTimeWithFullTank() {
        assertEquals(DUSTER_RENT_COST * 2,
                autorent.costFor(
                        DUSTER_LICENSE_PLATE,
                        aMondayPickupDay, 2, aMondayPickupDay.plusDays(2),
                        DUSTER_FULL_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningAFordKaForThreeDaysAndThreeHoursLateWithFullTank() {
        assertEquals(FORD_KA_RENT_COST * 3 + FORD_KA_RENT_COST,
                autorent.costFor(
                        FORD_KA_LICENSE_PLATE,
                        aMondayPickupDay, 3, aMondayPickupDay.plusDays(3).plusHours(3),
                        KA_FULL_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningFordKaForTwoDaysOnTimeWitTankLevelBetween0and25Percent() {
        assertEquals(FORD_KA_RENT_COST * 2 + 400,
                autorent.costFor(
                        FORD_KA_LICENSE_PLATE,
                        aMondayPickupDay, 2, aMondayPickupDay.plusDays(2),
                        KA_20_PERCENT_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningFordKaForTwoDaysOnTimeWitTankLevelBetween25and50Percent() {
        assertEquals(FORD_KA_RENT_COST * 2 + 300,
                autorent.costFor(
                        FORD_KA_LICENSE_PLATE,
                        aMondayPickupDay, 2, aMondayPickupDay.plusDays(2),
                        KA_40_PERCENT_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningFordKaForTwoDaysOnTimeWitTankLevelBetween50and75Percent() {
        assertEquals(FORD_KA_RENT_COST * 2 + 200,
                autorent.costFor(
                        FORD_KA_LICENSE_PLATE,
                        aMondayPickupDay, 2, aMondayPickupDay.plusDays(2),
                        KA_60_PERCENT_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningFordKaForTwoDaysOnTimeWitTankLevelBetween75and100Percent() {
        assertEquals(FORD_KA_RENT_COST * 2 + 100,
                autorent.costFor(
                        FORD_KA_LICENSE_PLATE,
                        aMondayPickupDay, 2, aMondayPickupDay.plusDays(2),
                        KA_80_PERCENT_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningLogan1DayWithMoreThanAllowedKilometers() {
        int exceededKms = 85;
        assertEquals(LOGAN_RENT_COST + exceededKms * LOGAN_COST_PER_EXCEEDED_KM,
                autorent.costFor(
                        LOGAN_LICENSE_PLATE,
                        aMondayPickupDay, 1, aMondayPickupDay.plusDays(1),
                        LOGAN_FULL_TANK, MAX_KM_ALLOWED_PER_DAY + exceededKms),
                DELTA);
    }

    @Test
    public void costOfLoganFromSaturdayToSundayInclusive() {
        assertEquals(LOGAN_RENT_COST * 1.15 * 2,
                autorent.costFor(
                        LOGAN_LICENSE_PLATE,
                        aSaturdayPickupDay, 2, aSaturdayPickupDay.plusDays(2),
                        LOGAN_FULL_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfLoganFromSaturdayToMonday() {
        assertEquals(LOGAN_RENT_COST * 1.15 * 2 + LOGAN_RENT_COST,
                autorent.costFor(
                        LOGAN_LICENSE_PLATE,
                        aSaturdayPickupDay, 3, aSaturdayPickupDay.plusDays(3),
                        LOGAN_FULL_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfLoganFromFridayToMonday() {
        assertEquals(LOGAN_RENT_COST * 2 + LOGAN_RENT_COST * 1.15 * 2,
                autorent.costFor(
                        LOGAN_LICENSE_PLATE,
                        aFridayPickupDay, 4, aFridayPickupDay.plusDays(4),
                        LOGAN_FULL_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfClioFromMondayTo30DaysOn() {
        assertEquals(CLIO_RENT_COST * 22 + CLIO_RENT_COST * 1.15 * 8,
                autorent.costFor(
                        CLIO_LICENSE_PLATE,
                        aMondayPickupDay, 30, aMondayPickupDay.plusDays(30),
                        CLIO_FULL_TANK, TRAVELLED_300_KMS),
                DELTA);
    }

    @Test
    public void costOfReturningClioForTwoDaysWithAllViolations() {
        int exceededKms = 15;
        assertEquals(CLIO_RENT_COST * 2 + CLIO_RENT_COST + exceededKms * CLIO_RENT_COST_PER_EXCEEDED_KM + 100,
                autorent.costFor(
                        CLIO_LICENSE_PLATE,
                        aMondayPickupDay, 2, aMondayPickupDay.plusDays(2).plusHours(4),
                        CLIO_80_PERCENT_TANK, MAX_KM_ALLOWED_PER_DAY * 2 + exceededKms),
                DELTA);
    }
}
