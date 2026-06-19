import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        System.out.println("Servidor iniciado e aguardando conexões na porta 12345...");
        try (
                ServerSocket serverSocket = new ServerSocket(12345);
                Socket socket = serverSocket.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream saidaCliente = new PrintStream(socket.getOutputStream())) {
            System.out.println("Usuário conectado! IP: " + socket.getInetAddress());
            String nomeUsuario = entrada.readLine();
            String mensagem = entrada.readLine();
            if ("thav01986515".equals(mensagem)) {
                System.out.println("Login aceito para o usuário: " + nomeUsuario);
                saidaCliente.println("aceitoLogin");
                System.out.println("Conversa iniciada.");
                System.out.println("Olá " + nomeUsuario + "! Seja bem-vindo ao nosso servidor.");
                while ((mensagem = entrada.readLine()) != null) {
                    if (mensagem.equalsIgnoreCase("Cliente desconectou!")) {
                        break;
                    }
                    System.out.println(mensagem);
                }
            } else {
                System.out.println("Login recusado para o usuário: " + nomeUsuario);
                saidaCliente.println("recusadoLogin");
            }
            System.out.println("Conexão encerrada.");
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}