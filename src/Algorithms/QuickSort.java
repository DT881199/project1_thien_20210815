package Algorithms;

import java.util.List;

public class QuickSort{
    
    public static void quickSort(int[] array, int low, int high, List<int[]> actionArray, List<int[]> statusArrays){
        
        if (low < high) {
            int partitionIndex = partition(array, low, high, actionArray, statusArrays);

            quickSort(array, low, partitionIndex - 1, actionArray, statusArrays);
            quickSort(array, partitionIndex + 1, high, actionArray, statusArrays);
        }
    }
    private static int partition(int[] array, int low, int high, List<int[]> actionArray, List<int[]> statusArrays){
        int pivot = array[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                // Visualize Swap array[i] and array[j]
                //***********************//
                int temp1 = array[i];
                array[i] = array[j];
                array[j] = temp1;

                int[] status = new int[array.length];
                int m = 0;
                for(int k : statusArrays.get(statusArrays.size()-1)){
                    status[m] = k;
                    m++;
                }

                int temp2 = status[i];
                status[i] = status[j];
                status[j] = temp2;
                statusArrays.add(status);

                int[] action = {i, j, low, high};
                actionArray.add(action);
                //***********************//
            }
        }

        // Visualize Swap array[i+1] and array[high] (put the pivot in its correct position)
        //***********************//
        int temp1 = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp1;

        int[] status = new int[array.length];
        int m = 0;
        for(int k : statusArrays.get(statusArrays.size()-1)){
            status[m] = k;
            m++;
        }
        int temp2 = status[i+1];
        status[i+1] = status[high];
        status[high] = temp2;
        statusArrays.add(status);
        
        int[] action = {i+1, high, low, high};
        actionArray.add(action);
        //***********************//

        return i + 1;   
    }
}