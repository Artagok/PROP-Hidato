package Domini;

import java.util.ArrayList;

public class Casella {
    
    private String valor;
    
    private int posicioI;
    private int posicioJ;
    
    private ArrayList<Casella> adjacencies;
    
    
    /* És una creadora per copia, pero en Java no alocata memoria, serveix per inicialitzar camps de la classe*/
    public Casella () {
        
        this.valor = "_";
        this.adjacencies = new ArrayList<Casella> (0);
        this.posicioI = -1;
        this.posicioJ = -1;
    }
    
    /* Getters */
    
    public int getPosicioI () {
        return this.posicioI;
    }
    
    public int getPosicioJ () {
        return this.posicioJ;
    }
    
    public String getValor() {
        return this.valor;
    }
    
    /* Cuidado, hi ha aliasing pq es un tipus no primitiu */
    public ArrayList<Casella> getAdjacencies() {
        return this.adjacencies;
    }
    
    /* Inserta la Casella c pel final al vector d'adjacencies */
    public void addAdjacencia(Casella c) {
        this.adjacencies.add(c);
    }
    
    /* Omple el valor (un String) de la casella */
    public void omplirValor(String val) {
        this.valor = new String(val);
    }
    
    public void setPosicioI (int i) {
        if (i < 0) {
            System.out.println("La posició ha de ser positiva!");
        }
        else {
            this.posicioI = i;
        }
    }
    
    public void setPosicioJ (int j) {
        if (j < 0) {
            System.out.println("La posició ha de ser positiva!");
        }
        else {
            this.posicioJ = j;
        }
    }
    
    /* Retorna l'index del vector adjacencies on apareix val*/
    /* Si no apareix retorna -1 */
    public int searchAdjacencia (String val) {
        int ret = -1; /* -1 significa que no hi és */
        boolean trobat = false;
        for (int i = 0; i < adjacencies.size() && !trobat; ++i) {
            if (adjacencies.get(i).getValor().equals(val)) {
                trobat = true;
                ret = i;
            }
        }
        return ret;
    }
}
