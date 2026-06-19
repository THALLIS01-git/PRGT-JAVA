package Teste;

public class carro extends modeloC{
    @Override
    public void acelerar(){
        System.out.println("Carro acelerando!");
    }
    @Override
    public void reduzir(){
        System.out.println("Carro reduzindo!");
    }
    @Override
    public void buzinar(){
        System.out.println("Carro buzinando!");
    }
    @Override
    public void ligar(){
        System.out.println("Carro ligando!");
    }
}
