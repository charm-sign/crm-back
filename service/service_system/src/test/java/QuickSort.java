import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * @ClassName: QuickSort
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/11/16 15:18
 */
public class QuickSort {
    //快速排序
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("输入数组的长度：");
        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("输入第" + (i + 1) + "个数");
            a[i] = in.nextInt();
        }
        quickSort(a, 0, n - 1);
        System.out.println("快速排序结果是：");
        System.out.println(Arrays.toString(a));
        System.out.println("ZB2103022143-赵飞");
    }


    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp = 0;
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }

        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }}


