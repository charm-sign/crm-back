import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MaxLoading {
    static final int NUM = 100;
    static int[] w=new int[NUM];
    static int[] bestX = new int[NUM];
    static int[] x= new int[NUM];
    static int bestW = 0;
    static int n;
    static int c1;
    static int c2;
    static int cw = 0;
    private static int bound(int t) {
        int rw = 0;
        for (int i = t+1;i <= n;++i) {
            rw += w[i];
        }
        return rw+cw;
    }

    /**
     * 回溯法
     * @param t :第几个集装箱
     */
    public static void loadingRec(int t) {
        if (t > n) { // 集装箱装箱完毕
            if (cw > bestW) {  // 如果当前重量大于最优重量
                // 更新最优重量
                bestW = cw;
                for (int i = 1; i <= n; i++) {
                    // 最优解更新为当前解
                    bestX[i] = x[i];
                }
            }
            return;
        }else { // 尚未结束装箱
            if (cw + w[t] <= c1) {
                // 加上此集装箱重量
                cw +=w[t];
                // 选择该集装箱
                x[t] = 1;
                // 下一个
                loadingRec(t+1);
                cw -= w[t];
                x[t] = 0;
            }
            if (bound(t) > bestW) {
                loadingRec(t+1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入集装箱的个数：");
        n = in.nextInt();
        System.out.println("请输入集装箱的重量(整数）：");
        for (int i = 1; i <= n;++i) {
            w[i] = in.nextInt();
        }
        in.nextLine();
        System.out.print("请输入第一船的载重量：");
        c1 = in.nextInt();
        in.nextLine();
        System.out.print("请输入第二船的载重量：");
        c2 = in.nextInt();
        loadingRec(1);
        System.out.println("第一船的最优重量为：" + bestW);
        System.out.println("第一船的最优解为：");
        for (int i = 1; i <= n;++i) {
            if (bestX[i] == 1) { // bestX[i] == 1，表示选中
                System.out.println("物品" + i + "装入第1个集装箱");
            }
        }
        int cw2 = 0;
        for (int i = 1;i <= n;++i) {
            // 计算剩余重量
            if (bestX[i] == 0) {
                cw2 += w[i];
            }
        }

        if (cw2 <= c2) {
            System.out.println("可以将剩余集装箱装入第二船，问题有解");
        }else { // 剩余重量大于第二个船的重量，不能装入
            System.out.println("不能将剩余集装箱装入第二船，问题无解");
        }
        in.close();
    }
}

