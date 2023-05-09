// Radix Sort - using 2 arrays, based on bucket sort
// Purpose: Sort a sequence of decimal numbers of integer datatype, in the form of an array
// Programmers: 

import java.util.LinkedList;
import java.util.*;

public class radix_sort {

    // Method to get the maximum value of the array
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

    public static void RadixSort(int[] arr) {

        // Declaring 2 fixed-sized linked lists (size 10) and a
        // placeholder linked list (temp) for 10 decimal places.
        LinkedList<Integer>[] source = new LinkedList[10]; // linked list 1
        LinkedList<Integer>[] destination = new LinkedList[10]; // linked list 2
        LinkedList<Integer>[] temp = new LinkedList[10]; // placeholder

        int size = arr.length;

        int MaxIndex = GetMax(arr); // Get index value of maximum value in the array
        int MaxValue = arr[MaxIndex]; // Declare maximum value in the array

        // Get the number of digits in the maximum value
        // This is to determine the number of loops needed for the sort
        int num_loop = GetDigits(MaxValue);

        // Creating buckets for each linked list to store pointers
        for (int i = 0; i < 10; i++) {
            source[i] = new LinkedList<Integer>();
            destination[i] = new LinkedList<Integer>();
            temp[i] = new LinkedList<Integer>();
        }

        // Append the array elements into their respective buckets in the source
        // linked list based on the digit value of ones place value
        for (int j = 0; j < size; j++) {
            int digit = (int) (arr[j] % 10);
            source[digit].add(arr[j]);
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
                arr[y] = source[x].remove();
                y++;
            }
            x++;
        }
    }

    // Method to display the sorted array
    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of integers to sort: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Integer " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Function call
        RadixSort(arr); // Sort the array

        System.out.print("Sorted floating point array: ");
        printArr(arr); // Print the array
    }
}