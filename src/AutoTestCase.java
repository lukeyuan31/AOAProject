import java.util.Random;

public class AutoTestCase {
    public static void main(String[] args){
        Random random=new Random();
        int m = random.nextInt(5)+10;
        int n = random.nextInt(5)+10;
        int h = random.nextInt(1000);

        System.out.println(m+" "+n+" "+h);

        for (int i = 1;i <= m ; i++){
            for (int j = 1;j <= n ; j++){
                if (j!=n) {
                    System.out.print(random.nextInt(1000)+" ");
                }else {
                    System.out.print(random.nextInt(1000));
                }
            }
            System.out.println();
        }

    }
}
