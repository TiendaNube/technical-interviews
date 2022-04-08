class TelcoSystem
    def initialize(an_hour=nil)
        raise Exception.new("Not implemented yet.")
    end

    def self.with_peak_hour_starting_at(an_hour)
        raise Exception.new("Not implemented yet.")
    end
    
    def register_international_call_between(start_date, end_date, client)
        raise Exception.new("Not implemented yet.")
    end        
    
    def register_national_call_between(start_date, end_date, client)
        raise Exception.new("Not implemented yet.")
    end        
    
    def register_local_call_between(start_date, end_date, client)
        raise Exception.new("Not implemented yet.")
    end        
    
    def historical_total_billed
        raise Exception.new("Not implemented yet.")
    end        
    
    def historical_number_of_calls
        raise Exception.new("Not implemented yet.")
    end        
    
    def total_billed_for(client)
        raise Exception.new("Not implemented yet.")
    end        
    
    def number_of_calls_for(client)
        raise Exception.new("Not implemented yet.")
    end        

end