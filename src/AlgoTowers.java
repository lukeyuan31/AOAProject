import java.util.Arrays;
import java.util.Scanner;

public class AlgoTowers {
    static int[][] towers;
    static int[][] memoization;
    static int task1Result=0;
    static int maxX;
    static int maxY;
    //static long period;
    public static int ALG1TASK1(int x,int y,int h){
        //The implementation of task1, a recursive implementation of ALG1 using memoization and O(mn) space.

        if (x==0 || y==0){
            if (towers[x][y]>=h) {
                memoization[x][y] = 1;
                return 1;
            }else {
                memoization[x][y] =0;
                return 0;
            }
            //return 0;
        }else if (memoization[x][y]!=Integer.MIN_VALUE) {
            return memoization[x][y];
        }else {
                int result = Math.min(Math.min(ALG1TASK1(x - 1, y, h), ALG1TASK1(x, y - 1, h)), ALG1TASK1(x - 1, y - 1, h)) + 1;
                if (towers[x][y]>=h) {
                    memoization[x][y] = result;
                    if (result>task1Result){
                        //System.out.println("Current result "+task1Result+"Current position is"+x+" "+y);
                        maxX=x;
                        maxY=y;
                        task1Result=result;
                    }
                    //task1Result=Math.max(task1Result,result);
                    return result;
                }else {
                    memoization[x][y] = 0;
                    return 0;
                }

                //memoization[x][y]=0;
                //return Math.min(Math.min(ALG1TASK1(x - 1, y, h), ALG1TASK1(x, y - 1, h)), ALG1TASK1(x - 1, y - 1, h)) + 1;

            }
    }
    public static void ALG1TASK2(int[][] input,int h){

        //The implementation of task2, a BottomUp implementation of ALG1 and O(n) space

        int len=input[0].length;
        int height=input.length;
        int result=0;
        int pre=0;
        int temp;

        int[] dp=new int[len+1];
        //dp[0]=0;
        for (int i=1;i<=height;i++){
            for (int j=1;j<=len;j++){
                temp=dp[j];
                if (input[i-1][j-1]>=h){
                    dp[j]=Math.min(Math.min(dp[j-1],pre),dp[j])+1;
                    //result=Math.max(result,dp[j]);
                    if (dp[j]>result){
                        maxX=i;
                        maxY=j;
                        result=dp[j];
                    }
                }else {
                    dp[j]=0;
                }
                pre=temp;
            }


        }
        System.out.println((maxX-result+1)+" "+(maxY-result+1)+" "+maxX+" "+(maxY));

    }
    public static boolean isRectangle(int x1,int y1,int x2,int y2,int h){
        for (int i=x1;i<=x2;i++){
            for (int j=y1;j<=y2;j++){
                if (towers[i][j]<h){
                    return false;
                }
            }
        }
        return true;
    }
    public static void ALG2TASK3(int h){
        int col=towers.length;
        int row=towers[0].length;
        int result=0;
        int printX1=0, printY1=0, printX2=0, printY2=0;

        for (int i=0;i<col;i++){
            for (int j=0;j<row;j++){
                for (int m=i;m<col;m++){
                    for (int n=j;n<row;n++){
                        if (isRectangle(i,j,m,n,h)){
                            int tempArea=(m-i+1)*(n-j+1);
                            if (tempArea>result){
                                result=tempArea;
                                printX1=i;
                                printY1=j;
                                printX2=m;
                                printY2=n;
                            }
                        }
                    }
                }

            }
        }
        //System.out.println("Task3 "+result);
        System.out.println((printX1+1)+" "+(printY1+1)+" "+(printX2+1)+" "+(printY2+1));
        //return result;
    }

