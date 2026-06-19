
import java.util.Scanner;

public class ban {
    public static void main(String[] args) {
        int VotoNulo=139;
        int VotoEmBranco=300;
        candidatos candidatoA=new candidatos();
        candidatoA.setNomecandidato("Thallis Vitor");
        candidatoA.setVotoValido(567);

        candidatos candidatoB=new candidatos();
        candidatoB.setNomecandidato("Luiz Inacio");
        candidatoB.setVotoValido(345);

        candidatos candidatoC=new candidatos();
        candidatoC.setNomecandidato("Jair Bolsonaro");
        candidatoC.setVotoValido(210);

        System.out.println("Candidatos participantes: ["+candidatoA.getNomecandidato()+", "+candidatoB.getNomecandidato()+", "+candidatoC.getNomecandidato()+"]");
        System.out.println("|================================================================|");
        System.out.println("Voce deseja verificar quem ganhou? s/n");
        Scanner scanner=new Scanner(System.in);
        String opçãoSN=scanner.nextLine();
        if(opçãoSN.equals("s")){
           int votosA = candidatoA.getVotoValido();
            int votosB = candidatoB.getVotoValido();
            int votosC = candidatoC.getVotoValido();

            if (votosA > votosB && votosA > votosC) {
                System.out.println("Candidato A (Thallis Vitor) venceu com: "+ candidatoA.getVotoValido());
                System.out.println("Votos nulos: "+ VotoNulo+" | "+VotoEmBranco);

            } else if (votosB > votosA && votosB > votosC) {
                System.out.println("Candidato B (Luiz Inacio) venceu com: "+candidatoB.getVotoValido());
                System.out.println("Votos nulos: "+ VotoNulo+" | "+VotoEmBranco);
            } else {
                System.out.println("Candidato C (Jair Bolsonaro) venceu com: "+candidatoC.getVotoValido());
                System.out.println("Votos nulos: "+ VotoNulo+" | "+VotoEmBranco);
            }
        }
        else{System.out.println("Ok até mais!");}
    }
    
}
