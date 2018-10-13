package com.ctci.chapter1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Question 1.1: Is Unique.
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */
class Q$1$1 {

    /**
     * Runtime: O(N * logN)
     * Space: O(N)
     */
    static boolean isUniqueByHashMap(String value) {
        HashSet<Character> chars = new HashSet<>();
        for (int i = 0; i < value.length(); i++) // N
            if (!chars.add(value.charAt(i))) // logN
                return false;

        return true;
    }

    /**
     * Runtime: O(N)
     * Space: O(k)
     * k - size of alphabet
     */
    static boolean isUniqueByArray(String value) {
        boolean chars[] = new boolean[128];
        for (int i = 0; i < value.length(); i++) { // N
            char c = value.charAt(i);
            if (chars[c]) return false;
            else chars[c] = true;
        }
        return true;
    }

    /**
     * Runtime: O(N)
     * Space: O(1)
     */
    static boolean isUniqueByBitShift(String value) {
        int checker = 0;
        for (int i = 0; i < value.length(); i++) { // N
            int mask = 1 << (value.charAt(i) - 'a');
            if ((checker & mask) != 0) return false;
            checker |= mask;
        }
        return true;
    }

    /**
     * What if you cannot use additional data structures?
     * Runtime: O(N * logN)
     * Space: O(1)
     */
    static boolean isUniqueBySort(char[] value) {
        Arrays.sort(value); // N * log N
        for (int i = 1; i < value.length; i++) // N
            if (value[i] == value[i - 1])
                return false;

        return true;
    }

    /**
     * What if you cannot use additional data structures?
     * Runtime: O(N^2) | N * (N - 1) / 2
     * Space: O(1)
     */
    static boolean isUniqueByPairs(String value) {
        for (int i = 0; i < value.length(); i++) // N
            for (int j = i + 1; j < value.length(); j++) // N - 1
                if (value.charAt(i) == value.charAt(j))
                    return false;
        return true;
    }


    @ParameterizedTest
    @ValueSource(strings = {"abcde", "kite", "padle"})
    void checkUnique(String value) {
        String msg = String.format("'%s' have all unique characters", value);
        assertTrue(isUniqueByHashMap(value), msg);
        assertTrue(isUniqueByArray(value), msg);
        assertTrue(isUniqueBySort(value.toCharArray()), msg);
        assertTrue(isUniqueByPairs(value), msg);
        assertTrue(isUniqueByBitShift(value), msg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello", "apple", "abcddd", "aaabcd"})
    void checkNonUnique(String value) {
        String msg = String.format("'%s' doesn't have all unique characters", value);
        assertFalse(isUniqueByHashMap(value), msg);
        assertFalse(isUniqueByArray(value), msg);
        assertFalse(isUniqueBySort(value.toCharArray()), msg);
        assertFalse(isUniqueByPairs(value), msg);
        assertFalse(isUniqueByBitShift(value), msg);
    }
}
