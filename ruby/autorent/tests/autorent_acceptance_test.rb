require 'minitest/autorun'
require 'date'
require_relative 'test_utils'
require_relative '../lib/autorent_system'


class AutorentAcceptanceTest < Minitest::Unit::TestCase
  @@a_monday_pickup_day = DateTime.new(2015, 4, 6, 12, 0, 0)
  @@a_friday_pickup_day = @@a_monday_pickup_day + 4 #days
  @@a_saturday_pickup_day = @@a_friday_pickup_day + 1
  @@an_hour = 1 / 24.0
  @@two_hours = @@an_hour * 2
  @@three_hours = @@an_hour * 3
  @@four_hours = @@an_hour * 4

  def setup
    @autorent = create_system_using_data_from_the_exercise_assignment
  end

  def test_cost_of_returning_duster_for_two_days_on_time_with_full_tank
    assert_in_delta(
        DUSTER_RENT_COST * 2,
        @autorent.cost_for(
            DUSTER_LICENSE_PLATE,
            @@a_monday_pickup_day, 2, @@a_monday_pickup_day + 2,
            DUSTER_FULL_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_a_ford_ka_for_three_days_and_three_hours_late_with_full_tank
    assert_in_delta(
        FORD_KA_RENT_COST * 3 + FORD_KA_RENT_COST,
        @autorent.cost_for(
            FORD_KA_LICENSE_PLATE,
            @@a_monday_pickup_day, 3, @@a_monday_pickup_day +3 + @@three_hours,
            KA_FULL_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between0and25_percent
    assert_in_delta(
        FORD_KA_RENT_COST * 2 + 400,
        @autorent.cost_for(
            FORD_KA_LICENSE_PLATE,
            @@a_monday_pickup_day, 2, @@a_monday_pickup_day + 2,
            KA_20_PERCENT_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between25and50_percent
    assert_in_delta(
        FORD_KA_RENT_COST * 2 + 300,
        @autorent.cost_for(
            FORD_KA_LICENSE_PLATE,
            @@a_monday_pickup_day, 2, @@a_monday_pickup_day + 2,
            KA_40_PERCENT_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between50and75_percent
    assert_in_delta(
        FORD_KA_RENT_COST * 2 + 200,
        @autorent.cost_for(
            FORD_KA_LICENSE_PLATE,
            @@a_monday_pickup_day, 2, @@a_monday_pickup_day + 2,
            KA_60_PERCENT_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between75and100_percent
    assert_in_delta(
        FORD_KA_RENT_COST * 2 + 100,
        @autorent.cost_for(
            FORD_KA_LICENSE_PLATE,
            @@a_monday_pickup_day, 2, @@a_monday_pickup_day + 2,
            KA_80_PERCENT_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_logan1_day_with_more_than_allowed_kilometers
    exceeded_kms = 85
    assert_in_delta(
        LOGAN_RENT_COST + exceeded_kms * LOGAN_COST_PER_EXCEEDED_KM,
        @autorent.cost_for(
            LOGAN_LICENSE_PLATE,
            @@a_monday_pickup_day, 1, @@a_monday_pickup_day + 1,
            LOGAN_FULL_TANK, MAX_KM_ALLOWED_PER_DAY + exceeded_kms))
  end

  def test_cost_of_logan_from_saturday_to_sunday_inclusive
    assert_in_delta(
        LOGAN_RENT_COST * 1.15 * 2,
        @autorent.cost_for(
            LOGAN_LICENSE_PLATE,
            @@a_saturday_pickup_day, 2, @@a_saturday_pickup_day + 2,
            LOGAN_FULL_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_logan_from_saturday_to_monday
    assert_in_delta(
        LOGAN_RENT_COST * 1.15 * 2 + LOGAN_RENT_COST,
        @autorent.cost_for(
            LOGAN_LICENSE_PLATE,
            @@a_saturday_pickup_day, 3, @@a_saturday_pickup_day + 3,
            LOGAN_FULL_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_logan_from_friday_to_monday
    assert_in_delta(
        LOGAN_RENT_COST * 2 + LOGAN_RENT_COST * 1.15 * 2,
        @autorent.cost_for(
            LOGAN_LICENSE_PLATE,
            @@a_friday_pickup_day, 4, @@a_friday_pickup_day + 4,
            LOGAN_FULL_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_clio_from_monday_to30_days_on
    assert_in_delta(
        CLIO_RENT_COST * 22 + CLIO_RENT_COST * 1.15 * 8,
        @autorent.cost_for(
            CLIO_LICENSE_PLATE,
            @@a_monday_pickup_day, 30, @@a_monday_pickup_day + 30,
            CLIO_FULL_TANK, TRAVELLED_300_KMS))
  end

  def test_cost_of_returning_clio_for_two_days_with_all_violations
    exceeded_kms = 15
    assert_in_delta(
        CLIO_RENT_COST * 2 + CLIO_RENT_COST + exceeded_kms * CLIO_RENT_COST_PER_EXCEEDED_KM + 100,
        @autorent.cost_for(
            CLIO_LICENSE_PLATE,
            @@a_monday_pickup_day, 2, @@a_monday_pickup_day + 2 + @@four_hours,
            CLIO_80_PERCENT_TANK, MAX_KM_ALLOWED_PER_DAY * 2 + exceeded_kms))
  end
end
