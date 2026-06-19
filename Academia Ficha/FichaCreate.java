import java.io.FileWriter;
import java.util.Scanner;

public class FichaCreate {

    public static void main(String[] args) {
        try {
            Scanner leitor = new Scanner(System.in);
            String caminhoArquivo = "c:\\Users\\Tiago\\Desktop\\testeFicha\\fichaPrenchida.txt";
            FileWriter escritor = new FileWriter(caminhoArquivo);
            System.out.println("Digite algo para salvar na ficha:");
            System.out.print("Nome do aluno(a): ");
            String nomeAluno = leitor.nextLine();
            escritor.write("\n" + nomeAluno);
            escritor.write("\n___________________________________________________________________________________");
            System.out.print("Data de nascimento do aluno: ");
            String dataNascimento = leitor.nextLine();
            escritor.write("\n" + dataNascimento);
            System.out.print("Sexo do aluno: ");
            String sexo = leitor.nextLine();
            escritor.write("\n" + sexo);
            System.out.print("Data da emissão da ficha: ");
            String dataFicha = leitor.nextLine();
            escritor.write("\n" + dataFicha);
            escritor.write("\n___________________________________________________________________________________");
            escritor.close();
            leitor.close();
            System.out.println("Arquivo criado com sucesso!");
        } catch (Exception e) {
            System.out.println("Algo deu errado!");
            e.printStackTrace();
        }

    }
}