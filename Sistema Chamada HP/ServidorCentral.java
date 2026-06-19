import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServidorCentral {
    // Filas em memória para gerenciar o fluxo dos pacientes
    private static final BlockingQueue<Paciente> filaTriagem = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Paciente> filaMedico = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        System.out.println("[SERVIDOR] Iniciando o Servidor Central do Hospital...");
        
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("[SERVIDOR] Aguardando conexões na porta 12345 (localhost)...");

            while (true) {
                // Aceita conexões e cria uma Thread para não travar o servidor
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVIDOR] Erro no servidor: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                // Lendo o protocolo de comando enviado pelo cliente
                String comando = in.readUTF();

                switch (comando) {
                    case "RECEPCAO:NOVO_PACIENTE":
                        Paciente pRecepcao = (Paciente) in.readObject();
                        filaTriagem.put(pRecepcao);
                        System.out.println("[SERVIDOR] " + pRecepcao.getNome() + " entrou na fila da Triagem. Total na fila: " + filaTriagem.size());
                        break;

                    case "TRIAGEM:PUXAR_PROXIMO":
                        // Se a fila estiver vazia, ele bloqueia e espera até entrar alguém
                        Paciente pTriagem = filaTriagem.take();
                        out.writeObject(pTriagem);
                        out.flush();
                        System.out.println("[SERVIDOR] " + pTriagem.getNome() + " enviado para o PC da Triagem.");
                        break;

                    case "TRIAGEM:FINALIZAR":
                        Paciente pFinalizado = (Paciente) in.readObject();
                        filaMedico.put(pFinalizado);
                        System.out.println("[SERVIDOR] " + pFinalizado.getNome() + " triado com sucesso e movido para a fila do Médico.");
                        break;

                    case "MEDICO:PUXAR_PROXIMO":
                        // Se a fila do médico estiver vazia, espera até a triagem mandar alguém
                        Paciente pMedico = filaMedico.take();
                        out.writeObject(pMedico);
                        out.flush();
                        System.out.println("[SERVIDOR] " + pMedico.getNome() + " enviado para o PC do Médico.");
                        break;
                }

            } catch (Exception e) {
                // Conexão finalizada normalmente após processar o comando
            }
        }
    }
}