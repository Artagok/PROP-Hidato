/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domini;

import com.google.gson.Gson;
import dades.ControladorPersistencia;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 *
 * @author carlosaires
 */
public class ControladorDomini {
    
    private ControladorPersistencia cpe = new ControladorPersistencia();
    Gson gson = new Gson();    
    public ControladorDomini() {
        
    }
    
    public boolean validarLogin(String usr) throws IOException, FileNotFoundException, ClassNotFoundException {
        String jugadorLoaded;
        jugadorLoaded = cpe.cargarJugador(usr);
        if(jugadorLoaded.equals("noexisteix"))return false;
        else return true;
    }
    
    public boolean validarRegisterCd(String usr1, String pwd1, Boolean b) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        if((cpe.cargarJugador(usr1)).equals("noexisteix")) {
            if(b) {
               Jugador j = new Jugador(usr1,pwd1); 
               String jug = gson.toJson(j);
               cpe.guardarJugador(jug, usr1);
            }
            return true;
        }
        else return false; 
    }

    public boolean comprovarPass(String usr, String pwd) throws IOException, FileNotFoundException, ClassNotFoundException {
        String jugadorAux;
        jugadorAux = cpe.cargarJugador(usr);
        Jugador j =gson.fromJson(jugadorAux, Jugador.class);
            if ((j.getContrasenya()).equals(pwd)) {
                return true;
            }
            else return false;
    }
     
 }
