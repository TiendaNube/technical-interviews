<?php


class TelcoSystem
{
    public static function withRushHourStartingAt(int $int)
    {
        throw new Exception("Not implemented");
    }

    function historicalTotalBilled()
    {
        throw new Exception("Not implemented");
    }

    public function historicalNumberOfCalls()
    {
        throw new Exception("Not implemented");
    }

    public function registerInternationalCallBetween($startTime, $endTime, $clientName)
    {
        throw new Exception("Not implemented");
    }

    public function registerNationalCallBetween($startTime, $endTime, $clientName)
    {
        throw new Exception("Not implemented");
    }

    public function registerLocalCallBetween($startTime, $endTime, $clientName)
    {
        throw new Exception("Not implemented");
    }

    public function numberOfCallsFor(string $clientName)
    {
        throw new Exception("Not implemented");
    }

    public function totalBilledFor(string $clientName)
    {
        throw new Exception("Not implemented");
    }
}
