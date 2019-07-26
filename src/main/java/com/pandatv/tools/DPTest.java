package com.pandatv.tools;

import java.util.*;

/**
 * @author: likaiqing
 * @create: 2019-02-12 10:23
 * 动态规划
 **/
public class DPTest {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 6, 9, 3, 4, 5};
        int[] ints = Arrays.stream(nums).sorted().toArray();
//        subSegmentSum();
//        numGrouping();
//        int i = coinChange(nums, 20);
//        System.out.println(i);

//        nums = new int[]{2};
//        int change = change(5, nums);
//        System.out.println(change);
//        boolean flag = checkPerfectNumber(1);
//        System.out.println(flag);
        int fib = fib(4);
        System.out.println(fib);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> largestValues(TreeNode root) {
        if (null == root) return null;
        List<Integer> res = new ArrayList();
        int val = root.val;
        res.add(val);
        TreeNode left = root.left;
        TreeNode right = root.right;
        Map<Integer, Integer> map = new HashMap<>();
        int i = 1;
        map.put(1, val);
        if (null != left) {
            i++;
        }
        if (null != right) {

        }
        return res;
    }


    /**
     * 斐波那契数
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        int a = 0;
        int b = 1;
        if (n == 0) return a;
        if (n == 1) return b;
        int tmp = 0;
        for (int i = 2; i <= n; i++) {
            tmp = a + b;
            a = b;
            b = tmp;
        }
        return tmp;
    }

    /**
     * 完美数
     *
     * @param num
     * @return
     */
    public static boolean checkPerfectNumber(int num) {
        List<Integer> list = new ArrayList<>();
        if (num == 1) return false;
        list.add(1);
        int i = 2;
        while (i < num) {
            if (num % i == 0) {
                list.add(i);
            }
            i++;
        }
        Integer integer = list.stream().reduce((a, b) -> a + b).get();
        return num == integer;
    }

    /**
     * 币种无限个数的硬币找零组合
     *
     * @param amount
     * @param coins
     * @return 给你不同面值的硬币数组coins和总金额amount。 编写一个函数来计算组成该amount的组合的数量。每种硬币的个数是无限的。
     * 例 1:
     * Input: amount = 5, coins = [1, 2, 5]
     * Output: 4
     * 有四种组合方式：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 单个币种无限个数的硬币找零
     * 给你不同面值的硬币数组coins和总金额amount。 编写一个函数来计算您需要对amount金额的进行找零的最少数量的硬币。 如果这些金额不能由硬币的任何组合来找零，则返回'-1'。
     * 例 1:
     * coins = [1, 2, 5], amount = 11
     * return 3 (11 = 5 + 5 + 1)
     * 例 2:
     * coins = [2], amount = 3
     * return -1.
     */
    public static int coinChange(int[] coins, int amount) {
        if (coins.length < 1 || amount < 1) return -1;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; ++i) {
            dp[i] = amount;
            for (int j = 0; j < coins.length; ++j) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 正整数分组
     * 将一堆正整数分为2组，要求2组的和相差最小。
     * 例如：1 2 3 4 5，将1 2 4分为1组，3 5分为1组，两组和相差1，是所有方案中相差最少的。
     */
    public static void numGrouping() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int n = nums.length;
        long sum = 0, max = 0;
        sum = Arrays.stream(nums).reduce((a, b) -> a + b).getAsInt();
        int[] dp = new int[(int) (sum / 2 + 1)];
        for (int i = 0; i < n; i++) {
            for (int j = (int) sum / 2; j > 0; j--) {
                if (j >= nums[i]) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }
            }
        }
        for (int i = 1; i < sum / 2 + 1; i++) {
            max = max > dp[i] ? max : dp[i];
        }
        System.out.println(Math.abs((sum - max) - max));
    }

    /**
     * 最长公共子序列Lcs
     * 给出两个字符串A B，求A与B的最长公共子序列（子序列不要求是连续的）
     * abcicba
     * abdkscab
     * ab是两个串的子序列，abc也是，abca也是，其中abca是这两个字符串最长的子序列。
     */
    public static void lcs() {
        String aStr = "abcicba";
        String bStr = "abdkscab";
        int aLen = aStr.length();
        int bLen = bStr.length();
        int[][] dp = new int[aLen + 1][bLen + 1];
        for (int i = 1; i < aLen + 1; i++) {
            for (int j = 1; j < bLen + 1; j++) {
                if (dp[i - 1][j] == dp[i][j - 1] && aStr.charAt(i - 1) == bStr.charAt(j - 1) && dp[i - 1][j] == dp[i - 1][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int max = dp[aLen][bLen];
        StringBuilder sb = new StringBuilder();
        while (max > 0) {
            if (dp[aLen - 1][bLen] == dp[aLen][bLen - 1] && dp[aLen - 1][bLen] + 1 == dp[aLen][bLen]) {
                sb.append(aStr.charAt(aLen - 1));
                max--;
                aLen--;
                bLen--;
            } else {
                if (dp[aLen][bLen - 1] == dp[aLen][bLen]) {
                    bLen--;
                } else {
                    aLen--;
                }
            }
        }
        System.out.println(sb.reverse().toString());
    }

    /**
     * 最大子段和
     */
    public static void subSegmentSum() {
        int[] arr = {-2, 11, -4, 13, -5, -2};
        long max = 0;
        long subMax = 0;
//        List<Integer> list = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            subMax += arr[i];
            if (max < subMax) {
                max = subMax;
            }
//            list.add(arr[i]);
            if (subMax < 0) {
                subMax = 0;
//                list.clear();
            }
        }
        System.out.println(max);
//        System.out.println(list);
    }
}
