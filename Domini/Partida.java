package Domini;
/*** ___PROVA PEL PULL blablalbalblalbla ***/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Partida {
    private Jugador jugador;
    private Taulell taulell;
    private int iAnterior;
    private int jAnterior;
    private String valorAnterior;


    public Partida () {
        this.jugador = null;
        this.taulell = null;
        this.valorAnterior = null;
    }

    public Partida(Jugador j, Taulell t) {
        this.jugador = j;
        this.taulell = t;
        this.valorAnterior = null;
    }

    /* Getters */

    public Taulell getTaulell() {
        return this.taulell;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public int getIAnterior() {
        return this.iAnterior;
    }

    public int getJAnterior() {
        return this.jAnterior;
    }

    public void generarTaulell(int dificultat){
      Random rand = new Random();
      int r;
      if(dificultat == 1){
        r = rand.nextInt(2);
        switch(r){
          case 1:
                this.taulell.setTipusCasella("Q");
                this.taulell.setTipusAdj("C");
                this.taulell.setNumFiles(3);
                this.taulell.setNumColumnes(3);
                break;
          case 2:

                break;
        }
      }
      else if(dificultat == 2){

      }
      else if(dificultat == 3){

      }
      else if(dificultat == 4){

      }
      else if(dificultat == 5){

      }
      else System.out.println("Ep! La dificultat no esta entre 1 i 5");
      tractaGeneracio();
    }
    public void tractaGeneracio(){
      int nF = this.taulell.getNumFiles();
      int nC = this.taulell.getNumColumnes();


    }
    public void omplirCasella(int i, int j, String valor) {
        ArrayList <ArrayList <Casella> > matriuOriginal = new ArrayList<ArrayList<Casella>> (0);
        boolean valorEsEnter = true;
        try {
            Integer.parseInt(valor);
        }
        catch(NumberFormatException e){
            System.out.println("El valor introduït no és un número");
            valorEsEnter = false;
        }
        if(valorEsEnter) {
            int x = Integer.parseInt(valor);
            if(x <= 1){
                System.out.println("El valor introduït ha de ser major a 1");
            }
            else {
                String valorCasellaOriginal = taulell.getTaulellOriginal().get(i).get(j);
                /*boolean valorCasellaOriginalEsEnter = true;
                try {
                    Integer.parseInt(valorCasellaOriginal);
                }
                catch(NumberFormatException e2){
                    valorCasellaOriginalEsEnter = false;
                }
                if(valorCasellaOriginalEsEnter) System.out.println("Aquesta posició estava preestablerta");
                */
                boolean esModificable = true;
                if(!valorCasellaOriginal.equals("?")){
                    esModificable = false;
                    System.out.println("Aquesta posicio no es modificable");
                }
                if(esModificable) {
    		        String vAux = taulell.getTaulell().get(i).get(j).getValor();
                    valorAnterior = new String(vAux);
                    iAnterior = i;
                    jAnterior = j;
                    taulell.modificarCasella(i,j,valor);
                }
            }
        }
    }
    public void resetT(){
        taulell.resetTaulell();
    }
    public void movimentEnrere() {
        if(this.valorAnterior == null) System.out.println("Encara no has fet cap moviment");
        else {
		taulell.modificarCasella(this.iAnterior, this.jAnterior, this.valorAnterior);
	    }
    }

    public void esborrarCasella(int i, int j){
        taulell.canviarPerInterrogant(i, j);
    }

    public enum dificultat {
        FACIL, INTERMIG, DIFICIL;
    }
}
