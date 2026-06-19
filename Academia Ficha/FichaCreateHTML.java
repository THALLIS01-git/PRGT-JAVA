import java.io.FileWriter;
import java.util.Scanner;

public class FichaCreateHTML {

    public static void main(String[] args) {
        try {
            Scanner leitor = new Scanner(System.in);
            String caminhoArquivo = "c:\\Users\\Tiago\\Desktop\\testeFicha\\fichaPreenchida.html";
            FileWriter escritor = new FileWriter(caminhoArquivo);
            System.out.println("--- Sistema de Cadastro de Ficha de Academia ---");
            System.out.print("Nome do aluno(a): ");
            String nomeAluno = leitor.nextLine();
            System.out.print("Data de nascimento: ");
            String dataNascimento = leitor.nextLine();
            System.out.print("Sexo: ");
            String sexo = leitor.nextLine();
            System.out.print("Data da emissão da ficha: ");
            String dataFicha = leitor.nextLine();
            String linhasExercicios = "";
            String[] dorsais = { "Pulley Frente", "Remada Sentado", "Remada Unilateral", "Remada Curvada" };
            String[] peitorais = { "Supino Inclinado", "Peitoral Voador", "Cross-Over", "Paralela Baixa" };
            String[] inferiores = { "Leg Press Horizontal", "Leg Press Vertical", "Cadeira Extensora",
                    "Agachamento Hack" };
            String[] ombros = { "Desenvolvimento Aparelho", "Elevação Lateral", "Elevação Frontal", "Remada Alta" };
            String[] biceps = { "Rosca Mesa Scott", "Rosca Direta Barra", "Rosca Alternada" };
            String[] triceps = { "Tríceps Pulley", "Tríceps Patada", "Tríceps Corda" };
            String[] abdome = { "Stiff Barra", "Cadeira Flexora", "Cadeira Abdutora", "Abdominal" };
            String[][] todosGrupos = { dorsais, peitorais, inferiores, ombros, biceps, triceps, abdome };
            String[] nomesGrupos = { "Dorsais", "Peitorais", "Membros Inferiores", "Ombros", "Bíceps", "Tríceps",
                    "Abdome" };
            for (int i = 0; i < todosGrupos.length; i++) {
                System.out.println("\n=== CONFIGURANDO TREINO DE " + nomesGrupos[i].toUpperCase() + " ===");
                for (String exercicio : todosGrupos[i]) {
                    System.out.print("Incluir '" + exercicio + "'? (s/n): ");
                    String resposta = leitor.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        System.out.print("  -> Quantidade de Séries (ex: 4): ");
                        String series = leitor.nextLine();
                        System.out.print("  -> Repetições (ex: 10a12): ");
                        String repeticoes = leitor.nextLine();
                        linhasExercicios += "<tr>"
                                + "  <td class='grupo'>" + nomesGrupos[i] + "</td>"
                                + "  <td>" + exercicio + "</td>"
                                + "  <td class='centro'>" + series + "</td>"
                                + "  <td class='centro'>" + repeticoes + "</td>"
                                + "</tr>";
                    }
                }
            }
            String htmlFinal = "<html>"
                    + "<head>"
                    + "  <meta charset='UTF-8'>"
                    + "  <style>"
                    + "    body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #121212; color: #ffffff; padding: 30px; margin: 0; }"
                    + "    .container { max-width: 700px; margin: 0 auto; background: #1e1e1e; border-radius: 12px; border: 2px solid #ff0055; box-shadow: 0 8px 16px rgba(0,0,0,0.6); overflow: hidden; }"
                    + "    .header { background: #ff0055; padding: 20px; text-align: center; }"
                    + "    .header h1 { margin: 0; font-size: 26px; color: #ffffff; text-transform: uppercase; letter-spacing: 3px; font-weight: 800; }"
                    + "    .conteudo { padding: 25px; }"
                    + "    .topo-ficha { display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin-bottom: 30px; background: #151515; padding: 15px; border-radius: 8px; border-left: 4px solid #ff0055; }"
                    + "    .info-item { font-size: 14px; color: #aaaaaa; }"
                    + "    .info-item strong { color: #ffffff; font-size: 16px; display: block; margin-top: 2px; }"
                    + "    h2 { color: #ff0055; font-size: 18px; text-transform: uppercase; border-bottom: 2px solid #333; padding-bottom: 8px; margin-top: 0; }"
                    + "    table { width: 100%; border-collapse: collapse; margin-top: 10px; background: #151515; border-radius: 6px; overflow: hidden; }"
                    + "    th { background: #2d2d2d; color: #ff0055; text-align: left; padding: 12px; font-size: 12px; text-transform: uppercase; letter-spacing: 1px; }"
                    + "    td { padding: 12px; border-bottom: 1px solid #252525; color: #eeeeee; font-size: 15px; }"
                    + "    tr:hover { background: #222222; }"
                    + "    .centro { text-align: center; }"
                    + "    .grupo { font-weight: bold; color: #ff0055; font-size: 13px; text-transform: uppercase; }"
                    + "    .footer { text-align: center; padding: 15px; color: #666666; font-size: 12px; background: #151515; margin-top: 20px; }"
                    + "  </style>"
                    + "</head>"
                    + "<body>"
                    + "  <div class='container'>"
                    + "    <div class='header'>"
                    + "      <h1> PROGRAMA DE MUSCULAÇÃO test</h1>"
                    + "    </div>"
                    + "    <div class='conteudo'>"
                    + "      "
                    + "      <div class='topo-ficha'>"
                    + "        <div class='info-item'>Aluno(a): <strong>" + nomeAluno + "</strong></div>"
                    + "        <div class='info-item'>Nascimento: <strong>" + dataNascimento + "</strong></div>"
                    + "        <div class='info-item'>Sexo: <strong>" + sexo + "</strong></div>"
                    + "        <div class='info-item'>Emissão: <strong>" + dataFicha + "</strong></div>"
                    + "      </div>"
                    + "      "
                    + "      "
                    + "      <h2>📋 Prescrição de Treinamento</h2>"
                    + "      <table>"
                    + "        <thead>"
                    + "          <tr>"
                    + "            <th style='width: 25%;'>Grupo Muscular</th>"
                    + "            <th style='width: 45%;'>Exercício</th>"
                    + "            <th style='width: 15%;' class='centro'>Séries</th>"
                    + "            <th style='width: 15%;' class='centro'>Repetições</th>"
                    + "          </tr>"
                    + "        </thead>"
                    + "        <tbody>"
                    + "          " + linhasExercicios // Injeta todas as linhas coletadas aqui!
                    + "        </tbody>"
                    + "      </table>"
                    + "    </div>"
                    + "    <div class='footer'>"
                    + "      Ficha gerada eletronicamente pelo Sistema FichaCreate"
                    + "    </div>"
                    + "  </div>"
                    + "</body>"
                    + "</html>";
            escritor.write(htmlFinal);
            escritor.close();
            leitor.close();
            System.out.println("\n[SUCESSO] Ficha HTML completa gerada na sua Área de Trabalho!");
        } catch (Exception e) {
            System.out.println("[ERRO] Algo deu errado!");
            e.printStackTrace();
        }
    }
}