// Radix Sort
// Purpose:
// Programmers:

import java.util.LinkedList;

public class radix_sort {

    // Method to get the maximum value of the array
    static int GetMax(double[] arr) {
        double max_value = arr[0];
        int max_index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max_value) {
                max_value = arr[i];
                max_index = i;
            }
        }
        return max_index;
    }

    // Get the number of digits of a number
    static int GetDigits(double number) {
        int i = 1;
        if (number < 10)
            i = 1;
        else {
            while (number > Math.pow(10, i))
                i++;
        }
        return i;
    }

    public static void RadixSort(double[] arr) {

        // Declaring 2 fixed-sized arrays (size 10) for 10 decimal places.
        double[] array1 = new double[10];
        double[] array2 = new double[10];

        int MaxIndex = GetMax(arr); // Get index value of maximum value in the array
        double MaxValue = arr[MaxIndex]; // Declare maximum value in the array
        int num = GetDigits(MaxValue); // Get the number of digits in the maximum value

    }

}