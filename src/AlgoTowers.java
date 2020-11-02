import java.util.Scanner;

public class AlgoTowers {
    public static int ALG1(int[][] input,int h){
        int len=input[0].length;
        int height=input.length;
        int result=0;

        int[][] dp=new int[height][len];
        for (int i=0;i<len;i++){
            if (input[0][i]>=h){
                dp[0][i]=1;
                result=1;
            }else {
                dp[0][i]=0;
            }
        }
        for (int j=0;j<height;j++){
            if (input[j][0]>=h){
                dp[j][0]=1;
                result=1;
            }else {
                dp[j][0]=0;
            }
        }
        //int result=0;
        for (int i=1;i<height;i++){
            for (int j=1;j<len;j++){
                if (input[i][j]>=h){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
                result=Math.max(result,dp[i][j]);
            }
        }
        System.out.println(result*result);
        return result*result;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] firstLine=sc.nextLine().split(" ");
        int m=Integer.parseInt(firstLine[0]);
        int n=Integer.parseInt(firstLine[1]);
        int p=Integer.parseInt(firstLine[2]);
        int[][] towers=new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                towers[i][j]=sc.nextInt();
                //System.out.print(towers[i][j]);
            }
            //System.out.println();
        }

        ALG1(towers,6);


    }
}
