class TelcoSystem {

    _COSTFORTYPE = Object.freeze({
        INTERNATIONAL: 2,
        NATIONAL: 1.5,
        LOCAL_NORMAL: 0.5,
        LOCAL_RH: 1
    });

    constructor(rushHour) {
        this.RUSHHOUR = rushHour;
        this.allCalls = [];
    }

    callMinutes(aDateTime, anotherDateTime) {
        try {
            const diffMs = anotherDateTime.getTime() - aDateTime.getTime();
            const diffMins = Math.round(diffMs / (1000 * 60));
            return diffMins;
        } catch (err) {
            console.log('Error in the dates');
            return 0;
        }
    }

    historicalNumberOfCalls() {
        return this.allCalls.length;
    }

    historicalTotalBilled() {
        return this.allCalls.reduce((acc, call) => acc + call.total_cost, 0);
    }

    registerNationalCallBetween(aDateTime, anotherDateTime, client) {
        let minutes = this.callMinutes(aDateTime, anotherDateTime);
        const total = minutes * this._COSTFORTYPE.NATIONAL;
        let oneCall = {
            client,
            typeCall: 'NATIONAL',
            minutes,
            total_cost: total
        }
        this.allCalls.push(oneCall);
    }

    registerInternationalCallBetween(aDateTime, anotherDateTime, client) {
        let mins = this.callMinutes(aDateTime, anotherDateTime);
        const total = mins * this._COSTFORTYPE.INTERNATIONAL;
        let oneCall = {
            client: client,
            typeCall: 'NATIONAL',
            minutes: mins,
            total_cost: total
        }
        this.allCalls.push(oneCall);
    }

    registerLocalCallBetween(aDateTime, anotherDateTime, client) {
        let total_Mins = 0;
        let total_cost = 0;
        if (this.RUSHHOUR) {
            for (let min = aDateTime; min < anotherDateTime; min.setUTCMinutes(min.getMinutes() + 1)) {
                total_cost += min.getUTCHours() === this.RUSHHOUR ? this._COSTFORTYPE.LOCAL_RH : this._COSTFORTYPE.LOCAL_NORMAL;
            }
        } else {
            total_Mins = this.calcMinutes(aDateTime, anotherDateTime);
            total_cost = total_Mins * this._COSTS.LOCAL_NORMAL;
        }

        let oneCall = {
            client: client,
            typeCall: 'LOCAL',
            minutes: total_Mins,
            total_cost
        }
        this.allCalls.push(oneCall);

    }

    numberOfCallsFor(client) {
        const callOfCliente = this.allCalls.filter(call => call.client === client);
        return callOfCliente.length;
    }

    totalBilledFor(client) {
        const billedClient = this.allCalls
            .filter(call => call.client === client)
            .reduce((acc, call) => acc + call.total_cost, 0);
        return billedClient;
    }
}

module.exports = TelcoSystem;
