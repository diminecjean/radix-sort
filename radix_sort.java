// Radix Sort
// Purpose:
// Programmers:

import java.util.LinkedList;

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

    // Get the number of digits of a number
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

        // Declaring 2 fixed-sized linked lists (size 10) for 10 decimal places.
        LinkedList<Integer>[] array1 = new LinkedList[10];
        LinkedList<Integer>[] array2 = new LinkedList[10];

        int size = arr.length;

        int MaxIndex = GetMax(arr); // Get index value of maximum value in the array
        int MaxValue = arr[MaxIndex]; // Declare maximum value in the array

        // Get the number of digits in the maximum value
        // This is to determine the number of loops needed for the sort
        int num_loop = GetDigits(MaxValue);

        // Creating buckets for each array index to store pointers
        for (int i = 0; i < 10; i++) {
            array1[i] = new LinkedList<Integer>();
            array2[i] = new LinkedList<Integer>();
        }

        // Number of iterations of the outer loop is based on the
        // number of digits of the maximum value in the input array
        for (int i = 0; i < num_loop; i++) {
            for (int j = 0; j < size; j++) {
                int digit = (int) (arr[j] / Math.pow(10, i)) % 10;
                if (j % 2 == 0)
                    array1[digit].add(arr[j]);

            }
            int x = 0, y = 0;
            // write back to the array after each pass
            while (x < 10) {
                while (!array1[x].isEmpty()) {
                    arr[y] = array1[x].remove();
                    y++;
                }
                x++;
            }
        }
    }

    // a utility function to print the sorted array
    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 573, 25, 415, 12, 161, 6 };

        // function call
        RadixSort(arr);
        printArr(arr);
    }
}