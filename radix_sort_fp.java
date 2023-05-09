// Radix Sort for Floating Points - code based on the previous Radix Sort code for Integers
// Purpose: Sort a sequence of positive decimal numbers of floating point datatype, in the form of an array
// Programmers: 

import java.util.LinkedList;
import java.util.*;

public class radix_sort_fp {

    // Get the number of decimal places of the float with
    // most number of decimal places in the array
    static int NumberOfDP(float[] arr) {
        int max_dp = 0;
        for (int i = 0; i < arr.length; i++) {
            String text = Float.toString(Math.abs(arr[i]));
            int integer_places = text.indexOf('.');
            int decimal_places = text.length() - integer_places - 1;
            if (decimal_places > max_dp)
                max_dp = decimal_places;
        }
        return max_dp;
    }

    // Method to convert the floating point numbers into integers
    // Radix = base of hte number = 10
    // To convert, the integer is multiplied by the radix to the
    // power of max_dp
    static int[] FloatToInt(float[] arr, int max_dp) {
        int[] int_arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int_arr[i] = (int) (arr[i] * (float) (Math.pow(10, max_dp)));
        }
        return int_arr;
    }

    // Method to convert the integers into floating point numbers
    // To convert, the integer is divided by the radix to the
    // power of max_dp
    static float[] IntToFloat(int[] int_arr, int max_dp) {
        float[] arr = new float[int_arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (int_arr[i] / (Math.pow(10, max_dp)));
        }
        return arr;
    }

    // Method to get the number of digits of a number
    static int GetDigits(int number) {
        int i = 1;
        if (number < 10)
            i = 1;
        else {
            while (number > Math.pow(10, i))
                i++;
        }
        return i;
    }

    // Method to get the maximum value of an integer array
    static int GetMax(int[] arr) {
        int max_value = arr[0];
        int max_index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max_value) {
                max_value = arr[i];
                max_index = i;
            }
        }
        return max_index;
    }

    public static float[] RadixSort(float[] arr) {
        // Get number of decimal places of the longest
        // floating point value in the array
        int DPNum = NumberOfDP(arr);

        // Convert the floating point array into integer array
        // using the defined FloatToInt method
        int[] int_arr = new int[arr.length];
        int_arr = FloatToInt(arr, DPNum);

        // -------------Operations using converted integer array-------------

        // Declaring 2 fixed-sized linked lists (size 10) and a
        // placeholder linked list (temp) for 10 decimal places.
        LinkedList<Integer>[] source = new LinkedList[10]; // linked list 1
        LinkedList<Integer>[] destination = new LinkedList[10]; // linked list 2
        LinkedList<Integer>[] temp = new LinkedList[10]; // placeholder

        int size = arr.length; // Get the array length
        int MaxIndex = GetMax(int_arr); // Get index value of maximum value in the array
        int MaxValue = int_arr[MaxIndex]; // Get the maximum value
        int num_loop = GetDigits(MaxValue); // Get the number of digits in the maximum value to determine the number of
                                            // loops needed

        // Creating buckets for each linked list to store pointers
        for (int i = 0; i < 10; i++) {
            source[i] = new LinkedList<Integer>();
            destination[i] = new LinkedList<Integer>();
            temp[i] = new LinkedList<Integer>();
        }

        // Append the array elements into their respective buckets in the source
        // linked list based on the digit value of ones place value
        for (int j = 0; j < size; j++) {
            int digit = (int) (int_arr[j] % 10);
            source[digit].add(int_arr[j]);
        }

        // Number of iterations of the outer loop is based on the
        // number of digits of the maximum value in the input array
        // i starts from 1 as the sequence has already been sorted once
        // for the ones place value
        for (int i = 1; i < num_loop; i++) {
            for (int j = 0; j < size; j++) {
                int x = 0; // x represents the number of buckets
                while (x < 10) {
                    // Each bucket may have 0 to n number of linked list
                    while (!source[x].isEmpty()) {
                        int num = source[x].remove();
                        int digit = (int) (num / Math.pow(10, i)) % 10;
                        destination[digit].add(num);
                    }
                    x++;
                }
            }
            // Switch the arrays after each round of sorting
            temp = source;
            source = destination;
            destination = temp;
        }

        int x = 0, y = 0;
        // Write back the sorted elements into the array
        while (x < 10) {
            while (!source[x].isEmpty()) {
                int_arr[y] = source[x].remove();
                y++;
            }
            x++;
        }

        // -------------End of operations using converted integer array-------------

        // Convert the floating point array into integer array
        // using the defined IntToFloat method
        return IntToFloat(int_arr, DPNum);
    }

    // Method to display the sorted array
    static void printArr(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of floating point numbers to sort: ");
        int size = sc.nextInt();
        float[] arr = new float[size];

        // Get array input
        for (int i = 0; i < size; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            arr[i] = sc.nextFloat();
        }

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Function call
        arr = RadixSort(arr); // Sort the array

        System.out.print("Sorted floating point array: ");
        printArr(arr); // Print the array
    }
}