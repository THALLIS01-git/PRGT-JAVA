
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try {
            // 1. Sua lista de nomes
            List<String> nomes = new ArrayList<>();
            
            // Vamos adicionar alguns nomes só para testar o salvamento
            nomes.add("João");
            nomes.add("Maria");
            nomes.add("Pedro");

            // 2. Criar o objeto Gson que vai transformar a lista em texto
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // 3. Instanciar a sua classe de funções
            Funcoes funcao1 = new Funcoes();
            
            // 4. CHAMA A FUNÇÃO DE SALVAR (vamos passar a lista e o gson para ela)
            System.out.println("Salvando os nomes no arquivo...");
            funcao1.salvarNomes(nomes, gson);
            System.out.println("Salvo com sucesso!");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
