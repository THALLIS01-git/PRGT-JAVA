package Abstrac_Pratica;

public class usuario extends herança{
  @Override
  public void nome(){
    System.out.println("Digite seu nome");
  }
  @Override
  public void cpf(){
    System.out.println("Digite seu cpf");
  }
  @Override
  public void nascimento(){
    System.out.println("Digite sua data de nascimento");
  }
  @Override
  public void idade(int valorIdade){
    System.out.println("A idade digitada foi:"+ valorIdade);
  }

}