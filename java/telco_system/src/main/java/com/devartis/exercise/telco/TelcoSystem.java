package com.devartis.exercise.telco;

import org.joda.time.DateTime;

/**
 * The only purpose of the behavior defined by the protocol (set of methods that implements) of this
 * class is to explain the problem and different cases of the com.devartis.exercise.telco.TelcoSystem.
 *
 * We expect from the candidate a model that solves the problem.
 *
 *
 */
public class TelcoSystem {

    public TelcoSystem() {
        throw new NotImplementedYetException();
    }

    public TelcoSystem(int peakHour) {
        throw new NotImplementedYetException();
    }

    public double historicalTotalBilled() {
        throw new NotImplementedYetException();
    }

    public void registerInternationalCallBetween(DateTime start, DateTime end, String clientName) {
        throw new NotImplementedYetException();
    }

    public void registerNationalCallBetween(DateTime start, DateTime end, String clientName) {
        throw new NotImplementedYetException();
    }

    public void registerLocalCallBetween(DateTime start, DateTime end, String clientName) {
        throw new NotImplementedYetException();
    }

    public static TelcoSystem withPeakHourStartingAt(int hourOfDay) {
        return new TelcoSystem(hourOfDay);
    }

    public float totalBilledFor(String clientName) {
         throw new NotImplementedYetException();
    }

    public int historicalNumberOfCalls() {
        throw new NotImplementedYetException();
    }

    public int numberOfCallsFor(String clientName) {
        throw new NotImplementedYetException();
    }
}
