package poo_arraylist;

public class User {
    private String primeiroNome;
    private String segundoNome;

    public User(String primeiroNome, String segundoNome){
        this.primeiroNome=primeiroNome.toUpperCase();
        this.segundoNome=segundoNome.toUpperCase();
    }

    public void setPrimeiroNome(String primeiroNome){
        this.primeiroNome.toUpperCase();
    }
    public String getPrimeiroNome(){
        return this.primeiroNome;
    }

    public void setSegundoNome(String segundoNome){
        this.segundoNome.toUpperCase();
    }
    public String getSegundoNome(){
        return this.segundoNome;
    }
}
