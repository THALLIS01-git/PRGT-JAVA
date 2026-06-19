package poo_set_get;


public class UsuarioOBJ {
    private String primeiroNome;
    private String segundoNome;
    private Long cpf;
    private String email;
    private int senha;
    
    public void setPrimeiroNome(String primeiroNome){
        if(primeiroNome.matches("[a-zA-ZÀ-ú\\\\s]+")){
        this.primeiroNome=primeiroNome.toUpperCase();
        }
        else{
        System.out.println("ERRO: Nome inválido (não use números ou símbolos).");
        this.primeiroNome="ERRO_Nome invalido";
        }
    }
    public String getPrimeiroNome(){
        return this.primeiroNome;
    }

    public void setSegundoNome( String segundoNome){
        if(segundoNome.matches("[a-zA-ZÀ-ú]+")){
        this.segundoNome=segundoNome.toUpperCase();    
        }
        else{
        System.out.println("ERRO: Nome inválido (não use números ou símbolos).");
        this.segundoNome="ERRO_Nome invalido";
        }
    }
    public String getSegundoNome(){
        return this.segundoNome;
    }

    public void setCpf(long cpf){
        if(cpf>=1000000000){
        this.cpf=cpf;
        }
        else if(cpf<1000000000){
        System.out.println("ERRO: Formato de cpf invalido");
        this.cpf=0l;
        }
    }
    public long getCpf(){
        return this.cpf;
    }
    
    public void setEmail(String email){
        if(email.contains("@")){
        this.email=email.toLowerCase();
        }
        else{
        System.out.println("ERRO: Seu endereço não contem @");
        this.email="ERRO_Endereço invalido";
        }
    }
    public String getEmail(){
        return this.email;
    }
    
    public void setSenha(int senha){
        if(senha>=1000){
        this.senha=senha;
        }
        else if(senha<1000){
        System.out.println("ERRO: Senha de nivel baixo");
        this.senha=0;
        }
    }
    public int getSenha(){
        return this.senha;
    }
   }
