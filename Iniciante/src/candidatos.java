
public class candidatos {
    private String Nomecandidato;
    private int VotoValido;
    public void setNomecandidato(String Nomecandidato){
        this.Nomecandidato=Nomecandidato.toUpperCase();
    }
    public String getNomecandidato(){
        return Nomecandidato;
    }
    public void setVotoValido(int VotoValido){
        this.VotoValido=VotoValido;
    }
    public int getVotoValido(){
        return VotoValido;
    }
}