    public static void ALG3TASK4(int h){
        int row=towers.length;
        int col=towers[0].length;
        int[][] dp=new int[row][col];
        int result=0;
        int x1 = 0,y1=0,x2=0,y2=0;

        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (towers[i][j]>=h){
                    if (j==0){
                        dp[i][j]=1;
                    }else {
                        dp[i][j]=dp[i][j-1]+1;
                    }
                    //dp[i][j]=j=0? 1 : dp[i][j-1]+1;
                    int width=dp[i][j];
                    for (int k=i;k>=0;k--){
                        width=Math.min(width,dp[k][j]);
                        if (width*(i-k+1)>result){
                            //System.out.println("Task4 "+i+" "+j+" k: "+(i-k+1)+"width: "+width);
                            x1=i+1;
                            y1=j+1-width+1;
                            x2=k+1;
                            y2=j+1;
                            result=width*(i-k+1);
                        }
                        //result=Math.max(result,width*(i-k+1));
                    }
                }
            }
        }
        System.out.println(x2+" "+y1+" "+x1+" "+y2);
        //return result;
    }
    public static void ALG3TASK5(int h){
        int row=towers.length;
        int col=towers[0].length;
        int[] left=new int[col];
        int[] right=new int[col];
        int[] height=new int[col];
        int x1=0,y1=0,x2=0,y2=0;

        Arrays.fill(right,col);

        int result=0;
        for (int i=0;i<row;i++){
            int cur_left=0,cur_right=col;
            for (int j=0;j<col;j++){
                if (towers[i][j]>=h){
                    height[j]=height[j]+1;
                }else {
                    height[j]=0;
                }
            }
            for (int j=0;j<col;j++){
                if (towers[i][j]>=h){
                    left[j]=Math.max(left[j],cur_left);
                }else {
                    left[j]=0;
                    cur_left=j+1;
                }
            }

            for (int j=col-1;j>=0;j--){
                if (towers[i][j]>=h){
                    right[j]=Math.min(right[j],cur_right);
                }else {
                    right[j]=col;
                    cur_right=j;
                }
            }
            for (int j=0;j<col;j++){
                if (((right[j]-left[j])*height[j])>result){
                    x1=i+1;
                    y1=left[j]+1;
                    x2=i+1-height[j]+1;
                    y2=right[j];
                    //System.out.println("Task5: "+(i+1)+" "+(left[j]+1)+" "+(i+1-height[j]+1)+" "+right[j]);
                    result=(right[j]-left[j])*height[j];
                }

            }

        }
        System.out.println(x2+" "+y1+" "+x1+" "+y2);
        //return result;

    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] firstLine=sc.nextLine().split(" ");
        int m=Integer.parseInt(firstLine[0]);
        int n=Integer.parseInt(firstLine[1]);
        int h=Integer.parseInt(firstLine[2]);
        towers=new int[m][n];
        memoization=new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                towers[i][j]=sc.nextInt();
                //System.out.print(towers[i][j]);
            }
            //System.out.println();
        }
        for (int i=0;i<memoization.length;i++){
            for (int j=0;j<memoization[0].length;j++){
                memoization[i][j]=Integer.MIN_VALUE;
            }
        }
        System.out.println();

        /*switch (args[0]){
            case "1":{
                ALG1TASK1(m-1,n-1,h);
                System.out.println((maxX-task1Result+2)+" "+(maxY-task1Result+2)+" "+(maxX+1)+" "+(maxY+1));
                break;
            }
            case "2":{
                ALG1TASK2(towers,h);
                break;
            }
            case "3":{
                ALG2TASK3(h);
                break;
            }
            case "4":{
                ALG3TASK4(h);
                break;
            }
            case "5":{
                ALG3TASK5(h);
                break;
            }
        }*/
        long start=System.nanoTime();
        ALG1TASK1(m-1,n-1,h);
        long end=System.nanoTime();
        System.out.println((maxX-task1Result+2)+" "+(maxY-task1Result+2)+" "+(maxX+1)+" "+(maxY+1));
        System.out.println("Time is "+ (end-start) +" nano seconds");
        //System.out.println("task1 result is "+ task1Result);

        start=System.nanoTime();
        ALG1TASK2(towers,h);
        end=System.nanoTime();
        System.out.println("Time is "+ (end-start)+" nano seconds");

        start=System.nanoTime();
        ALG2TASK3(h);
        end=System.nanoTime();
        System.out.println("Time is "+ (end-start)+ "nano seconds");

        start=System.nanoTime();
        ALG3TASK4(h);
        end=System.nanoTime();
        System.out.println("Time is "+ (end-start)+ "nano seconds");

        start=System.nanoTime();
        ALG3TASK5(h);
        end=System.nanoTime();
        System.out.println("Time is "+ (end-start)+ "nano seconds");


    }
}
