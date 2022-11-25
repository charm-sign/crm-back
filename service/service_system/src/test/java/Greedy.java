public class Greedy {
 public static int greedySelector(int[] s,int[] f,boolean[] a) {
  int n = s.length-1;
  a[1] = true;
  int j = 1;
  int count = 1;
  for(int i = 2;i<=n;i++) {
   if(s[i]>=f[j]) {
    a[i] = true;
    j = i;
    count++;
   }else {
    a[i] = false;
   }
  }
  return count;
 }
 
 public static void main(String[] args) {
  int[] s = {1,3,0,5,3,5,6, 8, 8, 2, 12};
     int[] f = {4,5,6,7,8,9,10,11,12,13,14};
     boolean[] a = new boolean[12];
  System.out.println("相容活动共有: " + greedySelector(s, f, a) + "个: ");


  for(int i = 1;i < a.length;i++) {
   if(a[i]) {
    System.out.print(i + "   ");
   }
  }
  System.out.println();
  System.out.println("ZB2103022143-赵飞");
 }

}