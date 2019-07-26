package com.pandatv.tools.sort;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/guoyaohua/p/8600214.html
 *
 * @author: likaiqing
 * @create: 2019-02-27 16:39
 **/
public class AllSorts {
    public static void main(String[] args) {
        int[] arr = {50, 19, 15, 30, 12, 70, 40, 80, 60, 16, 12, 10};
        System.out.println("排序之前：" + Arrays.toString(arr));
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertSort(arr);
//        insertSort2(arr);
//        ShellSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        heapSort(arr);
        System.out.println("排序之后：" + Arrays.toString(arr));
        int[] arrBi = {1, 3, 3, 3, 5, 6, 7, 8};
        int i = biSearch(arrBi, 3);
        System.out.println(i);
        i = biSearchFirst(arrBi, 3);
        System.out.println(i);
        int[] arrBiFir = {8, 7, 6, 5, 3, 3, 3, 1};
        i = biSearch2(arrBiFir, 3);
        System.out.println(i);
        i = biSearchFirst2(arrBiFir, 3);
        System.out.println(i);
    }


    private static int biSearchFirst2(int[] arrBiFir, int val) {
        int l = 0;
        int h = arrBiFir.length - 1;
        int mid = 0;
        while (l < h) {
            mid = l + (h - l) / 2;
            if (val == arrBiFir[mid]) {
                h = mid;
            } else if (val > arrBiFir[mid]) {
                h = mid - 1;
            } else if (val < arrBiFir[mid]) {
                l = mid + 1;
            }
        }
        if (arrBiFir[h]==val){
            return h;
        }
        return -1;
    }

    private static int biSearch2(int[] arrBi, int val) {
        int l = 0;
        int h = arrBi.length - 1;
        int mid = 0;
        while (l <= h) {
            mid = l + (h - l) / 2;
            if (val == arrBi[mid]) {
                return mid;
            } else if (val > arrBi[mid]) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    private static int biSearchFirst(int[] arr, int val) {
        int l = 0;
        int h = arr.length - 1;
        int mid = 0;
        while (l < h) {
            mid = l + (h - l) / 2;
            if (val == arr[mid]) {
                h = mid;
            } else if (val < arr[mid]) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (arr[h] == val) {
            return h;
        }
        return -1;
    }

    /**
     * 二分查找
     *
     * @param arr
     * @param val
     * @return
     */
    private static int biSearch(int[] arr, int val) {
        int l = 0;
        int h = arr.length - 1;
        int mid = 0;
        while (l <= h) {
//            mid = (l + h) / 2;
            mid = l + (h - l) / 2;
            if (arr[mid] > val) {
                h = mid - 1;
            } else if (arr[mid] < val) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     * 时间复杂度O(n^2) 最好情况O(n) 最坏O(n^2)
     * 空间复杂度O(1)
     * 稳定排序
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度
     * 时间复杂度最差最佳平均都是O(n^2)
     * 空间复杂度O(1)
     * 不稳定
     *
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }

    /**
     * 插入排序
     * 时间复杂度O(n^2) 最佳O(n) 最坏O(n^2)
     * 空间复杂度O(1)
     * 稳定
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            for (; j > 0; j--) {
                if (arr[j - 1] > tmp) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = tmp;
        }
    }

    private static void insertSort2(int[] arr) {
        int current;
        for (int i = 0; i < arr.length - 1; i++) {
            current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    /**
     * 希尔排序 是一种插入排序 选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列
     * 时间复杂度：O(nlog2N) //以2为底N的对数
     * 空间复杂度：O(1)
     * 不稳定
     *
     * @param arr
     */
    private static void ShellSort(int[] arr) {
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    /**
     * 归并排序
     * 时间复杂度固定O(nlogn)
     * 空间复杂度O(n)
     * 稳定
     *
     * @param arr
     * @return
     */
    private static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[index];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];

        }
        return result;
    }

    /**
     * 快速排序
     * 时间复杂度，平均O(nlogn) 最佳O(nlogn) 最坏O(n^2)
     * 空间复杂度 O(logn)
     * 不稳定
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
//        if (arr.length < 1 || left < 0 || right >= arr.length || left > right) return;
        if (left >= right)
            return;
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid);
        quickSort(arr, mid + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int tmp = arr[left];
        while (left < right) {
            while (left < right && tmp <= arr[right])
                right--;
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }
            while (left < right && tmp >= arr[left])
                left++;
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = tmp;
        return left;
    }


    private static void heapSort(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            heapAjust(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapAjust(arr, 0, i);
        }
    }

    private static void swap(int[] arr, int i, int i1) {
        int tmp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = tmp;
    }

    private static void heapAjust(int[] arr, int i, int length) {
        int father, child;
        for (father = arr[i]; leftChild(i) < length; i = child) {
            child = leftChild(i);
            if (child < length - 1 && arr[child] < arr[child + 1]) {
                child++;
            }
            if (father < arr[child]) {
                arr[i] = arr[child];
            }
        }
        arr[i] = father;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }
}
