public class Array {
    public static void main(String[] args) {
        int[] array=new int[5];
        array[0]=10; array[1]=20; array[2]=30; array[3]=40; array[4]=50;
        for(int n:array){
          System.out.printf("%d%n",n);  // OBS : Usa "%d" em caso de numeros inteiros
        }
    }
    

    public static void empratica(String[] args) {
        String[] empraticaT=new String[5];
        empraticaT[0]="Thallis"; empraticaT[1]="Vitor"; empraticaT[2]="de"; empraticaT[3]="Sousa"; empraticaT[4]="Silva";
        for(String T:empraticaT){
            System.out.printf("%s",T); // OBS: Usa "%s" em caso de String
        }
    }
}
