using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TelcoSystem;

namespace TelcoSystemTest
{
    [TestClass]
    public class TelcoSystemTests
    {
        private DateTime aDateTime = DateTime.Now;
        private DateTime anotherDateTime = DateTime.Now.AddDays(2);
        private double delta = 0.01;

        [TestMethod]
        public void TheHistoricalTotalBilledShouldBeZeroIfNoCallsWereMade() {
            Telco telcoSystem = new Telco();

            Assert.AreEqual(0, telcoSystem.HistoricalTotalBilled(), delta);
        }

        [TestMethod]
        public void AnInternationalCallOfTwoMinutesShouldCost4Pesos() {
            Telco telcoSystem = new Telco();

            telcoSystem.RegisterInternationalCallBetween(aDateTime, aDateTime.AddMinutes(2), "Juan");

            Assert.AreEqual(4, telcoSystem.HistoricalTotalBilled(), delta);
            Assert.AreEqual(1, telcoSystem.HistoricalNumberOfCalls());
        }

        [TestMethod]
        public void TestANationalCallOf100MinutesShouldCost150Pesos() {
            Telco telcoSystem = new Telco();
            telcoSystem.RegisterNationalCallBetween(aDateTime, aDateTime.AddMinutes(100), "Juan");

            Assert.AreEqual(150, telcoSystem.HistoricalTotalBilled(), delta);
            Assert.AreEqual(1, telcoSystem.HistoricalNumberOfCalls());
        }

        [TestMethod]
        public void TestALocalCallOf10MinutesDuringRushHourShouldCost10Pesos() {
            Telco telcoSystem = Telco.WithRushHourStartingAt(18);
            DateTime eighteenOClock = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, 18, 0, 0);

            telcoSystem.RegisterLocalCallBetween(eighteenOClock, eighteenOClock.AddMinutes(10), "Juan");

            Assert.AreEqual(10, telcoSystem.HistoricalTotalBilled(), delta);
            Assert.AreEqual(1, telcoSystem.HistoricalNumberOfCalls());
        }

        [TestMethod]
        public void TestALocalCallOf6MinutesDuringNotRushHourShouldCost3Pesos() {
            Telco telcoSystem = Telco.WithRushHourStartingAt(18);
            DateTime twoOClock = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, 14, 0, 0);

            telcoSystem.RegisterLocalCallBetween(twoOClock, twoOClock.AddMinutes(6), "Juan");

            Assert.AreEqual(3, telcoSystem.HistoricalTotalBilled(), delta);
            Assert.AreEqual(1, telcoSystem.HistoricalNumberOfCalls());
        }

        [TestMethod]
        public void TestALocalCallStartedInRushHourAndFinishedInNonRushHourShouldConsiderEachCost() {
            Telco telcoSystem = Telco.WithRushHourStartingAt(18);
            DateTime twoMinutesBeforeRushHour = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, 17, 58, 0);

            telcoSystem.RegisterLocalCallBetween(twoMinutesBeforeRushHour, twoMinutesBeforeRushHour.AddMinutes(6), "Juan");

            Assert.AreEqual(5, telcoSystem.HistoricalTotalBilled(), delta);
            Assert.AreEqual(1, telcoSystem.HistoricalNumberOfCalls());
        }

        [TestMethod]
        public void TestTwoInternationalCallsOf2MinutesShouldCost8PesosForJuan() {
           Telco telcoSystem = new Telco();

            telcoSystem.RegisterInternationalCallBetween(aDateTime, aDateTime.AddMinutes(2), "Juan");
            telcoSystem.RegisterInternationalCallBetween(anotherDateTime, anotherDateTime.AddMinutes(2), "Juan");

            telcoSystem.RegisterInternationalCallBetween(aDateTime, aDateTime.AddMinutes(20), "Pedro");
            telcoSystem.RegisterInternationalCallBetween(anotherDateTime, anotherDateTime.AddMinutes(12), "Pedro");

            Assert.AreEqual(8, telcoSystem.TotalBilledFor("Juan"), delta);

            Assert.AreEqual(2, telcoSystem.NumberOfCallsFor("Juan"));
            Assert.AreEqual(4, telcoSystem.HistoricalNumberOfCalls());
        }
    }
}
