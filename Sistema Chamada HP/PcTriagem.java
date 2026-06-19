import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PcTriagem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TERMINAL DA TRIAGEM ===");

        while (true) {
            System.out.print("\nPressione ENTER para chamar o próximo paciente para a Triagem (ou digite 'sair'): ");
            String comando = scanner.nextLine();
            if (comando.equalsIgnoreCase("sair")) break;

            try {
                // 1. Puxa o paciente do servidor
                Socket socketPuxar = new Socket("127.0.0.1", 12345);
                ObjectOutputStream outPuxar = new ObjectOutputStream(socketPuxar.getOutputStream());
                ObjectInputStream inPuxar = new ObjectInputStream(socketPuxar.getInputStream());

                outPuxar.writeUTF("TRIAGEM:PUXAR_PROXIMO");
                outPuxar.flush();

                System.out.println("[Aguardando] Esperando paciente aparecer na fila...");
                Paciente paciente = (Paciente) inPuxar.readObject();
                
                socketPuxar.close(); // Fecha essa conexão após receber

                // Simulação da Sala de Espera / Chamada no painel
                System.out.println("\n-------------------------------------------------------");
                System.out.println("📢 [PAINEL SALA DE ESPERA]: PACIENTE " + paciente.getNome().toUpperCase() + " IR PARA A SALA DE TRIAGEM!");
                System.out.println("-------------------------------------------------------");

                System.out.println("\nDados do Paciente na Tela:");
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("CPF: " + paciente.getCpf());
                
                // Realizando a triagem
                System.out.print("\nDigite os sintomas/pressão aferidos pelo Enfermeiro: ");
                paciente.setSintomaTriagem(scanner.nextLine());

                // 2. Devolve o paciente atualizado para o servidor (vai para a fila do médico)
                Socket socketFinalizar = new Socket("127.0.0.1", 12345);
                ObjectOutputStream outFinalizar = new ObjectOutputStream(socketFinalizar.getOutputStream());
                
                outFinalizar.writeUTF("TRIAGEM:FINALIZAR");
                outFinalizar.writeObject(paciente);
                outFinalizar.flush();
                
                socketFinalizar.close();
                System.out.println("[Sucesso] Triagem concluída. Paciente enviado para a fila do Médico.");

            } catch (Exception e) {
                System.out.println("[Erro] Falha de comunicação ou Servidor desconectado.");
            }
        }
        scanner.close();
    }
}