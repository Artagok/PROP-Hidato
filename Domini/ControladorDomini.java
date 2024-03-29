/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domini;
import com.google.gson.Gson;
import Persistencia.ControladorPersistencia;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        
        if(cpe.cargarJugador(usr).equals("noexisteix")) return false;
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
        Jugador j = gson.fromJson(jugadorAux, Jugador.class);
            if ((j.getContrasenya()).equals(pwd)) {
                return true;
            }
            else return false;
    }

    public boolean resultatProposarHidato(String header, String matriu) {
       Taulell t = new Taulell(-1);
       t.readTaulell(header, matriu);
       int i = t.getICasellaInicial();
       int j = t.getJCasellaInicial();
       t.resoldreHidato(t.getTaulell().get(i).get(j));
       return (t.esValid());
    }

    public boolean validarHidatoJugar(String header, String matriuResultat) {
       Taulell t = new Taulell(-1);
       t.readTaulell(header, matriuResultat);
       return t.esValid();
    }

    public String resoldreHidatoJugar(String header, String matriuResultat) {
        Taulell t = new Taulell(-1);
        t.readTaulell(header, matriuResultat);
        //t.imprimirTaulell();
        int i = t.getICasellaInicial();
        int j = t.getJCasellaInicial();
        t.resoldreHidato(t.getTaulell().get(i).get(j));
        return t.taulellToString();
    }

    public String generaHidato(int dif, String usr) throws IOException, FileNotFoundException, ClassNotFoundException {
        Taulell t = new Taulell(-2);
        String jugadorAux = cpe.cargarJugador(usr);
        Jugador j = gson.fromJson(jugadorAux, Jugador.class);
        Partida p = new Partida(j, t);
        p.generarHidato(dif);
        String head = new String("");
        head = p.getTaulell().calculaHead();
        return head + p.getTaulell().taulellToString();
        
        
        
        
    }

    public String definirHidato(int dif, String tC, String tA, String usr) throws IOException, FileNotFoundException, ClassNotFoundException {
        Taulell t = new Taulell(-3);
        String jugadorAux = cpe.cargarJugador(usr);
        Jugador j = gson.fromJson(jugadorAux, Jugador.class);
        Partida p = new Partida(j, t);
        p.definirHidato(dif,tC,tA);
        String head = new String("");
        head = p.getTaulell().calculaHead();
        return head + p.getTaulell().taulellToString();
    }

    public String ajuda1(String header, String matriuResultat) {
        Taulell t = new Taulell(-4);
        t.readTaulell(header, matriuResultat);
        if(t.esValid()) return"JaEraValid";
        return t.ajuda();
        
    }
    
}
