import java.util.Arrays;

public class ComparaçãoDeArrays {
    public static void main(String[] args) {
        int[]numeroA=new int[]{1,2,3};
        int[]numeroB=new int[]{1,2,3};

        System.out.println(numeroA==numeroB);
        System.out.println(numeroA.equals(numeroB));
        System.out.println(numeroA+" "+numeroB);
        System.out.println(Arrays.equals(numeroA, numeroB));
    }
}
