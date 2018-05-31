package Drivers;
import java.util.Scanner;
import Domini.Casella;
import Domini.Taulell;

public class DriverTaulell {
    
    public static void main(String[] args) {
        
        System.out.println("Quina acció vols realitzar?" + '\n' + "CREARHIDATO, IMPRIMIRHIDATO, GETIDHIDATO, CASELLESPERCOMPLETAR, RESOLDREHIDATO, VALIDARSOLUCIO, EXIT");  /* Provem les funciones de la classe Taulell */
        Scanner scanner = new Scanner(System.in);
        String accio = scanner.next();
        Taulell t = null;
        int id = 0;
        
        while(!accio.equals("EXIT")) {
            
            System.out.println("Has triat: "+accio);
            switch(accio) {
                /* Provem crear hidato*/
                case "CREARHIDATO":
                    t = new Taulell(id);
                    System.out.println("Introdueix un Hidato");
                    t.readTaulell(); /*Provem la funció de llegir */
                    System.out.println("Hem llegit i arreglat el Hidato");
                    ++id;
                    System.out.println("El hidato que has introduït és: ");
                    t.imprimirTaulell();
                    break;
                
                case "IMPRIMIRHIDATO":
                    if (t != null) {
                        t.imprimirTaulell();
                    }
                    else System.out.println("No has creat un hidato!");
                    break;
                
                case "GETIDHIDATO":
                    if (t != null) {
                        int id1;
                        id1 = t.getIdTaulell();
                        System.out.println(id1);
                    }
                    else System.out.println("No has creat un hidato!");
                    break;
                
                case "CASELLESPERCOMPLETAR":
                    if(t != null) {
                        int count;
                        count = t.countCasellesInterrogant();
                        System.out.println ("Et queden per completar " +count+ " caselles" );
                    }
                    
                    else System.out.println("No has creat un hidato!");
                    break;
                    
                case "RESOLDREHIDATO":
                    if (t != null) {
                        /* Aquí ens retorna la casella que té com a valor "1" */
                        int i = t.getICasellaInicial();
                        int j = t.getJCasellaInicial();
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
                    }
                    else System.out.println("No has creat un hidato!");
                    break;
                
                case "VALIDARSOLUCIO":
                    if (t != null) {
                        boolean b;
                        b = t.esValid();
                        if(b) System.out.println ("La solució és correcta");
                        else System.out.println ("La solució no és correcta");
                    }
                    else System.out.println("No has creat un hidato!");
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
        
        
        
       