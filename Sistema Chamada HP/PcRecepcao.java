import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PcRecepcao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TERMINAL DA RECEPÇÃO ===");

        while (true) {
            System.out.print("\nCadastrar novo paciente? (S/N): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                Paciente p = new Paciente();

                System.out.print("Nome do Paciente: ");
                p.setNome(scanner.nextLine());

                System.out.print("Data de Nascimento (dd/mm/aaaa): ");
                p.setDataNascimento(scanner.nextLine());

                System.out.print("CPF (apenas números): ");
                p.setCpf(Long.parseLong(scanner.nextLine()));

                // Conecta ao servidor local e envia os dados
                try (Socket socket = new Socket("127.0.0.1", 12345);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
                    
                    out.writeUTF("RECEPCAO:NOVO_PACIENTE");
                    out.writeObject(p);
                    out.flush();
                    
                    System.out.println("[Sucesso] " + p.getNome() + " enviado para a fila de triagem.");

                } catch (IOException e) {
                    System.out.println("[Erro] Não foi possível conectar ao Servidor Central.");
                }
            } else if (resposta.equalsIgnoreCase("N")) {
                System.out.println("Saindo do sistema de cadastro...");
                break;
            }
        }
        scanner.close();
    }
}