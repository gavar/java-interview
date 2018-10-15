package com.ctci.chapter5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Question 5.2: Binary to String.
 * Given a real number between O and 1 (e.g., 0.72) that is passed in as a double, print
 * the binary representation. If the number cannot be represented accurately in binary with at most 32
 * characters, print "ERROR:'
 */
public class Q$5$2 {

    /**
     * Hint #143:
     * To wrap your head around the problem, try thinking about how you'd do it for integers.
     * <p>
     * Hint #167:
     * In a number like .893 (in base 10), what does each digit signify?
     * What then does each digit in .10010 signify in base 2?
     */
    static String formatBinaryUsingDouble(double value) {
        StringBuilder binary = new StringBuilder();
        binary.append(".");

        double fraction = 0.5;
        while (value > 0) {
            if (binary.length() > 32)
                return "ERROR";

            if (value >= fraction) {
                binary.append("1");
                value -= fraction;
            } else {
                binary.append("0");
            }

            fraction *= 0.5;
        }

        if (binary.length() < 2)
            binary.append("0");

        return binary.toString();
    }

    @ParameterizedTest
    @MethodSource("data")
    void checkInsert(double value, String expect) {
        assertEquals(expect, formatBinaryUsingDouble(value));
    }

    static Object[][] data() {
        double f1 = 1.0 / Math.pow(2, 1);
        double f2 = 1.0 / Math.pow(2, 2);
        double f3 = 1.0 / Math.pow(2, 3);
        double f4 = 1.0 / Math.pow(2, 4);
        double f32 = 1.0 / Math.pow(2, 32);
        double f33 = 1.0 / Math.pow(2, 33);
        return new Object[][]{
                {0.72, "ERROR"},
                {f1 + f2, ".11"},
                {f1 + f3, ".101"},
                {f1 + f2 + f3 + f4, ".1111"},
                {f32, "." + repeat("0", 31) + "1"},
                {1 - f32, "." + repeat("1", 32)},
                {1 - f32 + f33, "ERROR"},
                {f33, "ERROR"},
        };
    }

    static String repeat(String value, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        while (times-- > 0) stringBuilder.append(value);
        return stringBuilder.toString();
    }
}
