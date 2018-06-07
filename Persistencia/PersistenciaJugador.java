/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.*;

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
        
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(usr)));
            String jug = (String) ois.readObject();
            ois.close();
            return jug;
        }
        catch (FileNotFoundException e) {
            return "noexisteix";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "noexisteix";
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "noexisteix";
        }
        
    }
   
}
