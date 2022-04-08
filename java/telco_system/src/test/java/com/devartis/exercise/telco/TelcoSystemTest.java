package com.devartis.exercise.telco;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TelcoSystemTest {

    private DateTime aDateTime = DateTime.now();
    private DateTime anotherDateTime = aDateTime.plusDays(2);
    private double delta = 0.01;

    @Test
    public void theHistoricalTotalBilledShouldBeZeroIfNoCallsWereMade() {
        TelcoSystem telcoSystem = new TelcoSystem();

        assertEquals(0, telcoSystem.historicalTotalBilled(), delta);
    }

    @Test
    public void anInternationalCallOfTwoMinutesShouldCost4pesos() {
        TelcoSystem telcoSystem = new TelcoSystem();

        telcoSystem.registerInternationalCallBetween(aDateTime, aDateTime.plusMinutes(2), "Juan");

        assertEquals(4, telcoSystem.historicalTotalBilled(), delta);
        assertEquals(1, telcoSystem.historicalNumberOfCalls());
    }

    @Test
    public void aNationalCallOf100MinutesShouldCost150pesos() {
        TelcoSystem telcoSystem = new TelcoSystem();
        telcoSystem.registerNationalCallBetween(aDateTime, aDateTime.plusMinutes(100), "Juan");

        assertEquals(150, telcoSystem.historicalTotalBilled(), delta);
        assertEquals(1, telcoSystem.historicalNumberOfCalls());
    }

    @Test
    public void aLocalCallOf10MinutesDuringPeakHourShouldCost10pesos() {
        TelcoSystem telcoSystem = TelcoSystem.withPeakHourStartingAt(18);
        DateTime eighteenOClock = DateTime.now().withHourOfDay(18).withMinuteOfHour(0);

        telcoSystem.registerLocalCallBetween(eighteenOClock, eighteenOClock.plusMinutes(10), "Juan");

        assertEquals(10, telcoSystem.historicalTotalBilled(), delta);
        assertEquals(1, telcoSystem.historicalNumberOfCalls());
    }

    @Test
    public void aLocalCallOf6MinutesDuringNotPeakHourShouldCost3pesos() {
        TelcoSystem telcoSystem = TelcoSystem.withPeakHourStartingAt(18);
        DateTime twoOClock = DateTime.now().withHourOfDay(14).withMinuteOfHour(0);

        telcoSystem.registerLocalCallBetween(twoOClock, twoOClock.plusMinutes(6), "Juan");

        assertEquals(3, telcoSystem.historicalTotalBilled(), delta);
        assertEquals(1, telcoSystem.historicalNumberOfCalls());
    }

    @Test
    public void aLocalCallStartedBeforePeakHourAndFinishedInPeakHourShouldConsiderEachCost() {
        TelcoSystem telcoSystem = TelcoSystem.withPeakHourStartingAt(18);
        DateTime twoMinutesBeforePeakHour = DateTime.now().withHourOfDay(17).withMinuteOfHour(58);

        telcoSystem.registerLocalCallBetween(twoMinutesBeforePeakHour, twoMinutesBeforePeakHour.plusMinutes(6), "Juan");

        assertEquals(5, telcoSystem.historicalTotalBilled(), delta);
        assertEquals(1, telcoSystem.historicalNumberOfCalls());
    }

    @Test
    public void aLocalCallStartedAfterPeakHourAndFinishedInNonPeakHourShouldConsiderEachCost() {
        TelcoSystem telcoSystem = TelcoSystem.withPeakHourStartingAt(18);
        DateTime twoMinutesBeforePickHour = DateTime.now().withHourOfDay(18).withMinuteOfHour(58);

        telcoSystem.registerLocalCallBetween(twoMinutesBeforePickHour, twoMinutesBeforePickHour.plusMinutes(6), "Juan");

        assertEquals(4, telcoSystem.historicalTotalBilled(), delta);
        assertEquals(1, telcoSystem.historicalNumberOfCalls());
    }
    
    @Test
    public void twoInternationalCallsOf2MinutesShouldCost8PesosForJuan() {
       TelcoSystem telcoSystem = new TelcoSystem();

        telcoSystem.registerInternationalCallBetween(aDateTime, aDateTime.plusMinutes(2), "Juan");
        telcoSystem.registerInternationalCallBetween(anotherDateTime, anotherDateTime.plusMinutes(2), "Juan");

        telcoSystem.registerInternationalCallBetween(aDateTime, aDateTime.plusMinutes(20), "Pedro");
        telcoSystem.registerInternationalCallBetween(anotherDateTime, anotherDateTime.plusMinutes(12), "Pedro");

        assertEquals(8, telcoSystem.totalBilledFor("Juan"), delta);

        assertEquals(2, telcoSystem.numberOfCallsFor("Juan"));
        assertEquals(4, telcoSystem.historicalNumberOfCalls());
    }
}