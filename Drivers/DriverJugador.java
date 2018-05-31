package Drivers;
import java.util.ArrayList;
import java.util.Scanner;
import Domini.*;



public class DriverJugador {
    
    public static void main(String[] args) {
        
        System.out.println("Quina acció vols realitzar?" + '\n' + "CREARJUGADOR, GETNOMJUGADOR, GETCONTRASENYA, GETPARTIDES, SETNOM, SETCONTRASENYA, EXIT"); 
        /* Provem les funciones de la classe Jugador */
        
        Scanner scanner = new Scanner(System.in);
        String accio = scanner.next();
        Jugador j = null;
        
        while(!accio.equals("EXIT")) {    
            
            System.out.println("Has triat: "+accio);
            switch(accio) {
                
                /* Provem crear jugador*/
                case "CREARJUGADOR":
                    System.out.println("Introdueix el teu nom d'usuari: ");
                    String nom = scanner.next();
                    boolean sonIguals = false;
                    while (!sonIguals) {
                        System.out.println("Ara introdueix la teva contrasenya");
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
                    break;
        
                
                case "GETNOMJUGADOR":
                    if (j != null) {
                        System.out.println(j.getNomJugador());
                    }
                    else System.out.println("No has creat un jugador!");
                    break;
                
                case "GETCONTRASENYA":
                    if (j != null) {
                        System.out.println(j.getContrasenya());
                    }
                    else System.out.println("No has creat un jugador!");
                    break;
                
                case "GETPARTIDES":
                    if (j != null) {
                        ArrayList<Partida> partides = j.getPartidesJugador();
                        for(int i = 0; i < partides.size(); i++) {
                            System.out.println("Partida: " + i + " formada per:");
                            System.out.println("Jugador amb id: " + partides.get(i).getJugador());
                            System.out.println("Taulell amb id: " + partides.get(i).getTaulell().getIdTaulell());
                            System.out.println("");

                        }
                    }
                    else System.out.println("No has creat un jugador!");
                    break;
                
                case "SETNOM":
                    if (j != null) {
                        System.out.println("Introdueix el nou nom del jugador");
                        String nouNom = scanner.next();
                        j.setNomJugador(nouNom);
                        System.out.println("El teu nou nom és: ");
                        System.out.println(j.getNomJugador());
                    }
                    else System.out.println("No has creat un jugador!");
                    break;
                
                case "SETCONTRASENYA":
                    if (j != null) {
                        System.out.println("Introdueix la nova contrasenya del jugador: ");
                        String novaC = scanner.next();
                        System.out.println("Torna a introduïr la contrasenya: ");
                        String novaC2 = scanner.next();
                        if(novaC.equals(novaC2)) {
                            j.setContrasenya(novaC);
                            System.out.println("La teva nova contrasenya és: ");
                            System.out.println(j.getContrasenya());
                        }
                        else System.out.println("Les contrasenyes no coincideixen!");
                        
                        
                    }
                    else System.out.println("No has creat un jugador!");
                    break;
                    
                default:
                    System.out.println("Comanda errònea");
                    break;
            }
            accio = scanner.next();
        }
    }
}