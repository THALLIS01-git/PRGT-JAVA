import java.util.Scanner;

public class calculadora {
    public static void main(String[] args) {
        System.out.println("Para usar nossa calculadora digite a opção que deseja utilizando numeros");
        System.out.println("Somar-1 multiplicar-2 menos-3 dividir-4");
        try(Scanner scanner = new Scanner(System.in)){
            int opção = Integer.parseInt(scanner.nextLine());
            switch (opção) {
                case 1:
                    System.out.println("Ok vamos somar");
                    System.out.println("Primeiro digite o primeiro numero inteiro");
                    int somaN1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Agora digite o segundo numero inteiro");
                    int somaN2 = Integer.parseInt(scanner.nextLine()); 
                    System.out.println("O resultado é =");
                    System.out.println(somaN1 + somaN2);
                    break;
                
                case 2:
                    System.out.println("Ok vamos multiplicar");
                    System.out.println("Primeiro digite o primeiro numero inteiro");
                    int multiN1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Agora digite o segundo numero inteiro");
                    int multiN2 = Integer.parseInt(scanner.nextLine()); 
                    System.out.println("O resultado é =");
                    System.out.println(multiN1 * multiN2);
                    break;

                case 3:
                    System.out.println("Ok vamos fazer menos");
                    System.out.println("Primeiro digite o primeiro numero inteiro");
                    int menosN1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Agora digite o segundo numero inteiro");
                    int menosN2 = Integer.parseInt(scanner.nextLine()); 
                    System.out.println("O resultado é =");
                    System.out.println(menosN1 - menosN2);
                    break;

                case 4:
                    System.out.println("Ok vamos dividir");
                     System.out.println("Ok vamos fazer menos");
                    System.out.println("Primeiro digite o primeiro numero inteiro");
                    int diviN1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Agora digite o segundo numero inteiro");
                    int diviN2 = Integer.parseInt(scanner.nextLine()); 
                    System.out.println("O resultado é =");
                    System.out.println(diviN1 / diviN2);
                    break;

                default:
                    System.out.println("Nemnhum resultado encontrado com isso!");
                    break;
            }
        }
    }
}
