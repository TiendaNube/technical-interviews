<?php

use Carbon\Carbon;
use Carbon\CarbonImmutable;
use PHPUnit\Framework\TestCase;

class TelcoSystemTest extends TestCase
{
    private $aDateTime;
    private $anotherDateTime;

    protected function setUp(): void
    {
        parent::setUp();

        $this->aDateTime = CarbonImmutable::now();
        $this->anotherDateTime = CarbonImmutable::now();
    }

    /**
     * @test
     */
    public function initialHistoricalTotalBilledShouldBeZero()
    {
        $telcoSystem = new TelcoSystem();
        $this->assertEquals(0, $telcoSystem->historicalTotalBilled());
    }

    /**
     * @test
     */
    public function internationalCallOfTwoMinutesShouldCost4_pesos()
    {
        $telcoSystem = new TelcoSystem();
        $telcoSystem->registerInternationalCallBetween($this->aDateTime, $this->aDateTime->addMinutes(2), "Juan");

        $this->assertEquals(4, $telcoSystem->historicalTotalBilled());
        $this->assertEquals(1, $telcoSystem->historicalNumberOfCalls());
    }

    /**
     * @test
     */
    public function nationalCallOf100MinutesShouldCost150Pesos()
    {
        $telcoSystem = new TelcoSystem();
        $telcoSystem->registerNationalCallBetween($this->aDateTime, $this->aDateTime->addMinutes(100), "Juan");

        $this->assertEquals(150, $telcoSystem->historicalTotalBilled());
        $this->assertEquals(1, $telcoSystem->historicalNumberOfCalls());
    }

    /**
     * @test
     */
    public function localCallOf10MinutesDuringRushHourShouldCost10Pesos()
    {
        $telcoSystem = TelcoSystem::withRushHourStartingAt(18);
        $eighteenO_clock = CarbonImmutable::createFromTime(18, 0, 0, 'Europe/London');

        $telcoSystem->registerLocalCallBetween($eighteenO_clock, $eighteenO_clock->addMinutes(10), "Juan");

        $this->assertEquals(10, $telcoSystem->historicalTotalBilled());
        $this->assertEquals(1, $telcoSystem->historicalNumberOfCalls());
    }

    /**
     * @test
     */
    public function localCallOf6MinutesDuringNotRushHourShouldCost3_pesos()
    {
        $telcoSystem = TelcoSystem::withRushHourStartingAt(18);
        $twoO_clock = CarbonImmutable::createFromTime(14, 0, 0, 'Europe/London');

        $telcoSystem->registerLocalCallBetween($twoO_clock, $twoO_clock->addMinutes(6), "Juan");

        $this->assertEquals(3, $telcoSystem->historicalTotalBilled());
        $this->assertEquals(1, $telcoSystem->historicalNumberOfCalls());
    }

    /**
     * @test
     */
    public function localCallStartedInRushHourAndFinishedInNonRushHourShouldConsiderEachCost()
    {

        $telcoSystem = TelcoSystem::withRushHourStartingAt(18);
        $twoMinutesBeforeRushHour = CarbonImmutable::createFromTime(17, 58, 0, 'Europe/London');

        $telcoSystem->registerLocalCallBetween($twoMinutesBeforeRushHour, $twoMinutesBeforeRushHour->addMinutes(6), "Juan");

        $this->assertEquals(5, $telcoSystem->historicalTotalBilled());
        $this->assertEquals(1, $telcoSystem->historicalNumberOfCalls());
    }

    /**
     * @test
     */
    public function multipleClientsShouldBeSupported()
    {
        $telcoSystem = new TelcoSystem();

        $telcoSystem->registerInternationalCallBetween($this->aDateTime, $this->aDateTime->addMinutes(2), "Juan");
        $telcoSystem->registerInternationalCallBetween($this->anotherDateTime, $this->anotherDateTime->addMinutes(2), "Juan");

        $telcoSystem->registerInternationalCallBetween($this->aDateTime, $this->aDateTime->addMinutes(20), "Pedro");
        $telcoSystem->registerInternationalCallBetween($this->anotherDateTime, $this->anotherDateTime->addMinutes(12), "Pedro");

        $this->assertEquals(8, $telcoSystem->totalBilledFor("Juan"));

        $this->assertEquals(2, $telcoSystem->numberOfCallsFor("Juan"));
        $this->assertEquals(4, $telcoSystem->historicalNumberOfCalls());
    }
}