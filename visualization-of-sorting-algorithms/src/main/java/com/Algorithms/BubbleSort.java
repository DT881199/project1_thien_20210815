package com.Algorithms;

import java.util.List;

public class BubbleSort{

    public static void bubbleSort(int[] array, List<int[]> actionArray, List<int[]> statusArrays) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {

                // Visualize Swap array[i] and array[j]
                //***********************//
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    System.out.println("Swapped");

                    int[] status = new int[array.length];
                    int m = 0;
                    for(int k : statusArrays.get(statusArrays.size()-1)){
                        status[m] = k;
                        m++;
                    }

                    int temp2 = status[j];
                    status[j] = status[j+1];
                    status[j+1] = temp2;
                    statusArrays.add(status);

                    int[] action = {j, j+1};
                    actionArray.add(action);
                //***********************//    
                }
            }
        }
    }
}

