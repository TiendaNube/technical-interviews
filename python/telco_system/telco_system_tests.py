from nose.tools import istest, eq_
from telco_system import TelcoSystem
from testing_utils import *

@istest
def the_historical_total_billed_should_be_zero_if_no_calls_were_made():
    telco_system = TelcoSystem()

    eq_(0, telco_system.historical_total_billed())


@istest
def an_international_call_of_two_minutes_should_cost_4_pesos():
    telco_system = TelcoSystem()

    telco_system.register_international_call_between(a_date_time, increment(a_date_time, in_minutes=2), "Juan")

    eq_(4, telco_system.historical_total_billed())
    eq_(1, telco_system.historical_number_of_calls())


@istest
def a_national_call_of_100_minutes_should_cost_150_pesos():
    telco_system = TelcoSystem()
    telco_system.register_national_call_between(a_date_time, increment(a_date_time, in_minutes=100), "Juan")

    eq_(150, telco_system.historical_total_billed())
    eq_(1, telco_system.historical_number_of_calls())


@istest
def a_local_call_of_10_minutes_during_peak_hour_should_cost_10_pesos():
    telco_system = TelcoSystem.with_peak_hour_starting_at(18)
    eighteen_o_clock = a_date_time_at(hour=18, minutes=0)

    telco_system.register_local_call_between(eighteen_o_clock, increment(eighteen_o_clock, in_minutes=10), "Juan")

    eq_(10, telco_system.historical_total_billed())
    eq_(1, telco_system.historical_number_of_calls())


@istest
def a_local_call_of_6_minutes_during_not_peak_hour_should_cost_3_pesos():
    telco_system = TelcoSystem.with_peak_hour_starting_at(18)
    two_o_clock = a_date_time_at(hour=14, minutes=0)

    telco_system.register_local_call_between(two_o_clock, increment(two_o_clock, in_minutes=6), "Juan")

    eq_(3, telco_system.historical_total_billed())
    eq_(1, telco_system.historical_number_of_calls())


@istest
def a_local_call_started_in_peak_hour_and_finished_in_non_peak_hour_should_consider_each_cost():
    telco_system = TelcoSystem.with_peak_hour_starting_at(18)
    two_minutes_before_peak_hour = a_date_time_at(hour=17, minutes=58)

    telco_system.register_local_call_between(two_minutes_before_peak_hour, increment(two_minutes_before_peak_hour, in_minutes=6), "Juan")

    eq_(5, telco_system.historical_total_billed())
    eq_(1, telco_system.historical_number_of_calls())


@istest
def two_international_calls_of_2_minutes_should_cost_8_pesos_for_juan():
    telco_system = TelcoSystem()

    telco_system.register_international_call_between(a_date_time, increment(a_date_time, in_minutes=2), "Juan")
    telco_system.register_international_call_between(another_date_time, increment(another_date_time, in_minutes=2), "Juan")

    telco_system.register_international_call_between(a_date_time, increment(a_date_time, in_minutes=20), "Pedro")
    telco_system.register_international_call_between(another_date_time, increment(another_date_time, in_minutes=12), "Pedro")

    eq_(8, telco_system.total_billed_for("Juan"))

    eq_(2, telco_system.number_of_calls_for("Juan"))
    eq_(4, telco_system.historical_number_of_calls())


