import java.util.Scanner;

public class AlgoTowers {
    static int[][] towers;
    static int[][] memoization;
    static int task1Result=0;
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
                }else {
                    memoization[x][y] = 0;
                }
                task1Result=Math.max(task1Result,result);
                return result;
                //memoization[x][y]=0;
                //return Math.min(Math.min(ALG1TASK1(x - 1, y, h), ALG1TASK1(x, y - 1, h)), ALG1TASK1(x - 1, y - 1, h)) + 1;

            }
    }
    public static int ALG1TASK2(int[][] input,int h){

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
                    result=Math.max(result,dp[j]);
                }else {
                    dp[j]=0;
                }
                pre=temp;
            }
            for (int x:dp){
                System.out.print(x+" ");
            }
            System.out.println();

        }

        /*for (int j=0;j<height;j++){
            if (input[j][0]>=h){
                dp[j][0]=1;
                result=1;
            }else {
                dp[j][0]=0;
            }
        }*/
        //int result=0;
        /*for (int i=1;i<height;i++){
            for (int j=1;j<len;j++){
                if (input[i][j]>=h){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
                result=Math.max(result,dp[i][j]);
            }
        }*/

        System.out.println(result*result);
        return result*result;
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
    public static int ALG2TASK3(int h){
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
                                printX2=j;
                                printY1=m;
                                printY2=n;
                            }
                        }
                    }
                }

            }
        }
        System.out.println(result);
        return result;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] firstLine=sc.nextLine().split(" ");
        int m=Integer.parseInt(firstLine[0]);
        int n=Integer.parseInt(firstLine[1]);
        int p=Integer.parseInt(firstLine[2]);
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

        ALG1TASK2(towers,5);
        ALG1TASK1(m-1,n-1,5);
        ALG2TASK3(5);

        System.out.println(task1Result*task1Result);
        for (int i=0;i<memoization.length;i++){
            for (int j=0;j<memoization[0].length;j++){
                System.out.print(memoization[i][j]+" ");
            }
            System.out.println();
        }

    }
}
