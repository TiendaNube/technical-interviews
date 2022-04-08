package com.devartis.exercise.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FizzBuzzTest {

    @Test
    public void shouldReturnFirstThreeElements() {
        assertArrayEquals(
                new String[]{"1", "2", "Fizz"},
                FizzBuzzConverter.convert(3)
        );
    }

    @Test
    public void shouldReturnTheFirst6Elements() {
        assertArrayEquals(
                new String[]{"1", "2", "Fizz", "4", "Buzz", "Fizz"},
                FizzBuzzConverter.convert(6)
        );
    }

    @Test
    public void shouldReturnFizzBuzzFor15() {
        String[] convert = FizzBuzzConverter.convert(15);
        Assert.assertEquals("FizzBuzz", convert[convert.length - 1]);
    }

    @Test
    public void shouldReturn150000Elements() {
        String[] convert = FizzBuzzConverter.convert(150000);
        Assert.assertEquals("FizzBuzz", convert[convert.length - 1]);
    }
}