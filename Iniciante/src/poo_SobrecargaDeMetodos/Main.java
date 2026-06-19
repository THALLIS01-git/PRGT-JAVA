package poo_SobrecargaDeMetodos;

public class Main {
    public static void main(String[] args) {
        User userA=new User("Thallis", "Vitor");
        System.out.println(userA.output());
        System.out.println(userA.output(false));

        User userB=new User("Fulado", "de Tal");
        System.out.println(userB.toString());
    }
}
