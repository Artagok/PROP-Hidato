package Domini;
import java.util.ArrayList;
import java.util.Scanner;
public class Jugador {
    
    private String nomJugador;
    private String contrasenya;
    private ArrayList<Partida> partidesJugador;
    
    public Jugador (String nom, String pwd) {
        this.nomJugador = new String(nom);
        this.contrasenya = new String(pwd);
        this.partidesJugador = new ArrayList<Partida> (0);
    }
    
    /* Getters */
    
    /* Retorna el nom del jugador */
    public String getNomJugador() {
        return this.nomJugador;
    }
    
    /* Retorna el nom de la contrasenya */
    public String getContrasenya() {
        return this.contrasenya;
    }
    
    /* Retorna les partides vinculades al jugador */
    public ArrayList<Partida> getPartidesJugador() {
        return this.partidesJugador;
    }
    
    /* Setters */
    
    /* Actualitza el nom del jugador */
    public void setNomJugador(String nom) {
        this.nomJugador = new String(nom);
    }
    
    /* Actualitza la contrasenya del jugador */ 
    public void setContrasenya(String pwd) {
        this.contrasenya = new String(pwd);
    }
}