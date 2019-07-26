package com.pandatv.tools.sort;

/**
 * @author: likaiqing
 * @create: 2019-02-14 16:20
 **/
public class MergeArray {
    public static void main(String[] args) {
//        merge2Arr();
//        sortMergeRecursion();
        int[] arr = new int[]{2, 23, 3, 54, 6, 22, 6, 34};
        sortMergeIteration(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }

    public static void sortMergeIteration(int[] arr) {
        int len = 1;
        while (len < arr.length) {
            for (int i = 0; i < arr.length; i += len * 2) {
                sortMergeIterationHelper(arr, i, len);
            }
            len *= 2;
        }
    }

    public static void sortMergeIterationHelper(int[] nums, int start, int len) {
        int[] tem = new int[len * 2];
        int i = start;
        int j = start + len;
        int k = 0;
        while (i < start + len && (j < start + len + len && j < nums.length)) {
            tem[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i < start + len && i < nums.length) {  // 注意：这里i也可能超出长度
            tem[k++] = nums[i++];
        }
        while (j < start + len + len && j < nums.length) {
            tem[k++] = nums[j++];
        }
        int right = start + len + len;
        int index = 0;
        while (start < nums.length && start < right) {
            nums[start++] = tem[index++];
        }
    }

    /**
     * 归并排序，递归实现
     */
    private static void sortMergeRecursion() {
        int[] arr = new int[]{2, 23, 3, 54, 6, 22, 6, 34};
        sortMergeRecursionHelper(arr, 0, arr.length - 1);
    }

    public static void sortMergeRecursionHelper(int[] arr, int left, int right) {
        if (left == right) return;
        int middle = left + (right - left) / 2;
        sortMergeRecursionHelper(arr, left, middle);
        sortMergeRecursionHelper(arr, middle + 1, right);
        mergeArr(arr, left, middle, right);
    }

    public static void mergeArr(int[] nums, int left, int middle, int right) {
        int[] tem = new int[right - left + 1];
        int i = left, j = middle + 1, k = 0;
        while (i <= middle && j <= right) {
            tem[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= middle) {
            tem[k++] = nums[i++];
        }
        while (j <= right) {
            tem[k++] = nums[j++];
        }
        // 将辅助数组数据写入原数组
        int index = 0;
        while (left <= right) {
            nums[left++] = tem[index++];
        }
    }

    public static void merge2Arr() {
        int[] arr1 = new int[]{2, 3, 6, 6, 34};
        int[] arr2 = new int[]{12, 32, 34, 62};
        int len1 = arr1.length;
        int len2 = arr2.length;
        int i = 0;
        int j = 0;
        int k = 0;
        int[] arr3 = new int[len1 + len2];
        while (i < len1 || j < len2) {
            if (i == len1) {
                arr3[k++] = arr2[j++];
                continue;
            }
            if (j == len2) {
                arr3[k++] = arr1[i++];
                continue;
            }
            if (arr1[i] < arr2[j]) {
                arr3[k++] = arr1[i++];
            } else if (arr1[i] == arr2[j]) {
                arr3[k++] = arr1[i++];
                arr3[k++] = arr2[j++];
            } else {
                arr3[k++] = arr2[j++];
            }
        }
        for (int m = 0; m < arr3.length; m++) {
            System.out.print(arr3[m] + ",");
        }
    }
}
