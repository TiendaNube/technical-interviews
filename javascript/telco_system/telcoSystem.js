CALL_BILL = Object.freeze({
    local: {
        regular: 0.5,
        rush: 1
    },
    national: 1.5,
    international: 2,
})

class TelcoSystem {

    constructor(rushHour) {
        this.rushHour = rushHour;
        this.registeredCalls = [];
    }

    historicalNumberOfCalls() {
        return this.registeredCalls.length;
    }

    historicalTotalBilled() {
        return this.registeredCalls.reduce((total, call) => total + call.billing, 0);
    }

    registerNationalCallBetween(aDateTime, anotherDateTime, client) {
        const callDuration = (anotherDateTime.getTime() - aDateTime.getTime()) / (1000 * 60);
        this.registeredCalls.push({
            client,
            callDuration,
            billing: callDuration * CALL_BILL.national
        })
    }

    registerInternationalCallBetween(aDateTime, anotherDateTime, client) {
        const callDuration = (anotherDateTime.getTime() - aDateTime.getTime()) / (1000 * 60);
        this.registeredCalls.push({
            client,
            callDuration,
            billing: callDuration * CALL_BILL.international
        })
    }

    registerLocalCallBetween(aDateTime, anotherDateTime, client) {
        const callDuration = (anotherDateTime.getTime() - aDateTime.getTime()) / (1000 * 60);
        
        let totalBilling = 0;
        for (let i = aDateTime; i < anotherDateTime; i.setMinutes(i.getMinutes() + 1)) {
            const currentPrice = i.getUTCHours() === this.rushHour ? CALL_BILL.local.rush : CALL_BILL.local.regular;
            totalBilling += currentPrice;
        }
        
        this.registeredCalls.push({
            client,
            callDuration,
            billing: totalBilling
        })
    }

    numberOfCallsFor(client) {
        return this.registeredCalls.filter(call => call.client === client).length;
    }

    totalBilledFor(client) {
        return this.registeredCalls
            .filter(call => call.client === client)
            .reduce((total, call) => total + call.billing, 0)
    }
}

module.exports = TelcoSystem;