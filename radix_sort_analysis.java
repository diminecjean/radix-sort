// Radix Sort - using 2 arrays, based on bucket sort
// Purpose: Sort a sequence of decimal numbers of integer datatype, in the form of an array
// Programmers: Looi Wei En, Lee Ying Hooi, Wong Jia Yi

import java.util.LinkedList;
import java.util.*;

class analysis {
    public static int counter = 0;
}

public class radix_sort_analysis {

    // Method to get the maximum value of the array
    static int GetMax(int[] arr) {
        int max_value = arr[0];
        int max_index = 0;
        analysis.counter += 3; // 3 assignments
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max_value) {
                max_value = arr[i];
                max_index = i;
                analysis.counter += 2; // 2 assignments
            }
            analysis.counter += 5; // 2 comparisons, 1 addition, 1 assignment and 1 array access
        }
        analysis.counter++; // method return
        return max_index;
    }

    // Method to get the number of digits of a number
    static int GetDigits(int number) {
        int i = 1;
        analysis.counter += 2; // 1 assignment and 1 comparison
        if (number < 10) {
            i = 1;
            analysis.counter++; // 1 assignment
        } else {
            while (number > Math.pow(10, i)) {
                i++;
                analysis.counter += 4; // 1 comparison, 1 method call, 1 addition and 1 assignment
            }
        }
        analysis.counter++; // method return
        return i;
    }

    // Method Radix Sort
    public static void RadixSort(int[] arr) {

        LinkedList<Integer>[] source = new LinkedList[10];
        LinkedList<Integer>[] destination = new LinkedList[10];
        LinkedList<Integer>[] temp = new LinkedList[10];
        analysis.counter += 6; // 3 sets of initialization and instantiation of linked list

        int size = arr.length;
        analysis.counter += 2; // access to length property and 1 assignment

        int MaxIndex = GetMax(arr);
        int MaxValue = arr[MaxIndex];
        int num_loop = GetDigits(MaxValue);
        analysis.counter += 6; // 3 assignments, 2 method call and 1 array access

        analysis.counter++; // 1 assignment
        for (int i = 0; i < 10; i++) {
            source[i] = new LinkedList<Integer>();
            destination[i] = new LinkedList<Integer>();
            temp[i] = new LinkedList<Integer>();
            analysis.counter += 6; // 3 sets of assignments and instantiation
        }

        analysis.counter++; // 1 assignment
        for (int j = 0; j < size; j++) {
            int digit = (int) (arr[j] % 10);
            source[digit].add(arr[j]);
            analysis.counter += 7; // 1 assignment, 2 array access, 1 modulo operation, 1 integer casting, 1 linked
                                   // list access and 1 method call
        }

        analysis.counter++; // 1 assignment
        for (int i = 1; i < num_loop; i++) {
            analysis.counter++; // 1 assignment
            for (int j = 0; j < size; j++) {
                int x = 0;
                while (x < 10) {

                    while (!source[x].isEmpty()) {
                        int num = source[x].remove();
                        int digit = (int) (num / Math.pow(10, i)) % 10;
                        destination[digit].add(num);
                        analysis.counter += 13; // 1 negation, 1 integer casting, 3 linkedlist access, 4 method calls, 1
                                                // division, 1 modulo operation and 2 assignments
                    }
                    x++;
                    analysis.counter += 3; // 1 comparison, 1 addition and 1 assignment
                }
                analysis.counter++; // 2 assignment, 1 comparison, and 1 addition
            }
            temp = source;
            source = destination;
            destination = temp;
            analysis.counter += 3; // 3 assignments
        }

        int x = 0, y = 0;
        analysis.counter += 2; // 2 assignments
        while (x < 10) {
            while (!source[x].isEmpty()) {
                arr[y] = source[x].remove();
                y++;
                analysis.counter += 9; // 2 linkedlist access, 1 array access, 2 method calls, 1 negation, 1 addition
                                       // and 2 assignment
            }
            x++;
            analysis.counter += 3; // 1 comparison, 1 addition and 1 assignment
        }
    }

    // Method to display the sorted array
    static void PrintArr(int[] arr) {
        analysis.counter++; // 1 assignment
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            analysis.counter += 6; // 1 array access, 1 concatenation, 1 print, 1 comparrison, 1 addition and 1
                                   // assignment
        }
        System.out.println();
        analysis.counter++; // 1 print
    }

    // Main Method
    public static void main(String[] args) {
        int iteration = 5;

        for (int i = 3; i < iteration * 3; i += 3) {
            Random rand_num = new Random();
            int upperbound = 10000;
            int[] arr = new int[i];
            for (int j = 0; j < i; j++) {
                arr[i] = rand_num.nextInt(upperbound);
            }
            System.out.print("Unsorted: ");
            PrintArr(arr);
            RadixSort(arr);
            System.out.print("Sorted: ");
            PrintArr(arr);
            System.out.println("Number of inputs: " + i);
            System.out.println("Number of primitive operations: " + analysis.counter + "/n");
        }
    }
}