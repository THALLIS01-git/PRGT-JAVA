import java.util.Scanner;
import java.util.Random;

public class Adivinha {
    public static void main(String[] args) throws Exception{
        System.out.println("Seja bem vindo ao nosso jogo de adivinha!");
        Thread.sleep(2000);
        System.out.println("Voce tera que adivinha o numero secreto voce terá 5 tentativas");
        Thread.sleep(2000);
        Random gerador = new Random();
        int numeroSecre = gerador.nextInt(100) + 1;
        
        try(Scanner scanner = new Scanner(System.in)){
            boolean acertou = false;
            int[] chutes = new int[5];
            for(int tentativa = 1; tentativa < 6; tentativa++){
                System.out.println("Digite o numero, dica: contem dois digitos | Tentativa " + tentativa);
                int chute = Integer.parseInt(scanner.nextLine());
                chutes[tentativa - 1] = chute;
                if(chute == numeroSecre){
                    System.out.println("PARABENS! :D");
                    acertou = true;
                    break;
                }
                else if(chute>numeroSecre){
                    System.out.println("O numero secreto é menor!");
                }
                if(chute<numeroSecre){
                    System.out.println("O numero secreto é maior!");
                }
            }
            if (!acertou) {
                System.out.println("Voce utilizou todas as tentativas! :(");
                System.out.println("O numero secreto erá: " + numeroSecre);
            }
            imprimirHistorico(chutes);
        }
    } 
    public static void imprimirHistorico(int[] listaDechutes) {
        System.out.println("Seus chutes foram: ");
            for(int i = 0; i < 5; i++){
                System.out.println(listaDechutes[i]);
            }
    }  
}
