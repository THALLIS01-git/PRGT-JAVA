import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Olá digite seu nome");
            String name = scanner.nextLine();
            System.out.println("Ok " + name + " agora digite sua idade");
            int idade = Integer.parseInt(scanner.nextLine());
            if(idade<18){
                System.out.println("Ops! Voce ainda não pode usar este recurso");
            }
            else if(idade>=18){
                System.out.println("Ok conseguimos validar sua idade!");
                Thread.sleep(2300);
                System.out.println("Agora preciso que voce digite uma senha em numeros apenas");
                Integer senha = scanner.nextInt();
                Thread.sleep(4000);
                scanner.nextLine();
                System.out.println("Ok, agora para continuarmos digite a sua senha que acabou de digitar");
                int senhaVerif = Integer.parseInt(scanner.nextLine());
                if(senhaVerif == senha){
                    System.out.println("senha correta!");
                    Thread.sleep(3000);
                    System.out.println("Agora digite uma frase qualquer para calcularmos quantos caracteres ela possui");
                    String frase = scanner.nextLine();
                    System.out.println(frase.length());
                    System.out.println("Ok agora digite um numero inteiro");
                    int numeroInt = Integer.parseInt(scanner.nextLine());
                    System.out.println(numeroInt);
                }
                else{System.out.println("Senha não corresponde!");}
                
            }
        }
    }
}
