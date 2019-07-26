package com.pandatv.tools.sort;

/**
 * @author: likaiqing
 * @create: 2019-02-27 13:04
 **/
public class MergeArr {
    public static void main(String[] args) {
        int[] num1 = {2, 3, 7, 9, 17, 18};
        int[] num2 = {3, 8, 9, 19, 20};
        int[] num = new int[11];
//        merge1(num1,num2,num);
        merge2(num1, num2, num);
        for (int i1 : num) {
            System.out.print(i1 + ",");
        }
    }

    private static void merge2(int[] num1, int[] num2, int[] num) {
        int len1 = num1.length;
        int len2 = num2.length;
        int i = 0, j = 0, k = 0;
        while (i < len1 || j < len2) {
            if (i != len1 && j != len2) {
                int tmp1 = num1[i];
                int tmp2 = num2[j];
                if (tmp1 <= tmp2) {
                    num[k++] = num1[i++];
                } else {
                    num[k++] = num2[j++];
                }
            }
            if (i == len1 && j != len2) {
                for (int l = j; l < num2.length; l++) {
                    num[k++] = num2[l];
                    j++;
                }
            }
            if (j == len2 && i != len1) {
                for (int l = i; l < num1.length; l++) {
                    num[k++] = num1[l];
                    i++;
                }
            }
        }
    }

    private static void merge1(int[] num1, int[] num2, int[] num) {
        int i = 0, j = 0, k = 0;
        for (; i < num1.length; i++) {
            int tmpi = num1[i];
            for (; j < num2.length; j++) {
                int tmpj = num2[j];
                if (tmpj <= tmpi) {
                    num[k++] = tmpj;
                } else {
                    break;
                }
            }
            num[k++] = tmpi;
        }
        while (j != num2.length) {
            num[k++] = num2[j++];
        }
    }
}
