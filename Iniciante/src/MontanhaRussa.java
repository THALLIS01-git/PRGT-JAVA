import java.util.Scanner;

public class MontanhaRussa {
    public static void main(String[] args) throws Exception{
        System.out.println("Seja bem vindo a nossa montanha russa!");
        Thread.sleep(2000);
        System.out.println("Para voce passar precissa passar por uma verificação");
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Qual seu nome?");
            String nome = scanner.nextLine();
            System.out.println("Qual sua idade");
            int idade = Integer.parseInt(scanner.nextLine());
            if(idade>=12){
                System.out.println("Ok qual sua altura?");
                double altura = Double.parseDouble(scanner.nextLine());
                if(altura>=1.50){
                    System.out.println("OK "+ nome + " voce com " + idade + "anos e com a altura " + altura + "pode entrar na montanha russa!");
                }
                else if(altura<1.50){
                    System.out.println("Desculpe " + nome + " voce não pode entrar por conta de sua altura!");
                }
            }
            else if(idade<12){System.out.println("Desculpe " + nome + " voce não possui idade suficiente para entrar!");}
        }

        
    }
}
