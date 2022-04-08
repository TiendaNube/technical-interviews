require_relative './bad_compression.rb'
require "rspec"

RSpec.describe BadCompression do
    before do
        @bc = described_class.new
    end

    test_cases = [
        ["", ""],
        ["a", "a"],
        ["aba", "aba"],
        ["aa", ""],
        ["aab", "b"],
        ["aaaaa", "a"],
        ["aabb", ""],
        ["abba", ""],
        ["abaaba", ""],
        ["aaabccddd", "abd"],
    ]
    test_cases.each  do |original, spected|
        it { expect(@bc.bad_compression(original)).to eq(spected) }
    end
end