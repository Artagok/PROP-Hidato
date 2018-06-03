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
        r = rand.nextInt(2)%2;
        switch(r){
          case 0:
                this.taulell.setTipusCasella("Q");
                this.taulell.setTipusAdj("C");
                this.taulell.setNumFiles(3);
                this.taulell.setNumColumnes(3);
                break;
          case 1:
                this.taulell.setTipusCasella("Q");
                this.taulell.setTipusAdj("C");
                this.taulell.setNumFiles(3);
                this.taulell.setNumColumnes(3);

                break;
          default: System.out.println("el random no va be");
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
      System.out.println("ARRIBA1");
      tractaGeneracio();
    }
    public void tractaGeneracio(){
      int nF = this.taulell.getNumFiles();
      int nC = this.taulell.getNumColumnes();
      crearTotAmbInt(nF, nC);
      this.taulell.quinaAdj();
      //this.taulell.imprimirTaulell();
      determinarPrincipi(nF, nC);


    }
    public void crearTotAmbInt(int nF, int nC){
      ArrayList<ArrayList<Casella>> t = new ArrayList<ArrayList<Casella>> (0);
      for(int i = 0; i < nF; ++i){
        ArrayList<Casella> filaT = new ArrayList<Casella> (0);
        for(int j = 0; j < nC; ++j){
          Casella cAux = new Casella();
          cAux.omplirValor("?");
          cAux.setPosicioI(i);
          cAux.setPosicioJ(j);
          filaT.add(cAux);
        }
        t.add(filaT);
      }
      this.taulell.setTaulell(t);
      this.taulell.imprimirTaulell();
    }
    public void determinarPrincipi(int nF, int nC){
      boolean fixat = false;
      Random r = new Random();

      while(!fixat){
      //  System.out.println(fixat);
        int i = r.nextInt(nF)%nF;
        //System.out.println(i);                                           //ERROR AL RANDOM
        int j = r.nextInt(nC)%nC;
        //System.out.println(j);
        ArrayList<ArrayList<Casella>> tau = new ArrayList<ArrayList<Casella>> (0);

        tau = this.taulell.getTaulell();
        if(tau.get(i).get(j).equals("?")){

        tau.get(i).get(j).omplirValor("1");
        this.taulell.setIinicial(i);
        this.taulell.setJInicial(j);
        fixat = this.taulell.resoldreHidato(this.taulell.getTaulell().get(i).get(j));
        }
      }
      System.out.println("acaba el while");
      this.taulell.quinaAdj();
      this.taulell.llegirTaulellOriginal();



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
