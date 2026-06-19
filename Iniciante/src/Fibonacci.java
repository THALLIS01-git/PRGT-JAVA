import java.util.Scanner;

    public class Fibonacci {
        public static void main(String[] args) {
            Scanner scanner=new Scanner(System.in);
           int f1=0;
           int f2=1;
           int limite=scanner.nextInt();
            while(f2<limite){
                int fn=f1+f2;
                if(fn>limite)
                    break;
                f1=f2;
                f2=fn;
            System.out.println(fn);    
                
            }
        }
        
    }
