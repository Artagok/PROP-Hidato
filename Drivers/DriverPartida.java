package Drivers;
import java.util.ArrayList;
import java.util.Scanner;
import Domini.Partida;
import Domini.Jugador;
import Domini.Casella;
import Domini.Taulell;

public class DriverPartida {
    
    public static void main(String[] args) {
        
        System.out.println("Benvingut a la classe partida, primer de tot introdueix el taulell");
        
        Taulell t = new Taulell(0);
        t.readTaulell();
        Jugador jugador = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix el teu nom de jugador");
        String nom = scanner.next();
        boolean sonIguals = false;
        while (!sonIguals) {
            System.out.println("Ara introdueix la teva contrasenya");
            String pass = scanner.next();
            System.out.println("Torna a introduïr la contrasenya: ");
            String pass2 = scanner.next();
            if(pass.equals(pass2)) {
                jugador = new Jugador(nom, pass);
                System.out.println("La teva nova contrasenya és: ");
                System.out.println(jugador.getContrasenya());
                sonIguals = true;
            
            }
            
            else{
                System.out.println("Les contrasenyes no coincideixen!");
            }
        }
            Partida partida = new Partida(jugador, t);
        
        System.out.println("Si ja tens el taulell selecciona una accio: " + '\n' + "POSARNUM, ESBORRARNUM, BACK, VALIDARSOLUCIO, RESOLHIDATO, RESET, EXIT");
        String accio = scanner.next();
        
        while (!accio.equals("EXIT")) {
            
            switch (accio) {
                
                case "POSARNUM":
                
                    System.out.println("Introdueix una posicio i (espai) j (espai) valor");
                    String valorI = scanner.next();
                    String valorJ = scanner.next();
                    boolean esNumero = true;
                    try {
                        Integer.parseInt(valorI);
                    }
                    catch(NumberFormatException exc1){
                         esNumero = false;
                    }
                    try {
                        Integer.parseInt(valorJ);
                    }
                    catch(NumberFormatException exc2){
                         esNumero = false;
                     }
                    String valorCasella = scanner.next();
                    if(!esNumero) {
                        System.out.println("Alguna de les dues posicions no es un numero");
                    }
                    else {
                         int posI = Integer.parseInt(valorI);
                         int posJ = Integer.parseInt(valorJ);
                         if(posI < 0 || posI >= partida.getTaulell().getNumFiles() || posJ < 0 || posJ >=partida.getTaulell().getNumColumnes()) {
                             System.out.println("Aquesta posicio no es valida");
                         }
                         else {
                             partida.omplirCasella(posI, posJ, valorCasella);
                         }
                    }
                    System.out.println("El hidato té els següents números: ");
                    partida.getTaulell().imprimirTaulell();
                    break;
                
                case "ESBORRARNUM":
                    System.out.println("Introdueix una posicio i (espai) j del valor que vols esborrar");
                    String valorI2 = scanner.next();
                    String valorJ2 = scanner.next();
                    
                    boolean esNumero2 = true;
                    try {
                        Integer.parseInt(valorI2);
                    }
                    catch(NumberFormatException exc3){
                         esNumero2 = false;
                    }
                    try {
                        Integer.parseInt(valorJ2);
                    }
                    catch(NumberFormatException exc4){
                         esNumero2 = false;
                    }
                    if(!esNumero2) {
                        System.out.println("Alguna de les dues posicions no es un numero");
                    }
                    else {
                         int posI2 = Integer.parseInt(valorI2);
                         int posJ2 = Integer.parseInt(valorJ2);
                         if(posI2 < 0 || posI2 >= partida.getTaulell().getNumFiles() || posJ2 < 0 || posJ2 >= partida.getTaulell().getNumColumnes()) {
                             System.out.println("Aquesta posicio no es valida");
                         }
                         else {
                             partida.esborrarCasella(posI2, posJ2);
                         }
                    }
                    System.out.println("El hidato té els següents números: ");
                    partida.getTaulell().imprimirTaulell();
                    break;
                
                case "BACK":
                    System.out.println("Només pots fer BACK un sol cop!");
                    partida.movimentEnrere();
                    
                    Taulell actualT = partida.getTaulell();
                    
                    System.out.println("El teu taulell ha quedat així: ");
                    actualT.imprimirTaulell();
                    break;
                    
                case "VALIDARSOLUCIO":
                    if (partida.getTaulell().esValid())
                        System.out.println("Solució correcta!");
                    else
                        System.out.println("Solució incorrecta!");
                    break;
                    
                case "RESOLHIDATO":
                    /* De moment quan l'usuari vulgui la solució nosaltres el resetejarem primer i després el mostrarem solucionat pel nostre algorisme */
                    
                    partida.resetT();
                    int i = partida.getTaulell().getICasellaInicial();
                    int j = partida.getTaulell().getJCasellaInicial();
                    Casella c = t.getCasellaByIndexes(i,j);
                    boolean b;
                    b = t.resoldreHidato(c);
                    if(b) {
                        System.out.println("L'Hidato té una o més solucions");
                        System.out.println("Una possible solució és la següent");
                        t.imprimirTaulell();
                    }
                    else {
                        System.out.println ("L'Hidato és irresoluble");
                    }
                    break;
                    
                case "RESET":
                    System.out.println("Estàs segur que vols resetejar l'Hidato: Y/N");
                    String resposta = scanner.next();
                    if (resposta.equals("Y")) {
                        partida.resetT();
                        System.out.println("S'ha resetejat l'Hidato:");
                        partida.getTaulell().imprimirTaulell();
                    }
                    break;
                
                default:
                    System.out.println("Comanda errònea");
                    break;
            }
            accio = scanner.next();
        }
        
        scanner.close();
    }
}
