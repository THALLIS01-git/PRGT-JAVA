package poo_SobrecargaDeMetodos;

public class User {
    private String pNome;
    private String sNome;

    public User(String pNome, String sNome){
        this.pNome=pNome;
        this.sNome=sNome;
    }

    public void setPNome(String pNome){
        this.pNome=pNome;
    }
    public String getPNome(){
        return pNome;
    }
    public void setSNome(String sNome){
        this.sNome=sNome;
    }
    public String getSNome(){
        return sNome;
    }

    public String output(){
        return pNome.toUpperCase()+" "+sNome.toUpperCase();
    }

    public String output(boolean variavelTF){
        if(variavelTF){
            return output();
        }
        return pNome;
    }

    public String toString(){
        return "User "+"Primeiro Nome: "+pNome+" "+"Segundo Nome: "+sNome;
    }
}

