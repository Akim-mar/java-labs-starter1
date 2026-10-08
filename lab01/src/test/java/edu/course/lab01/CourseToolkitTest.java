package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber(){
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test 
    void returnsFalseForLessThanTwo(){
        boolean result = CourseToolkit.isPrime(1);
        assertFalse(result);
    }

    @Test 
    void returnsTrueForLessThanTwo(){
        boolean result = CourseToolkit.isPrime(3);
        assertTrue(result);
    }

    @Test 
    void returnsTrueForLessThanTwoNoTwo(){
        boolean result = CourseToolkit.isPrime(2);
        assertTrue(result);
    }

    @Test
    void isPalindromeReturnsTrueForLevel() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void isPalindromeReturnsFalseForHello() {
        assertFalse(CourseToolkit.isPalindrome("hello"));
    }

    @Test
    void isPalindromeIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Aba"));
    }

    @Test
    void isPalindromeConsidersSpaces() {
        assertTrue(CourseToolkit.isPalindrome("a a"));
    }

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsCorrectResult() {
        assertEquals(2.0, CourseToolkit.average(new int[]{1, 2, 3}));
    }

    @Test
    void averageHandlesNegativeNumbers() {
        assertEquals(-3.0, CourseToolkit.average(new int[]{-2, -4}));
    }

    @Test
    void averageReturnsFractionalResult() {
        assertEquals(1.5, CourseToolkit.average(new int[]{1, 2}));
    }

    @Test
    void averageThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void averageThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }

    @Test
    void averageDoesNotModifyInputArray() {
        int[] input = {1, 2, 3};
        CourseToolkit.average(input);
        assertArrayEquals(new int[]{1, 2, 3}, input);
    }

    @Test
    void minReturnsSmallestElement() {
        assertEquals(1, CourseToolkit.min(new int[]{3, 1, 4, 1, 5}));
    }

    @Test
    void minThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(new int[]{}));
    }

    @Test
    void maxReturnsLargestElement() {
        assertEquals(5, CourseToolkit.max(new int[]{3, 1, 4, 1, 5}));
    }

    @Test
    void maxThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(new int[]{}));
    }
}
