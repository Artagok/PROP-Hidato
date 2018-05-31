package Drivers;

import java.util.ArrayList;
import java.util.Scanner;
import Domini.Casella;

public class DriverCasella {
    
    public static void main(String[] args) {
        
        System.out.println("Quina acció vols realitzar?" + '\n' + "CREARCASELLA, OMPLIRVALOR, ADDADJACENCIA, SETPOSICIOI, SETPOSICIOJ, GETVALOR, GETADJACENCIES, GETPOSICIOI, GETPOSICIOJ, EXIT"); 
        /* Provem les funciones de la classe Taulell */
        
        Scanner scanner = new Scanner(System.in);
        String accio = scanner.next();
        Casella c = null;
        
        while(!accio.equals("EXIT")) {    
            
            System.out.println("Has triat: "+accio);
            switch(accio) {
                
                /* Provem crear casella */
                case "CREARCASELLA":
                    c = new Casella();
                    System.out.println("Has creat una casella!");
                    System.out.println("Els paràmetres de la casella: ");
                    
                    System.out.println("El valor és " +c.getValor());
                    System.out.println("La posicio I és " +c.getPosicioI());
                    System.out.println("La posicio J és " +c.getPosicioJ());
                    System.out.println("Size de les adjacencies és " +c.getAdjacencies().size());
                    break;
                    
                /* Provem omplir valor a la casella */
                case "OMPLIRVALOR":
                    if ( c != null) {
                        System.out.println("Introdueix el valor de la casella, pot ser un número, ?, # o * ");
                        String val = scanner.next();
                        c.omplirValor(val);
                        System.out.println("Has introduit el valor: "+val);
                        System.out.println("El valor és " +c.getValor());
                        
                        
                    }
                    else System.out.println("No has creat una casella!");
                    break;
                    
                /* Provem addadjacencia */
                case "ADDADJACENCIA":
                    if (c != null) {
                        System.out.println("Has de crear una nova casella per afegir com a adjacencia");
                         Casella c1 = new Casella();
                         
                        System.out.println("Pots omplirla amb un valor escriu " +'\n' + "SI, NO");
                        String decisio = scanner.next();
                        switch(decisio) {
                            
                            case "SI":
                                System.out.println("Introdueix el valor de la casella, pot ser un número, ?, # o * ");
                                String val2 = scanner.next();
                                c1.omplirValor(val2);
                                break;
                            
                            default:
                            break;
                                
                        }
                        
                        System.out.println("Finalment afegim l'adjacencia");
                        c.addAdjacencia(c1);
                        System.out.println("Size de les adjacencies és " +c.getAdjacencies().size());
                        
                        
                    }
                    else System.out.println("No has creat una casella!");
                    break;
                
                /* Provem setPosicioI */
                case "SETPOSICIOI":
                    if(c != null) {
                        System.out.println("Introdueix el valor de la posició I positiu ");
                        int i = Integer.parseInt(scanner.next());
                        c.setPosicioI(i);
                        System.out.println("La posicio I és " +c.getPosicioI());
                    }
                    
                    else System.out.println("No has creat una casella!");
                    break;
                
                /* Provem setPosicioJ */
                case "SETPOSICIOJ":
                    
                    if (c != null) {
                        System.out.println("Introdueix el valor de la posició J positiu ");
                        int j = Integer.parseInt(scanner.next());
                        c.setPosicioJ(j);
                        System.out.println("La posicio J és " +c.getPosicioJ());
                    }
                    else System.out.println("No has creat una casella!");
                    break;
                    
                /* Provem getValor */
                case "GETVALOR":
                    if (c != null) {
                        String val = c.getValor();
                        System.out.println("El valor de la teva casella és: " +val);
                    }
                    else System.out.println("No has creat una casella!");
                    break;
                
                /* Provem getValor */
                case "GETADJACENCIES":
                    if (c != null) {
                        ArrayList<Casella> adjacenciesaux = c.getAdjacencies();
                        for(int i1 = 0; i1< adjacenciesaux.size(); ++i1) {
                            System.out.println("El valor és " +adjacenciesaux.get(i1).getValor());
                        }
                    }
                    else System.out.println("No has creat una casella!");
                    break;
                    
                    
                 /* Provem getPosicioI */
                case "GETPOSICIOI":
                    
                    if (c != null) {
                        int posI = c.getPosicioI();
                        System.out.println("La teva posició I és: " +posI);
                    }
                    else System.out.println("No has creat una casella!");
                    break;
                
                /* Provem getPosicioJ */
                case "GETPOSICIOJ":
                    
                    if (c != null) {
                        int posJ = c.getPosicioJ();
                        System.out.println("La teva posició J és: " +posJ);
                    }
                    else System.out.println("No has creat una casella!");
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
        
        
        
       