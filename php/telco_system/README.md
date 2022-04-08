# Telco System

Design and implement a solution to the following problem

## Telco billing system

Model a system which prints the invoices for the customers of a Telco.

There are 3 different types of calls: Local, National, International

Each type has different pricing:
 * The cost of an international call is $2 per minute.
 * The cost of a national call is $1.50 per minute.
 * The cost of a local call depends if it was done during rush hour (just one hour) or not. 
     * Rush hour cost is $1 per minute.
     * Not rush hour cost is $0.50 per minute.

## Scope

Complete src/TelcoSystem.php to model the given problem. The objective is to create a simple, cohesive OOP model in 
that file that is able to pass all the tests defined in tests/TelcoSystemTest.php.

## Setup env

In ubuntu 20.04, only these dependencies are required:

`sudo apt install php php-xml php-mbstring`

then you should install (https://getcomposer.org/) composer and execute:

`./composer install`

## Run tests

`./vendor/bin/phpunit tests`