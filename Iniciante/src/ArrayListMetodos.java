import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class ArrayListMetodos {
    public static void main(String[] args) {
        //array sem fim
        List<String> nomes=new ArrayList<>();
        //como adicionar elementos noarray?

        nomes.add("Nome1");
        nomes.add("Nome2");
        nomes.add("Nome3");
        nomes.add("Nome4");
        
        Collections.sort(nomes);
        for(String nome:nomes){
            System.out.println(nome);
        }
    }
}
