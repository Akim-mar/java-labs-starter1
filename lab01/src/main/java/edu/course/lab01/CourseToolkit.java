package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    
    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    public static boolean isPrime(int number){
        if (number < 2) return false;
        for (int a = 2; a * a <= number; a++){
            if (number % a == 0) return false;
        }
        return true;
    }
    public static boolean isPalindrome(String text){
        if (text == null) throw new IllegalArgumentException("text must not be null");
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    public static double average(int[] values){
        if (values == null || values.length == 0)
        throw new IllegalArgumentException("values must not be null or empty");
        int sum = 0;
        for (int value : values) sum += value;
        return (double) sum / values.length;
    }


    public static int min(int[] values) {
        if (values == null || values.length == 0)
            throw new IllegalArgumentException("values must not be null or empty");
        int result = values[0];
        for (int value : values) {
            if (value < result) result = value;
        }
        return result;
    }

    public static int max(int[] values) {
        if (values == null || values.length == 0)
            throw new IllegalArgumentException("values must not be null or empty");
        int result = values[0];
        for (int value : values) {
            if (value > result) result = value;
        }
        return result;
    }
}
