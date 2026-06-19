import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Cliente iniciado...");
        try (
                Socket socket = new Socket("localhost", 12345);
                Scanner scanner = new Scanner(System.in);
                PrintStream saida = new PrintStream(socket.getOutputStream());
                BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Conectado ao servidor!");
            System.out.print("Digite seu nome de usuário para exibição: ");
            String usuarioNome = scanner.nextLine();
            saida.println(usuarioNome);
            System.out.println("\n--- Tela de Login ---");
            System.out.print("Digite seu usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine();
            String login = usuario + senha;
            saida.println(login);
            String statusLogin = entradaServidor.readLine();
            if ("aceitoLogin".equals(statusLogin)) {
                System.out.println("\n--- Chat Autorizado e Iniciado ---");
                System.out.println("Digite suas mensagens abaixo. Para encerrar, digite 'sair'.\n");
                System.out.println("__________________________________________________________");
                while (true) {
                    String teclado = scanner.nextLine();
                    if (teclado.equalsIgnoreCase("sair")) {
                        saida.println("Cliente desconectou!");
                        System.out.println("Você saiu da conversa.");
                        break;
                    }
                    saida.println(usuarioNome + ": " + teclado);
                }
            } else {
                System.out.println("Login recusado pelo servidor! Conexão encerrada.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
}