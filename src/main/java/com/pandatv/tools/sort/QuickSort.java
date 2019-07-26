package com.pandatv.tools.sort;

/**
 * @author: likaiqing
 * @create: 2019-02-27 11:32
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 2, 7, 9, 1, 8, 5};
        quickSort(arr, 0, arr.length - 1);
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left >= right || arr.length <= 1)
            return;
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid);
        quickSort(arr, mid + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int temp = arr[left];
        while (right > left) {
            while (temp <= arr[right] && left < right) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }
            while (temp >= arr[left] && left < right) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = temp;
        return left;
    }

    private static int partition1(int[] arr, int left, int right) {
        //采用三数中值分割法
        int mid = left + (right - left) / 2;
        //保证左端较小
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        //保证中间较小
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        //保证中间最小，左右最大
        if (arr[mid] > arr[left]) {
            swap(arr, left, mid);
        }
        int temp = arr[left];
        while (right > left) {
            while (temp <= arr[right] && left < right) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            while (temp >= arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = temp;
        return left;

    }

    private static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
