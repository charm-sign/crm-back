import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName: mergeSort
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/11/16 15:05
 */
public class mergeSort {

    /**
     * 合并排序
     * <p>
     * 分治策略
     * <p>
     * 将待排序的元素分成大小大致相同的两个子集合，先对两个子集合进行排序，将排序好的子集合合并成排好序的集合
     * <p>
     * 复杂度T(n)=O(nlogn)
     */

    public static void mergesort(int[] a, int left, int right) {
        int i = (left + right) / 2;
        if (left < right) {
            mergesort(a, left, i);
            mergesort(a, i + 1, right);
            merge(a, left, i, right);
        }

    }
    public static void merge(int a[], int left, int i, int right) {
        int k = 0;
        int m = i + 1;
        int n = left;
        int b[] = new int[right - left + 1];
        while (n <= i && right >= m) {
            if (a[n] < a[m]) {
                b[k++] = a[n++];
            } else {
                b[k++] = a[m++];
            }
        }

        while (n <= i) {

            b[k++] = a[n++];

        }
        while (m <= right) {

            b[k++] = a[m++];

        }
        for (int j = 0; j < b.length; j++) {

            a[j + left] = b[j];

        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("输入数组的长度：");
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("输入第" + (i + 1) + "个数");
            a[i] = in.nextInt();
        }
        mergesort(a, 0, n - 1);
        System.out.println("合并排序结果是：");
        System.out.println(Arrays.toString(a));
        System.out.println("ZB2103022143-赵飞");

    }

}
