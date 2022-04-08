import unittest
from datetime import datetime, timedelta

from lib.autorent_system import create_system_using_data_from_the_exercise_assignment
from lib.constants import *

a_monday_pickup_day = datetime(2015, 4, 6, 12, 0, 0, 0)
a_friday_pickup_day = a_monday_pickup_day + timedelta(days=4)
a_saturday_pickup_day = a_friday_pickup_day + timedelta(days=1)


class AutorentAcceptanceTest(unittest.TestCase):
    def setUp(self):
        self.autorent = create_system_using_data_from_the_exercise_assignment()

    def test_cost_of_returning_duster_for_two_days_on_time_with_full_tank(self):
        self.assertAlmostEqual(
            DUSTER_RENT_COST * 2,
            self.autorent.cost_for(
                DUSTER_LICENSE_PLATE,
                a_monday_pickup_day, 2, a_monday_pickup_day + timedelta(days=2),
                DUSTER_FULL_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_a_ford_ka_for_three_days_and_three_hours_late_with_full_tank(self):
        self.assertAlmostEqual(
            FORD_KA_RENT_COST * 3 + FORD_KA_RENT_COST,
            self.autorent.cost_for(
                FORD_KA_LICENSE_PLATE,
                a_monday_pickup_day, 3, a_monday_pickup_day + timedelta(days=3, hours=3),
                KA_FULL_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between0and25_percent(self):
        self.assertAlmostEqual(
            FORD_KA_RENT_COST * 2 + 400,
            self.autorent.cost_for(
                FORD_KA_LICENSE_PLATE,
                a_monday_pickup_day, 2, a_monday_pickup_day + timedelta(days=2),
                KA_20_PERCENT_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between25and50_percent(self):
        self.assertAlmostEqual(
            FORD_KA_RENT_COST * 2 + 300,
            self.autorent.cost_for(
                FORD_KA_LICENSE_PLATE,
                a_monday_pickup_day, 2, a_monday_pickup_day + timedelta(days=2),
                KA_40_PERCENT_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between50and75_percent(self):
        self.assertAlmostEqual(
            FORD_KA_RENT_COST * 2 + 200,
            self.autorent.cost_for(
                FORD_KA_LICENSE_PLATE,
                a_monday_pickup_day, 2, a_monday_pickup_day + timedelta(days=2),
                KA_60_PERCENT_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_ford_ka_for_two_days_on_time_wit_tank_level_between75and100_percent(self):
        self.assertAlmostEqual(
            FORD_KA_RENT_COST * 2 + 100,
            self.autorent.cost_for(
                FORD_KA_LICENSE_PLATE,
                a_monday_pickup_day, 2, a_monday_pickup_day + timedelta(days=2),
                KA_80_PERCENT_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_logan1_day_with_more_than_allowed_kilometers(self):
        exceeded_kms = 85
        self.assertAlmostEqual(
            LOGAN_RENT_COST + exceeded_kms * LOGAN_COST_PER_EXCEEDED_KM,
            self.autorent.cost_for(
                LOGAN_LICENSE_PLATE,
                a_monday_pickup_day, 1, a_monday_pickup_day + timedelta(days=1),
                LOGAN_FULL_TANK, MAX_KM_ALLOWED_PER_DAY + exceeded_kms))

    def test_cost_of_logan_from_saturday_to_sunday_inclusive(self):
        self.assertAlmostEqual(
            LOGAN_RENT_COST * 1.15 * 2,
            self.autorent.cost_for(
                LOGAN_LICENSE_PLATE,
                a_saturday_pickup_day, 2, a_saturday_pickup_day + timedelta(days=2),
                LOGAN_FULL_TANK, TRAVELLED_300_KMS))

    def test_cost_of_logan_from_saturday_to_monday(self):
        self.assertAlmostEqual(
            LOGAN_RENT_COST * 1.15 * 2 + LOGAN_RENT_COST,
            self.autorent.cost_for(
                LOGAN_LICENSE_PLATE,
                a_saturday_pickup_day, 3, a_saturday_pickup_day + timedelta(days=3),
                LOGAN_FULL_TANK, TRAVELLED_300_KMS))

    def test_cost_of_logan_from_friday_to_monday(self):
        self.assertAlmostEqual(
            LOGAN_RENT_COST * 2 + LOGAN_RENT_COST * 1.15 * 2,
            self.autorent.cost_for(
                LOGAN_LICENSE_PLATE,
                a_friday_pickup_day, 4, a_friday_pickup_day + timedelta(days=4),
                LOGAN_FULL_TANK, TRAVELLED_300_KMS))

    def test_cost_of_clio_from_monday_to30_days_on(self):
        self.assertAlmostEqual(
            CLIO_RENT_COST * 22 + CLIO_RENT_COST * 1.15 * 8,
            self.autorent.cost_for(
                CLIO_LICENSE_PLATE,
                a_monday_pickup_day, 30, a_monday_pickup_day + timedelta(days=30),
                CLIO_FULL_TANK, TRAVELLED_300_KMS))

    def test_cost_of_returning_clio_for_two_days_with_all_violations(self):
        exceeded_kms = 15
        self.assertAlmostEqual(
            CLIO_RENT_COST * 2 + CLIO_RENT_COST + exceeded_kms * CLIO_RENT_COST_PER_EXCEEDED_KM + 100,
            self.autorent.cost_for(
                CLIO_LICENSE_PLATE,
                a_monday_pickup_day, 2, a_monday_pickup_day + timedelta(days=2, hours=4),
                CLIO_80_PERCENT_TANK, MAX_KM_ALLOWED_PER_DAY * 2 + exceeded_kms))
