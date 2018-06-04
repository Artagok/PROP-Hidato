/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dades;
import java.io.*;
/**
 *
 * @author carlosaires
 */
public class PersistenciaJugador {
    
    public void guardarJugador(String jug, String usr) throws FileNotFoundException {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(usr)));
            oos.writeObject(jug);
            oos.close();
        }
        catch (IOException e) {}
        
    }
    
    public String cargarJugador(String usr) throws IOException, ClassNotFoundException {
        ObjectInputStream ois;
        String jug = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(usr)));
            jug = (String) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e) {
            return jug;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jug;
        
    }
    
}
