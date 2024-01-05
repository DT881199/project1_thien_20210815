package Algorithms;

import java.util.List;

public class MergeSort{

    // Merge sort function
    public static void mergeSort(int[] arr, int left, int right, List<int[]> actionArray, List<int[]> statusArray){
        if (left < right) {
            // Find the middle point
            int middle = (left + right) / 2;
            System.out.println("still merging");

            // Recursively sort the first and second halves
            mergeSort(arr, left, middle, actionArray, statusArray);
            mergeSort(arr, middle + 1, right, actionArray, statusArray);

            // Merge the sorted halves
            merge(arr, left, middle, right, actionArray, statusArray);
        }
    }

    // Merge two subarrays of arr[]
    public static void merge(int[] arr, int left, int middle, int right, List<int[]> actionArray, List<int[]> statusArrays){
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays and merging sequences
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        int[] status = new int[arr.length];

        int[] leftStatusArray = new int[n1];
        int[] righStatustArray = new int[n2];

        int m = 0;
        for(int k : statusArrays.get(statusArrays.size()-1)){
            status[m] = k;
            m++;
        }

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = arr[left + i];
            leftStatusArray[i] = status[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr[middle + 1 + j];
            righStatustArray[j] = status[middle + 1 + j];
        }

        System.out.println("entered 2");
        // Initial indexes of the merged subarrays
        int i = 0, j = 0;

        // Initial index of the merging array
        int k = left;

        System.out.println("entered ");
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                System.out.println("entered left");
                status[k] = leftStatusArray[i];
                i++;
            } else {
                System.out.println("entered right");
                arr[k] = rightArray[j];
                status[k] = righStatustArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if there are any
        while (i < n1) {
            arr[k] = leftArray[i];
            status[k] = leftStatusArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if there are any
        while (j < n2) {
            arr[k] = rightArray[j];
            status[k] = righStatustArray[j];
            j++;
            k++;
        } 

        statusArrays.add(status);

        int[] action = {left, right, middle};
        actionArray.add(action);
    }
}
