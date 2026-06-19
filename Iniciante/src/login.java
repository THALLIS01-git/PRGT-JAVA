import java.util.Scanner;

public class login {
    public static void main(String[] args) throws Exception{
        int senha = 986515;
        System.out.println("Para voce acessar digite a senha");
        try(Scanner scanner = new Scanner(System.in)){
            int verficSenha = Integer.parseInt(scanner.nextLine());
            if(verficSenha == senha){
                System.out.println("Conseguimos validar seu acesso!");
            }
            else{
                System.out.println("Senha incorreta, aguarde 5 segudos!");
                Thread.sleep(5000);
                System.out.println("Apartir de agora por segurança voce terá 3 tentativas!");
                boolean correto = false;
                for(int i = 1; i<4; i++){
                    System.out.println("Tentativa " + i + " de 3");
                    int senhaTent = Integer.parseInt(scanner.nextLine());
                    if (senhaTent == senha) {
                        System.out.println("Conseguimos validar seu acesso!");
                        correto = true;
                        break;
                    }
                }
                if(!correto){
                System.out.println("Tentativas zeradas, acesso bloqueado");
                }
            }
        }
    }
}
