package Algorithms;

import java.util.List;

public class SelectionSort{

    public static void selectionSort(int[] array, List<int[]> actionArray, List<int[]> statusArrays) {
        int n = array.length;

        // Traverse the array
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;

            int[] status = new int[array.length];
            int m = 0;
            for(int k : statusArrays.get(statusArrays.size()-1)){
                status[m] = k;
                m++;
            }
    
            int temp2 = status[minIndex];
            status[minIndex] = status[i];
            status[i] = temp2;
    
            statusArrays.add(status);
            
            int[] action = {i, minIndex};
            actionArray.add(action);           
        }
    }
}

