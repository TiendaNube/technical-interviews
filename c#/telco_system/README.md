# Telco System

Design and implement a solution to the following problem

## Telco billing system

Model a system which prints the invoices for the customers of a Telco.

There are 3 different types of calls: Local, National, International

Each type has differente pricing:
 * The cost of an international call is $2 per minute.
 * The cost of a national call is $1.50 per minute.
 * The cost of a local call depends if it was done during peak hour (just one hour) or not. Peak hour cost is $1 per minute, not peak hour cost is $0.50 per minute.

## Scope

Complete `Telco.cs` to model the given problem. The objective is to create a simple, cohesive OOP model in that file that is able to pass all the tests defined in `TelcoSystemTests.cs`.

## Run tests

1. Go to the tests directory: `cd TelcoSystem/TelcoSystemTest/`
1. Run tests: `dotnet test`
