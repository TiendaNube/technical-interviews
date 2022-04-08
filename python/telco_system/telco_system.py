class TelcoSystem:
    def __init__(self):
        raise NotImplementedError()

    @staticmethod
    def with_peak_hour_starting_at(an_hour):
        raise NotImplementedError()

    def register_international_call_between(self, start_date, end_date, client_name):
        raise NotImplementedError()

    def register_national_call_between(self, start_date, end_date, client_name):
        raise NotImplementedError

    def register_local_call_between(self, start_date, end_date, client_name):
        raise NotImplementedError()

    def historical_total_billed(self):
        raise NotImplementedError()

    def historical_number_of_calls(self):
        raise NotImplementedError()

    def total_billed_for(self, client_name):
        raise NotImplementedError()

    def number_of_calls_for(self, client_name):
        raise NotImplementedError()
