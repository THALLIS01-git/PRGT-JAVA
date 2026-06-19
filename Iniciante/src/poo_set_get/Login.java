package poo_set_get;
import java.util.Scanner;
public class Login {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        UsuarioOBJ usuario1=new UsuarioOBJ();
        System.out.println("Digite seu primeiro nome:");
        String usuario1nome1=scanner.nextLine();
        usuario1.setPrimeiroNome(usuario1nome1);
        System.out.println("Digite seu segundo nome:");
        String usuario2nome2=scanner.nextLine();
        usuario1.setSegundoNome(usuario2nome2);
        System.out.println("Digite seu CPF utilizando apenas numeros");
        long usuario1cpf=Long.parseLong(scanner.nextLine());
        usuario1.setCpf(usuario1cpf);
        System.out.println("Digite seu e-mail");
        String usuario1email=scanner.nextLine();
        usuario1.setEmail(usuario1email);
        System.out.println("Digite sua senha:");
        int usuario1senha=Integer.parseInt(scanner.nextLine());
        usuario1.setSenha(usuario1senha);
        System.out.println("Informações guardadas do usuario1: Nome: "+usuario1.getPrimeiroNome()+" "+usuario1.getSegundoNome()+" CPF: "+usuario1.getCpf()+" E-mail: "+usuario1.getEmail()+" Senha: "+usuario1.getSenha());
        System.out.println("Acessando...");
  }
}