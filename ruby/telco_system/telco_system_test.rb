#http://www.ruby-doc.org/core-1.9.3/Time.html#method-i-min

require 'test/unit'
require_relative 'telco_system'

class TelcoSystemTest < Test::Unit::TestCase

    def test_the_historical_total_billed_should_be_zero_if_no_calls_were_made
        telco_system = TelcoSystem.new

        assert_equal(0, telco_system.historical_total_billed)
    end

    def test_an_international_call_of_two_minutes_should_cost_4_pesos
        telco_system = TelcoSystem.new

        some_date = Time.now
        plus_2_minutes = some_date + 2 * 60
        telco_system.register_international_call_between(some_date, plus_2_minutes, "Juan")

        assert_equal(4, telco_system.historical_total_billed)
        assert_equal(1, telco_system.historical_number_of_calls)
    end        

    def test_a_national_call_of_100_minutes_should_cost_150_pesos
        telco_system = TelcoSystem.new
        some_date = Time.now
        plus_100_minutes = some_date + 100 * 60

        telco_system.register_national_call_between(some_date, plus_100_minutes, "Juan")

        assert_equal(150, telco_system.historical_total_billed)
        assert_equal(1, telco_system.historical_number_of_calls)
    end

    def test_a_local_call_of_10_minutes_during_peak_hour_should_cost_10_pesos
        telco_system = TelcoSystem.with_peak_hour_starting_at(18)
        eighteen_o_clock = Time.local(2012, 1, 1, 18, 0) 
        plus_10_minutes = eighteen_o_clock + (10 * 60)

        telco_system.register_local_call_between(eighteen_o_clock, plus_10_minutes, "Juan")

        assert_equal(10, telco_system.historical_total_billed)
        assert_equal(1, telco_system.historical_number_of_calls)
    end

    def test_a_local_call_of_6_minutes_during_not_peak_hour_should_cost_3_pesos
        telco_system = TelcoSystem.with_peak_hour_starting_at(18)
        two_o_clock = Time.local(2012, 1, 1, 2, 0) 
        plus_6_minutes = two_o_clock + (6 * 60)

        telco_system.register_local_call_between(two_o_clock, plus_6_minutes, "Juan")

        assert_equal(3, telco_system.historical_total_billed)
        assert_equal(1, telco_system.historical_number_of_calls)
    end

    def test_a_local_call_started_in_peak_hour_and_finished_in_non_peak_hour_should_consider_each_cost
        telco_system = TelcoSystem.with_peak_hour_starting_at(18)
        two_minutes_before_peak_hour = Time.local(2012, 1, 1, 17, 58) 
        plus_6_minutes = two_minutes_before_peak_hour + (6 * 60)

        telco_system.register_local_call_between(two_minutes_before_peak_hour, plus_6_minutes, "Juan")

        assert_equal(5, telco_system.historical_total_billed)
        assert_equal(1, telco_system.historical_number_of_calls)
    end

    def test_two_international_calls_of_2_minutes_should_cost_8_pesos_for_juan
        telco_system = TelcoSystem.new

        a_date = Time.now
        another_date_time = Time.local(2012, 1, 1, 17, 58) 

        telco_system.register_international_call_between(a_date, a_date + (2 * 60), "Juan")
        telco_system.register_international_call_between(another_date_time, another_date_time + (2 * 60), "Juan")

        telco_system.register_international_call_between(a_date, a_date + (20 * 60), "Pedro")
        telco_system.register_international_call_between(another_date_time, another_date_time + (12 * 60), "Pedro")

        assert_equal(8, telco_system.total_billed_for("Juan"))

        assert_equal(2, telco_system.number_of_calls_for("Juan"))
        assert_equal(4, telco_system.historical_number_of_calls)
    end        
end
