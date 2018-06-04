/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dades;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author carlosaires
 */
public class ControladorPersistencia {
    PersistenciaJugador pj = new PersistenciaJugador();
    
    
    
    public ControladorPersistencia() {
        
    }
    
    public void guardarJugador (String jug, String usr) throws FileNotFoundException {
        
        pj.guardarJugador(jug, usr);
    }
    
    public String cargarJugador (String usr) throws FileNotFoundException, IOException, ClassNotFoundException {
        return pj.cargarJugador(usr);
    }
    
    
}
