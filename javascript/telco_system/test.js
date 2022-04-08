let expect = require('chai').expect;
let TelcoSystem = require('./telcoSystem');

describe('TelcoSystem', function () {

    let aDateTime;

    before(function () {
        aDateTime = new Date();
    });

    describe('calls billing', function () {

        it('should not bill if no calls were made', function () {
            let telcoSystem = new TelcoSystem();

            expect(0).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(0).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });

        it('should support billing for different clients', function () {
            let telcoSystem = new TelcoSystem();

            let twoMinutesAfterStart = new Date(aDateTime);
            twoMinutesAfterStart.setUTCMinutes(aDateTime.getMinutes() + 2);

            telcoSystem.registerInternationalCallBetween(aDateTime, twoMinutesAfterStart, "Juan");

            let fourMinutesAfterStart = new Date(twoMinutesAfterStart);
            fourMinutesAfterStart.setUTCMinutes(twoMinutesAfterStart.getMinutes() + 2);

            telcoSystem.registerInternationalCallBetween(aDateTime, fourMinutesAfterStart, "Juan");
            telcoSystem.registerInternationalCallBetween(aDateTime, fourMinutesAfterStart, "Pedro");

            expect(12).to.be.equal(telcoSystem.totalBilledFor("Juan"));
            expect(2).to.be.equal(telcoSystem.numberOfCallsFor("Juan"));

            expect(8).to.be.equal(telcoSystem.totalBilledFor("Pedro"));
            expect(1).to.be.equal(telcoSystem.numberOfCallsFor("Pedro"));

            expect(20).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(3).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });

    });
    
    describe('national calls', function () {

        it('should bill 150 pesos for a 100 minutes call', function () {
            let telcoSystem = new TelcoSystem();

            let anotherDateTime = new Date(aDateTime);
            anotherDateTime.setUTCMinutes(aDateTime.getMinutes() + 100);

            telcoSystem.registerNationalCallBetween(aDateTime, anotherDateTime, "Juan");

            expect(150).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(1).to.be.equal(telcoSystem.historicalNumberOfCalls());
        })

    });

    describe('international calls', function () {

        it('should bill 4 pesos for a 2 minutes call', function () {
            let telcoSystem = new TelcoSystem();

            let anotherDateTime = new Date(aDateTime);
            anotherDateTime.setUTCMinutes(aDateTime.getMinutes() + 2);

            telcoSystem.registerInternationalCallBetween(aDateTime, anotherDateTime, "Juan");

            expect(4).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(1).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });
    });

    describe('local calls', function () {

        it('should bill 10 pesos for a 10 minutes call during rush hour', function () {
            let telcoSystem = new TelcoSystem(18);

            let eighteenOClock = new Date(aDateTime);
            eighteenOClock.setUTCHours(18);
            eighteenOClock.setUTCMinutes(0);

            let anotherDateTime = new Date(eighteenOClock);
            anotherDateTime.setUTCMinutes(eighteenOClock.getMinutes() + 10);

            telcoSystem.registerLocalCallBetween(eighteenOClock, anotherDateTime, "Juan");

            expect(10).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(1).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });

        it('should bill 3 pesos for a 6 minutes call during not rush hour', function () {
            let telcoSystem = new TelcoSystem(18);

            let twoOClock = new Date(aDateTime);
            twoOClock.setUTCHours(14);
            twoOClock.setUTCMinutes(0);

            let anotherDateTime = new Date(twoOClock);
            anotherDateTime.setUTCMinutes(twoOClock.getMinutes() + 6);

            telcoSystem.registerLocalCallBetween(twoOClock, anotherDateTime, "Juan");

            expect(3).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(1).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });

        it('should consider each cost for a call started before rush hour and finished in rush hour', function () {
            let telcoSystem = new TelcoSystem(18);

            let twoMinutesBeforeRushHour = new Date(aDateTime);
            twoMinutesBeforeRushHour.setUTCHours(17);
            twoMinutesBeforeRushHour.setUTCMinutes(58);

            let anotherDateTime = new Date(twoMinutesBeforeRushHour);
            anotherDateTime.setUTCMinutes(twoMinutesBeforeRushHour.getMinutes() + 6);

            telcoSystem.registerLocalCallBetween(twoMinutesBeforeRushHour, anotherDateTime, "Juan");

            expect(5).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(1).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });

        it('should consider each cost for a call started after rush hour and finished before rush hour', function () {
            let telcoSystem = new TelcoSystem(18);

            let twoMinutesBeforeRushHourEnds = new Date(aDateTime);
            twoMinutesBeforeRushHourEnds.setUTCHours(18);
            twoMinutesBeforeRushHourEnds.setUTCMinutes(58);

            let anotherDateTime = new Date(twoMinutesBeforeRushHourEnds);
            anotherDateTime.setUTCMinutes(twoMinutesBeforeRushHourEnds.getMinutes() + 6);

            telcoSystem.registerLocalCallBetween(twoMinutesBeforeRushHourEnds, anotherDateTime, "Juan");

            expect(4).to.be.equal(telcoSystem.historicalTotalBilled());
            expect(1).to.be.equal(telcoSystem.historicalNumberOfCalls());
        });

    });

});