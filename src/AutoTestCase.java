import java.util.Random;

public class AutoTestCase {
    public static void main(String[] args){
        Random random=new Random();
        int m = random.nextInt(1)+1000;
        int n = random.nextInt(1)+1000;
        int h = random.nextInt(10);

        System.out.println(m+" "+n+" "+h);

        for (int i = 1;i <= m ; i++){
            for (int j = 1;j <= n ; j++){
                if (j!=n) {
                    System.out.print(random.nextInt(10)+" ");
                }else {
                    System.out.print(random.nextInt(10));
                }
            }
            System.out.println();
        }

    }
}
