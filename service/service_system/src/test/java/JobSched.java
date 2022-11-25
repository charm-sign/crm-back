public class JobSched {
	int n;
    int f1;
    int f;
    int bestf;
    int[][] m;
    int []x;
    int[] bestx;
    int[] f2;
    //构造函数
    public JobSched(int n, int[][] m){
        this.n=n;
        this.m=m;
        f1=0;
        f=0;
        bestf=9999;
        bestx=new int[n+1];
        x=new int[n+1];
        //初始化，x[i]为原始排序
        for(int i=1;i<=n;i++){
            x[i]=i;
        }
        f2=new int[n+1];
    }
    //交换作业顺序 完成全排列
    private  void swap(int[] x,int i,int j){
        int temp=x[i];
        x[i]=x[j];
        x[j]=temp;
    }
    //参数i表示树的第几层，从1开始
    private  void backtrack(int i){
        //到达叶子结点，则打印
        if(i>n){
            for(int j=1;j<=n;j++)
                //更新最优调度序列
                bestx[j]=x[j];
            //更新最优目标值
            bestf=f;
        }
        //非叶子结点
        else{
            for(int j=i;j<=n;j++){
                //作业x[j]在第一台机器的时间
                f1+=m[x[j]][1];
                //f2[i]等于max(f2[i-1],f1)+作业x[j]在第2台机器的时间
                f2[i]=((f2[i-1]>f1)?f2[i-1]:f1)+m[x[j]][2];
                //完成时间和
                f+=f2[i];
                //完成时间和小于最优值
                if(f<bestf){
                    //交换两个作业的位置，把选择出的原来在x[j]位置上的任务调到当前执行的位置x[i]
                    swap(x,i,j);
                    backtrack(i+1);//深度搜索解空间树，进入下一层
                    swap(x,i,j);//进行回溯，还原
                }
                //回溯需要还原各个值
                f1-=m[x[j]][1];
                f-=f2[i];
            }
        }
    }
    public static void main(String[] args) {
        int n=3;
        //m的下标从1开始，因此第一行的0和每一行第一列的0无用
        int[][] m={{0,0,0},{0,2,1},{0,3,1},{0,2,3}};
        JobSched jobSched=new JobSched(n,m);
        jobSched.backtrack(1);
        System.out.println("最优批处理作业调度顺序为：");
        for(int i=1;i<=n;i++)
            System.out.print(jobSched.bestx[i]+" ");
        System.out.println();
        System.out.println("最优调度所需的最短时间为："+jobSched.bestf);
    }
}
