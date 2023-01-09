package com.study.p6_6.algorithm;


/**
 * @program: P6
 * @author: Jean
 * @create: 2021-05-18 21:35
 */
public class Sort {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
        quickSort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    private static void quickSort(int[] array) {
        qsort(array, 0, array.length - 1);
    }

    private static void qsort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = array[start];
        int left = start;
        int right = end;
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        qsort(array, start, right);
        qsort(array, left, end);
    }
}
