import java.util.Scanner;

/**
 * @ClassName: MaxAndMin
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/11/16 14:45
 */
public class MaxAndMin{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] strNums = sc.nextLine().split(",");
        sc.close();
        int[] nums = new int[strNums.length];
        for (int i = 0; i < strNums.length; i++) {
            nums[i] = Integer.parseInt(strNums[i]);
        }
        int[] Max = new int[1];
        int[] Min = new int[1];

        maxAndmin(nums, 0, nums.length - 1, Max, Min);
        System.out.println("max:"+Max[0]);
        System.out.println("min:"+Min[0]);
        System.out.println("ZB2103022143-赵飞");
    }

    public static void maxAndmin(int[] a, int left, int right, int[] maxnum, int[] minnum) {

        if (left == right) {
            maxnum[0] = a[left];
            minnum[0] = a[right];
        } else if (left + 1 == right) {
            if (a[left] > a[right]) {
                maxnum[0] = a[left];
                minnum[0] = a[left];
            } else {
                maxnum[0] = a[right];
                minnum[0] = a[left];
            }
        } else {
            int m = (right + left) / 2;
            int lmax[] = { 0 };
            int lmin[] = { 0 };
            int rmax[] = { 0 };
            int rmin[] = { 0 };

            maxAndmin(a, left, m, lmax, lmin);
            maxAndmin(a, m + 1, right, rmax, rmin);

            if (lmax[0] > rmax[0]) {
                maxnum[0] = lmax[0];
            } else {
                maxnum[0] = rmax[0];
            }
            if (lmin[0] < rmin[0]) {
                minnum[0] = lmin[0];
            } else {
                minnum[0] = rmin[0];
            }
        }

    }

}