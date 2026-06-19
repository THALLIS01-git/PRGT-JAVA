package Abstrac_Pratica;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        usuario User1=new usuario();
        User1.nome();
        User1.cpf();
        User1.nascimento();
        System.out.println("Digite sua idade:");
        int idadeDigitada=Integer.parseInt(scanner.nextLine());
        User1.idade(idadeDigitada);
    }
}
