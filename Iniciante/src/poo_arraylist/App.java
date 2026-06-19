package poo_arraylist;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
       List<User> users=new ArrayList<>();
       int i=0;
       while(i<10){
        User atual=new User("Nome"+i,"Sobrenome"+i);
        users.add(atual);
        i++;
       }
       System.out.println(users.get(0).getPrimeiroNome()+" "+users.get(0).getSegundoNome());
    }
    
}
