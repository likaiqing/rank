package com.pandatv.tools.sort;

import java.util.Arrays;

/**https://blog.csdn.net/qq_37169817/article/details/79893200
 * @author: likaiqing
 * @create: 2019-02-27 16:12
 **/
public class HeapSortTest {
    public static void main(String[] args) {
        int[] arr = {50, 10, 90, 30, 70, 40, 80, 60, 20};
        System.out.println("排序前：" + Arrays.toString(arr));
        //构建大顶堆
        for (int i = arr.length / 2; i >= 0; i--) {
            heapAjust(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapAjust(arr, 0, i);
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void heapAjust(int[] arr, int i, int length) {
        int father;
        int child;
        for (father = arr[i]; leftChild(i) < length; i = child) {
            child = leftChild(i);
            if (child != length - 1 && arr[child] < arr[child + 1]) {
                child++;
            }
            if (father < arr[child]) {
                arr[i] = arr[child];
            } else {
                break;
            }
        }
        arr[i] = father;
    }

    private static void swap(int[] arr, int i, int i1) {
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }


    private static int leftChild(int i) {
        return 2 * i + 1;
    }

}
