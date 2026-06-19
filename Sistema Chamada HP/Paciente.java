import java.io.Serializable;

public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L; // Garante a compatibilidade no envio dos bytes

    private String nome;
    private String dataNascimento;
    private long cpf;
    private String sintomaTriagem; // Adicionado para o médico ver o que a triagem anotou

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public long getCpf() { return cpf; }
    public void setCpf(long cpf) { this.cpf = cpf; }

    public String getSintomaTriagem() { return sintomaTriagem; }
    public void setSintomaTriagem(String sintomaTriagem) { this.sintomaTriagem = sintomaTriagem; }
}