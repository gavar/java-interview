package com.ctci.chapter1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Question 1.2: Check Permutation.
 * Given two strings, write a method to decide if one is a permutation of the other.
 */
class Q$1$2 {
    /**
     * Assumptions:
     * - none
     * Logic:
     * - both strings are of equal length
     * - both strings should have same count of each character
     * Performance:
     * - runtime: O(N * logN)
     * - memory: O(N)
     */
    static boolean hasPermutationByHashMap(String a, String b) {
        if (a.length() != b.length())
            return false;

        HashMap<Character, Integer> characters = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            int count = 0;
            char c = a.charAt(i);
            if (characters.containsKey(c))
                count = characters.get(c);

            characters.put(c, count + 1);
        }

        for (int i = 0; i < b.length(); i++) {
            int count = 0;
            char c = b.charAt(i);
            if (characters.containsKey(c))
                count = characters.get(c);

            if (count <= 0) return false;
            if (count == 1) characters.remove(c);
            else characters.put(c, count - 1);
        }

        return characters.isEmpty();
    }

    /**
     * Assumptions:
     * - string may contain only K different characters
     * Logic:
     * - both strings are of equal length
     * - both strings should have same count of each character
     * Performance:
     * - runtime: O(N)
     * - memory: O(k)
     * where
     * k - size of the alphabet
     */
    static boolean hasPermutationByAlphabet(String a, String b, int k) {
        if (a.length() != b.length())
            return false;

        int[] checker = new int[k];
        for (int i = 0; i < a.length(); i++)
            checker[a.charAt(i)]++;

        for (int i = 0; i < b.length(); i++)
            if (--checker[b.charAt(i)] < 0)
                return false;

        return true;
    }


    @ParameterizedTest
    @CsvSource({
            "abc, abc",
            "abc, acb",
            "abc, bac",
            "abc, bca",
            "abc, cab",
            "abc, cba",
            "apple, papel",
            "carrot, tarroc",
    })
    void checkIsPermutation(String a, String b) {
        String msg = String.format("'%s' is permutation of '%s'", a, b);
        assertTrue(hasPermutationByHashMap(a, b), msg);
        assertTrue(hasPermutationByAlphabet(a, b, 128), msg);
    }

    @ParameterizedTest
    @CsvSource({
            "abc, ab",
            "abc, abcd",
            "abc, def",
            "abc, abe",
            "abc, ebc",
            "abcd, abbc",
            "abcd, efgh",
            "hello, llloh",
    })
    void checkNoPermutation(String a, String b) {
        String msg = String.format("'%s' is not permutation of '%s'", a, b);
        assertFalse(hasPermutationByHashMap(a, b), msg);
        assertFalse(hasPermutationByAlphabet(a, b, 128), msg);
    }
}
