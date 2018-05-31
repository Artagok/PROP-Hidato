import Domini.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Benvingut, selecciona una acció:" + '\n' + "NOVAPARTIDA, EXIT");
        Scanner scanner = new Scanner(System.in);
        String accio = scanner.next();
        int id = 0;
        while (!accio.equals("EXIT")) {
            
            switch (accio) {
                
                case "NOVAPARTIDA":
                    
                    Jugador j = null;
                    Taulell t = null;
                    
                    System.out.println("Introdueix el teu nom d'usuari: ");
                    String nom = scanner.next();
                    boolean sonIguals = false;
                    while(!sonIguals){
                        System.out.println("Introdueix la teva contrasenya: ");
                        String pass = scanner.next();
                        System.out.println("Torna a introduïr la contrasenya: ");
                        String pass2 = scanner.next();
                        
                        if(pass.equals(pass2)) {
                            j = new Jugador(nom, pass);
                            System.out.println("La teva nova contrasenya és: ");
                            System.out.println(j.getContrasenya());
                            sonIguals = true;
                        
                        }
                        
                        else{
                            System.out.println("Les contrasenyes no coincideixen!");
                        }
                    }
                    
                    System.out.println("Selecciona un tipus:" + '\n' + "DEFINIRHIDATO, QUIT");
                    String tipus = scanner.next();
                    while(!tipus.equals("QUIT")) {
                        switch(tipus){
                                
                            case "DEFINIRHIDATO":
                                System.out.println("Entra el hidato que vols jugar");
                                t = new Taulell(id);
                                ++id;
                                t.readTaulell();
                                String adj = t.getTipusAdjacencia();
                                int prefixats = t.countCasellesNum();
                                int interrogants = t.countCasellesInterrogant();
                           
                                /* Li podem dir al usuari la dificultat que te */
                                int qDif = prefixats/(prefixats+interrogants);
                                if((qDif > 0.34) || interrogants < 8) System.out.println("Considerem que aquest Hidato es FACIL");
                                else if((qDif > 0.29) || (interrogants > 8 && interrogants < 12)) System.out.println("Considerem que aquest Hidato es NORMAL");
                                else if ( interrogants >= 12) {
                                    System.out.println("Considerem que aquest Hidato es DIFICIL");
                                }
                                
                                Partida partida = new Partida(j,t); 
                                
                                System.out.println("Vols resoldre o que sigui resolt?:" + '\n' + "PLAY, SOLUTION, ENRERE");
                                
                                String opcio = scanner.next();
                                while(!opcio.equals("ENRERE")){
                                    switch(opcio) {
                                         
                                        case "SOLUTION":
                                            int i1 = partida.getTaulell().getICasellaInicial();
                                            int j1 = partida.getTaulell().getJCasellaInicial();
                                            Casella c = t.getCasellaByIndexes(i1,j1);
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
                                            
                                            
                                        case "PLAY":
                                            System.out.println("Al jugar pots fer les següents accions: " + '\n' + "POSARNUM, ESBORRARNUM, BACK, RESET, VALIDAR, RESOLHIDATO, ABANDONAR");
                                            String accio2 = scanner.next();
                                            
                                            while(!accio2.equals("ABANDONAR")){
                                                
                                                if(accio2.equals("POSARNUM")){
                                                    System.out.println("Has d'introduïr i (espai) j (espai) valor");
                                                    
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
                                                    else{
                                                        int posI = Integer.parseInt(valorI);
                                                        int posJ = Integer.parseInt(valorJ);
                                                        if(posI < 0 || posI >= partida.getTaulell().getNumFiles() || posJ < 0 || posJ >=partida.getTaulell().getNumColumnes()) {
                                                            System.out.println("Aquesta posicio no es valida");
                                                        }
                                                        else {
                                                            partida.omplirCasella(posI, posJ, valorCasella);
                                                        }
                                                    }
                                                    partida.getTaulell().imprimirTaulell();
                                                }
                                                
                                                else if(accio2.equals("BACK")) {
                                                    System.out.println("Només pots fer BACK un sol cop!");
                                                    partida.movimentEnrere();
                                                    Taulell t2 = partida.getTaulell();
                                                    System.out.println("El teu taulell ha quedat així: ");
                                                    t2.imprimirTaulell();
                                                }
                                        
                                                else if(accio2.equals("RESET")) {
                                                    System.out.println("Estàs segur que vols resetejar l'Hidato: Y/N");
                                                    String resposta = scanner.next();
                                                    if (resposta.equals("Y")) {
                                                        partida.resetT();
                                                        System.out.println("S'ha resetejat l'Hidato:");
                                                        partida.getTaulell().imprimirTaulell();
                                                    }
                                                }
                                                else if(accio2.equals("VALIDAR")) {
                                                    boolean solValida = partida.getTaulell().esValid();
                                                    if(solValida){
                                                        System.out.println("Felicitats, has resolt correctament l'Hidato!");
                                                    }
                                                    else System.out.println("Oh, aquesta solucio no es correcta");
                                                }
                                                else if (accio2.equals("RESOLHIDATO")) {
                                                    partida.resetT();
                                                    int i2 = partida.getTaulell().getICasellaInicial();
                                                    int j2= partida.getTaulell().getJCasellaInicial();
                                                    Casella c1 = partida.getTaulell().getCasellaByIndexes(i2,j2);
                                                    boolean b1;
                                                    b1 = partida.getTaulell().resoldreHidato(c1);
                                                    if(b1) {
                                                        System.out.println("L'Hidato té una o més solucions");
                                                        System.out.println("Una possible solució és la següent");
                                                        partida.getTaulell().imprimirTaulell();
                                                    }
                                                    else {
                                                        System.out.println ("L'Hidato és irresoluble");
                                                    }
                                                }
                                                
                                                else if (accio2.equals("ESBORRARNUM")) {
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
                                                    
                                                }
                                                
                                                else System.out.println("Comanda errònea");
                                                System.out.println("TRIA:   POSARNUM, ESBORRARNUM, BACK, RESET, VALIDAR, RESOLHIDATO, ABANDONAR");
                                                accio2 = scanner.next();
                                            }
                                            
                                            break;
                                
                                        default:
                                            System.out.println("Comanda errònea");
                                            break;
                                    }
                                    System.out.println("TRIA: PLAY, SOLUTION, ENRERE");
                                    opcio = scanner.next();
                                }
                                break;
                        
                            default:
                                System.out.println("Comanda errònea");
                                break;
                        }
                        System.out.println("TRIA: DEFINIRHIDATO, QUIT");
                        tipus = scanner.next();
                    }
                
                default:
                    System.out.println("Comanda errònea");
                    break;
                   
            }
            System.out.println("TRIA:  NOVAPARTIDA, EXIT");
            accio = scanner.next(); 
        }
    scanner.close();
    }
}
