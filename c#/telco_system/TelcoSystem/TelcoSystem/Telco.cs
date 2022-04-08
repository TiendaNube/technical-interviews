using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TelcoSystem
{
    public class Telco
    {

        public Telco()
        {
            throw new NotImplementedException();
        }

        public Telco(int rushHour)
        {
            throw new NotImplementedException();
        }

        public double HistoricalTotalBilled()
        {
            throw new NotImplementedException();
        }

        public void RegisterInternationalCallBetween(DateTime start, DateTime end, String clientName) {
            throw new NotImplementedException();
        }

        public void RegisterNationalCallBetween(DateTime start, DateTime end, String clientName) {
            throw new NotImplementedException();
        }

        public void RegisterLocalCallBetween(DateTime start, DateTime end, String clientName) {
            throw new NotImplementedException();
        }

        public static Telco WithRushHourStartingAt(int hourOfDay) {
            return new Telco(hourOfDay);
        }

        public float TotalBilledFor(String clientName) {
            throw new NotImplementedException();
        }

        public int HistoricalNumberOfCalls() {
            throw new NotImplementedException();
        }

        public int NumberOfCallsFor(String clientName) {
            throw new NotImplementedException();
        }

    }
}
