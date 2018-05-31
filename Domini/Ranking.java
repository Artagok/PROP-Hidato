//Static perque nomes hi ha un Ranking, la classe no s'instancia i els metodes es cridaran aixi: Ranking.metode()
import java.util.ArrayList;
public class Ranking {
    
    private ArrayList<TripletRanking> ranking = new ArrayList<TripletRanking> (0);
    
    /* Getters */
    
    /* Retorna la puntuacio que te el jugador (nom) al taulell (idTaulell) */
    public int getPuntuacio(String nom, int idTaulell) {
        for (int i = 0; i < ranking.size(); ++i) {
            TripletRanking tr = ranking.get(i);
            if (tr.getNom() == nom && tr.getIdTaulell() == idTaulell)
                return tr.getPuntuacio();
        }
        return (-1); //Significa que no hi havia una entrada amb clau (nom,idTaulell)
    }
    
    /* Retorna quantes entrades hi ha a la BD Ranking */
    public int getSizeRanking() {
        return ranking.size();
    }
    
    /* Retorna totes les puntuacions que te el jugador (nom) */
    public ArrayList<TripletRanking> getPuntuacioByName(String nom) {
        ArrayList<TripletRanking> ret = new ArrayList<TripletRanking> ();
        for (int i = 0; i < ranking.size(); ++i) 
            if (ranking.get(i).getNom().equals(nom))
                ret.add(ranking.get(i));
        return ret;
    }
    
    /* Retorna totes les puntuacions que s'han fet al taulell (idTaulell) */
    public ArrayList<TripletRanking> getPuntuacioByTaulell(int idTaulell) {
        ArrayList<TripletRanking> ret = new ArrayList<TripletRanking> ();
        for (int i = 0; i < ranking.size(); ++i) 
            if (ranking.get(i).getIdTaulell() == idTaulell)
                ret.add(ranking.get(i));
        return ret;
    }
    
    /* Setters */
    
    /* Actualitza la puntuacio (punts) que el jugador (nom) te al taulell (idTaulell) */
    public void setPuntuacio(String nom, int idTaulell, int punts) {
        boolean assignada = false;
         for (int i = 0; i < ranking.size() && !assignada; ++i) {
            TripletRanking tr = ranking.get(i);
            if (tr.getNom() == nom && tr.getIdTaulell() == idTaulell) {
                assignada = true;
                tr.setPuntuacio(punts);
            }
        }
    }
    
    /* Afegeix una entrada a la BD Ranking, la tupla te valor = (nom,idTaulell,punts) */
    public void addEntrada(String nom, int idTaulell, int punts) {
        TripletRanking newEntrada = new TripletRanking(nom, idTaulell, punts);
        ranking.add(newEntrada);
    }

    //S'hauran de tocar els publics de quasi totes les classes --> més correcte tenir privates amb getters i setters
}
