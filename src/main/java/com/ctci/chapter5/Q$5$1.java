package com.ctci.chapter5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Question 5.1: Insertion.
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i.
 * You can assume that the bits j through i have enough space to fi t all of M.
 * That is, if M = 10011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 */
public class Q$5$1 {

    /**
     * Hint #137:
     * Break this into parts. Focus first on clearing the appropriate bits.
     */
    static int insert(int N, int M, int i, int j) {
        // clear bits from j to 0
        int mask = N >> j + i << j + i;
        // restore N i to 0 bits
        mask |= N & ~(~0 << i);
        // apply number
        return mask | M << i;
    }

    @ParameterizedTest
    @MethodSource("data")
    void checkInsert(int N, int M, int i, int j, int expect) {
        int actual = insert(N, M, i, j);
        assertEquals(expect, actual);
    }

    static Integer[][] data() {
        return new Integer[][]{
                {0b10000000000, 0b10011, 2, 6, 0b10001001100},
                {0b10001111100, 0b10011, 2, 6, 0b10001001100},
                {0b10001111111, 0b10011, 2, 6, 0b10001001111},
        };
    }
}
