//实验2-1
public class Dp1 {
	 public static void main(String[] args) {
	        int []p = {30,35,15,5,10,20,25,};
	        int [][]m = new int [p.length+1][p.length+1];
	        int [][]s = new int [p.length+1][p.length+1];
	        MatrixChain(p,m,s);
	        System.out.println("乘法的最优次序：");
	        traceback(s,1,p.length-1);
		 System.out.println();
		 System.out.println("ZB2103022143-赵飞");
	    }
	    public static void MatrixChain(int []p,int [][]m,int [][]s){

	        for(int i=1;i<=p.length;i++){
	            m[i][i]=0;
	        }
	        for(int r = 2; r<= p.length;r++){
	            for(int i=1;i<=p.length-r;i++){
	                int j = i+r-1;
	                m[i][j] = m[i+1][j]+p[i-1]*p[i]*p[j];
	                s[i][j] = i;
	                for(int k =i+1;k<j;k++){
	                    int t = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
	                    if(t<m[i][j]){
	                        m[i][j] = t;
	                        s[i][j]=k;
	                    }
	                }
	            }
	        }
	    }
	    private static void traceback(int[][] s, int i, int j) {
	        if(i==j)
	            System.out.print("A"+i);
	        else if(i+1==j)
	            System.out.print(" (A"+i+" * "+" A"+j+") ");
	        else{
	            System.out.print(" (");
	            traceback(s,i,s[i][j]);
	            traceback(s,s[i][j]+1,j);
	            System.out.print(") ");
	        }
	    }

}
