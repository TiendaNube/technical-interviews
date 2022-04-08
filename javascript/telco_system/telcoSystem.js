class TelcoSystem {

    constructor(rushHour) {}

    historicalNumberOfCalls() {}

    historicalTotalBilled() {}

    registerNationalCallBetween(aDateTime, anotherDateTime, client) {}

    registerInternationalCallBetween(aDateTime, anotherDateTime, client) {}

    registerLocalCallBetween(aDateTime, anotherDateTime, client) {}

    numberOfCallsFor(client) {}

    totalBilledFor(client) {}
}

module.exports = TelcoSystem;