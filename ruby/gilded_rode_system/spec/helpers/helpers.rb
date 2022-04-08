module Helpers
    def expect_quality_above_or_equal_0_for_all(items)
        items.all.each do |item|
            expect(item.quality).to be >= 0
        end
    end

    def expect_quality_below_or_equal_50_for_all(items)
        items.all.each do |item|
            expect(item.quality).to be <= 50
        end
    end

    def run_end_day_update_many_times(gilded_rose_system)
        run_end_day_update_times gilded_rose_system, 40
    end

    def run_end_day_update_five_times(gilded_rose_system)
        run_end_day_update_times gilded_rose_system, 5
    end

    def run_end_day_update_times(gilded_rose_system, times)
        times.times do |n|
            gilded_rose_system.end_day_update
        end
    end

    def random_integer
        Random.new.rand(1...30)
    end

end