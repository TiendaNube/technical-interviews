#http://docs.python.org/library/datetime.html
from datetime import datetime

#http://labix.org/python-dateutil
from dateutil.relativedelta import relativedelta

from random import randint

# some utilities to make the code more readable
def increment(a_date_time, in_minutes):
    return a_date_time + relativedelta(minutes=+in_minutes)


def a_date_time_at(hour, minutes):
    return datetime(
        year=randint(2001, 2011),
        month=randint(1, 12),
        day=randint(1, 28),
        hour=hour,
        minute=minutes,
        second=0
    )

#fixtures
a_date_time = datetime.now()
another_date_time = increment(a_date_time, in_minutes=randint(1, 20))