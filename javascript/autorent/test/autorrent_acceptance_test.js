describe("Autorent Acceptance Tests", function () {
    var autorent;
    var aMondayPickupDay;
    var aFridayPickupDay;
    var aSaturdayPickupDay;

    beforeEach(function () {
        autorent = createSystemUsingDataFromTheExerciseAssignment();
        aMondayPickupDay = moment("2015040612", "YYYYMMDDhh");
        aFridayPickupDay = aMondayPickupDay.add(4, 'days');
        aSaturdayPickupDay = aFridayPickupDay.add(1, 'days');
    });

    it("create system using data from the exercise assignment", function () {
        chai.assert.closeTo(
            DUSTER_RENT_COST * 2,
            autorent.costFor(
                DUSTER_LICENSE_PLATE,
                aMondayPickupDay, 2, aMondayPickupDay.add(2, 'days'),
                DUSTER_FULL_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning a ford ka for three days and three hours late with full tank", function () {
        chai.assert.closeTo(
            FORD_KA_RENT_COST * 3 + FORD_KA_RENT_COST,
            autorent.costFor(
                FORD_KA_LICENSE_PLATE,
                aMondayPickupDay, 3, aMondayPickupDay.add(3, 'days').add(3, 'hours'),
                KA_FULL_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning a ford ka for two days on time with tank level between 0 and 25 percent", function () {
        chai.assert.closeTo(
            FORD_KA_RENT_COST * 2 + 400,
            autorent.costFor(
                FORD_KA_LICENSE_PLATE,
                aMondayPickupDay, 2, aMondayPickupDay.add(2, 'days'),
                KA_20_PERCENT_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning a ford ka for two days on time with tank level between 25 and 50 percent", function () {
        chai.assert.closeTo(
            FORD_KA_RENT_COST * 2 + 300,
            autorent.costFor(
                FORD_KA_LICENSE_PLATE,
                aMondayPickupDay, 2, aMondayPickupDay.add(2, 'days'),
                KA_40_PERCENT_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning a ford ka for two days on time with tank level between 50 and 75 percent", function () {
        chai.assert.closeTo(
            FORD_KA_RENT_COST * 2 + 200,
            autorent.costFor(
                FORD_KA_LICENSE_PLATE,
                aMondayPickupDay, 2, aMondayPickupDay.add(2, 'days'),
                KA_60_PERCENT_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning a ford ka for two days on time with tank level between 75 and 100 percent", function () {
        chai.assert.closeTo(
            FORD_KA_RENT_COST * 2 + 100,
            autorent.costFor(
                FORD_KA_LICENSE_PLATE,
                aMondayPickupDay, 2, aMondayPickupDay.add(2, 'days'),
                KA_80_PERCENT_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning logan 1 day with more than allowed kilometers", function () {
        var exceededKms = 85;
        chai.assert.closeTo(
            LOGAN_RENT_COST + exceededKms * LOGAN_COST_PER_EXCEEDED_KM,
            autorent.costFor(
                LOGAN_LICENSE_PLATE,
                aMondayPickupDay, 1, aMondayPickupDay.add(1, 'days'),
                LOGAN_FULL_TANK, MAX_KM_ALLOWED_PER_DAY + exceededKms),
            DELTA);
    });

    it("cost of logan from saturday to sunday inclusive", function () {
        chai.assert.closeTo(
            LOGAN_RENT_COST * 1.15 * 2,
            autorent.costFor(
                LOGAN_LICENSE_PLATE,
                aSaturdayPickupDay, 2, aSaturdayPickupDay.add(2, 'days'),
                LOGAN_FULL_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of logan from saturday to monday", function () {
        chai.assert.closeTo(
            LOGAN_RENT_COST * 1.15 * 2 + LOGAN_RENT_COST,
            autorent.costFor(
                LOGAN_LICENSE_PLATE,
                aSaturdayPickupDay, 3, aSaturdayPickupDay.add(3, 'days'),
                LOGAN_FULL_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of logan from friday to monday", function () {
        chai.assert.closeTo(
            LOGAN_RENT_COST * 2 + LOGAN_RENT_COST * 1.15 * 2,
            autorent.costFor(
                LOGAN_LICENSE_PLATE,
                aFridayPickupDay, 4, aFridayPickupDay.add(4, 'days'),
                LOGAN_FULL_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of clio from monday to 30 days on", function () {
        chai.assert.closeTo(
            CLIO_RENT_COST * 22 + CLIO_RENT_COST * 1.15 * 8,
            autorent.costFor(
                CLIO_LICENSE_PLATE,
                aMondayPickupDay, 30, aMondayPickupDay.add(30, 'days'),
                CLIO_FULL_TANK, TRAVELLED_300_KMS),
            DELTA);
    });

    it("cost of returning clio for two days with all violations", function () {
        var exceededKms = 85;
        chai.assert.closeTo(
            CLIO_RENT_COST * 2 + CLIO_RENT_COST + exceededKms * CLIO_RENT_COST_PER_EXCEEDED_KM + 100,
            autorent.costFor(
                CLIO_LICENSE_PLATE,
                aMondayPickupDay, 2, aMondayPickupDay.add(2, 'days').add(4, 'hours'),
                CLIO_80_PERCENT_TANK, MAX_KM_ALLOWED_PER_DAY * 2 + exceededKms),
            DELTA);
    });
});
