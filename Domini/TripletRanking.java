public class TripletRanking {
    
    private String nom;
    private int idTaulell;
    private int puntuacio;
    
    public TripletRanking (String n, int idT, int p) {
        this.nom = n;
        this.idTaulell = idT;
        this.puntuacio = p;
    }
    
    /* Getters */
    
    public String getNom() {
        return this.nom;
    }
    
    public int getIdTaulell() {
        return this.idTaulell;
    }
    
    public int getPuntuacio() {
        return this.puntuacio;
    }
    
    /* Setters */
    
    /* Només setter de puntuacio per quan el jugador faci una puntuacio mes alta que la que tenia en aquest mapa */
    public void setPuntuacio (int p) {
        this.puntuacio = p;
    }
}