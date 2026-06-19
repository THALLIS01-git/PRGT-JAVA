import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PcMedico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TERMINAL DO MÉDICO ===");

        while (true) {
            System.out.print("\nPressione ENTER para chamar o próximo paciente para Consulta (ou digite 'sair'): ");
            String comando = scanner.nextLine();
            if (comando.equalsIgnoreCase("sair")) break;

            try (Socket socket = new Socket("127.0.0.1", 12345);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                out.writeUTF("MEDICO:PUXAR_PROXIMO");
                out.flush();

                System.out.println("[Aguardando] Esperando paciente liberado pela triagem...");
                Paciente paciente = (Paciente) in.readObject();

                // Simulação da Sala de Espera / Chamada do médico
                System.out.println("\n-------------------------------------------------------");
                System.out.println("📢 [PAINEL SALA DE ESPERA]: PACIENTE " + paciente.getNome().toUpperCase() + " COMPARECER AO CONSULTÓRIO 01!");
                System.out.println("-------------------------------------------------------");

                System.out.println("\nProntuário Técnico na Tela:");
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("Nascimento: " + paciente.getDataNascimento());
                System.out.println("Sintomas relatados na Triagem: " + paciente.getSintomaTriagem());
                
                System.out.println("\n[Atendimento] Realizando consulta médica... Pressione ENTER para finalizar o atendimento.");
                scanner.nextLine();
                System.out.println("Atendimento concluído! Paciente liberado.");

            } catch (Exception e) {
                System.out.println("[Erro] Falha de comunicação ou Servidor desconectado.");
            }
        }
        scanner.close();
    }
}