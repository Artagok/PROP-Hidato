package Domini;
/*** ___PROVA PEL PULL blablalbalblalbla ***/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Partida {

    private Jugador jugador;
    private Taulell taulell;
    private int iAnterior;
    private int jAnterior;
    private String valorAnterior;
    private Boolean startTimer;
    private int time;
    private Timer timer = new Timer();


    public Partida () {
        this.jugador = null;
        this.taulell = null;
        this.valorAnterior = null;
        this.startTimer = new Boolean(true);
        this.time = 0;
    }

    public Partida(Jugador j, Taulell t) {
        this.jugador = j;
        this.taulell = t;
        this.valorAnterior = null;
        this.startTimer = new Boolean(true);
        this.time = 0;
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

    public void stopTimer() {
        timer.cancel();
    }
    public void definiriHidato(int dificultat, String tC, String tA){
        Random rand = new Random();
        int r;
        int prob = 20;
        this.taulell.setTipusCasella(tC);
        this.taulell.setTipusAdj(tA);
        if(dificultat == 1){
          //prob = 20;
          r = rand.nextInt(2)%2;
          if(tC.equals("T")){
              switch(r){
                case 0:
                    this.taulell.setNumFiles(3);
                    this.taulell.setNumColumnes(3);
                    System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 3x3");
                    break;
                case 1:
                    this.taulell.setNumFiles(4);
                    this.taulell.setNumColumnes(4);
                    System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 4x4");
                    break;
                default: System.out.println("el random no va be");
              }

          }
          else {
              switch(r){
                case 0:
                      this.taulell.setNumFiles(6);
                      this.taulell.setNumColumnes(3);
                      System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 6x3");
                      break;
                case 1:
                      this.taulell.setNumFiles(4);
                      this.taulell.setNumColumnes(5);
                      System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 4x5");
                      break;
                default: System.out.println("el random no va be");
              }
          }

        }
        else if(dificultat == 2){
          //prob = 20;
          r = rand.nextInt(2)%2;
          if(tC.equals("T")){
              switch(r){
                case 0:
                    this.taulell.setNumFiles(4);
                    this.taulell.setNumColumnes(5);
                    System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 4x5");
                    break;
                case 1:
                    this.taulell.setNumFiles(5);
                    this.taulell.setNumColumnes(5);
                    System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 5x5");
                    break;
                default: System.out.println("el random no va be");
              }

          }
          else{
              switch(r){
                case 0:
                      this.taulell.setNumFiles(6);
                      this.taulell.setNumColumnes(3);
                      System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 6x3");
                      break;
                case 1:
                      this.taulell.setNumFiles(4);
                      this.taulell.setNumColumnes(5);
                      System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 4x5");
                      break;
                default: System.out.println("el random no va be");
              }
          }

        }
        else if(dificultat == 3){
          r = rand.nextInt(2)%2;
          if(tC.equals("T")){
              switch(r){
                case 0:
                    this.taulell.setNumFiles(5);
                    this.taulell.setNumColumnes(6);
                    System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 5x6");
                    break;
                case 1:
                    this.taulell.setNumFiles(6);
                    this.taulell.setNumColumnes(6);
                    System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 6x6");
                    break;
                default: System.out.println("el random no va be");
              }

          }
          else{
              switch(r){
                case 0:
                      this.taulell.setNumFiles(6);
                      this.taulell.setNumColumnes(6);
                      System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 6x6");
                      break;
                case 1:
                      this.taulell.setNumFiles(5);
                      this.taulell.setNumColumnes(5);
                      System.out.println("Aquest Hidato es de tipus" + tC + ", amb adjacencia per " + tA + ", i 5x5");
                      break;
                default: System.out.println("el random no va be");
              }
          }
        }
        else System.out.println("Ep! La dificultat no esta entre 1 i 3");
        tractaGeneracio();
        ArrayList<ArrayList<Casella>> tau = new ArrayList<ArrayList<Casella>> (0);
        tau = this.taulell.getTaulell();
        //this.taulell.imprimirTaulell();
        for(int i = 0; i < tau.size(); ++i){
          for(int j = 0; j < tau.get(0).size();++j){
              r = rand.nextInt(100)%100;
              //System.out.println(r);
              String comprova = tau.get(i).get(j).getValor();
              if((r > prob-1) && !comprova.equals("1") && !comprova.equals("#") && !comprova.equals("*")) {
                  tau.get(i).get(j).omplirValor("?");
              }
          }
        }
        this.taulell.imprimirTaulell();
      }

    public void generarHidato(int dificultat){
      Random rand = new Random();
      int r;
      int prob = 20;
      if(dificultat == 1){
        //prob = 20;
        r = rand.nextInt(2)%2;
        switch(r){
          case 0:
                this.taulell.setTipusCasella("Q");
                this.taulell.setTipusAdj("C");
                this.taulell.setNumFiles(3);
                this.taulell.setNumColumnes(3);
                System.out.println("Aquest Hidato es de tipus Quadrat, amb adjacencia per Costat, i 3x3");
                break;
          case 1:
                this.taulell.setTipusCasella("T");
                this.taulell.setTipusAdj("CA");
                this.taulell.setNumFiles(3);
                this.taulell.setNumColumnes(3);
                System.out.println("Aquest Hidato es de tipus Triangle, amb adjacencia per CostatVertex, i 3x3");
                break;
          default: System.out.println("el random no va be");
        }
      }
      else if(dificultat == 2){
        //prob = 20;
        r = rand.nextInt(2)%2;
        switch(r){
          case 0:
                this.taulell.setTipusCasella("T");
                this.taulell.setTipusAdj("CA");
                this.taulell.setNumFiles(6);
                this.taulell.setNumColumnes(6);
                System.out.println("Aquest Hidato es de tipus Triangular, amb adjacencia per CostatVertex, i 6x6");
                break;
          case 1:
                this.taulell.setTipusCasella("H");
                this.taulell.setTipusAdj("CA");
                this.taulell.setNumFiles(4);
                this.taulell.setNumColumnes(4);
                System.out.println("Aquest Hidato es de tipus Hexagonal, amb adjacencia per CostatVertex, i 4x4");
                break;
          default: System.out.println("el random no va be");
        }

      }
      else if(dificultat == 3){
        r = rand.nextInt(2)%2;
        switch(r){
          case 0:
                this.taulell.setTipusCasella("T");
                this.taulell.setTipusAdj("C");
                this.taulell.setNumFiles(6);
                this.taulell.setNumColumnes(6);
                System.out.println("Aquest Hidato es de tipus T, amb adjacencia per CostatVertex, i 6x6");
                break;
          case 1:
                this.taulell.setTipusCasella("Q");
                this.taulell.setTipusAdj("C");
                this.taulell.setNumFiles(10);
                this.taulell.setNumColumnes(15);
                System.out.println("Aquest Hidato es de tipus Q, amb adjacencia H, i 10x15");
                break;
          default: System.out.println("el random no va be");
        }

      }

      else System.out.println("Ep! La dificultat no esta entre 1 i 3");
      tractaGeneracio();
      ArrayList<ArrayList<Casella>> tau = new ArrayList<ArrayList<Casella>> (0);
      tau = this.taulell.getTaulell();
      //this.taulell.imprimirTaulell();
      for(int i = 0; i < tau.size(); ++i){
        for(int j = 0; j < tau.get(0).size();++j){
            r = rand.nextInt(100)%100;
            //System.out.println(r);
            if((r > prob-1) && !(tau.get(i).get(j).getValor().equals("1")) ){
                tau.get(i).get(j).omplirValor("?");
            }
        }
      }
      this.taulell.imprimirTaulell();
    }
    public void tractaGeneracio(){
      int nF = this.taulell.getNumFiles();
      int nC = this.taulell.getNumColumnes();
      crearTotAmbInt(nF, nC);
      if(this.taulell.getTipusCasella().equals("T")){
          if(this.taulell.getTipusAdjacencia().equals("C")){

              if(nF%2 == 1 && nC%2 == 1){
                  this.taulell.modificarCasella(nF-1, 0, "#");
                  this.taulell.modificarCasella(nF-1, nC-1, "#");
              }
              else if(nF%2 ==1 && nC%2 == 0){
                  this.taulell.modificarCasella(0, nC-1, "#");
                  this.taulell.modificarCasella(nF-1, 0, "#");
              }
              else if(nF%2 == 0 && nC%2 == 0){
                  this.taulell.modificarCasella(0, nC-1, "#");
                  this.taulell.modificarCasella(nF-1, nC-1, "#");
              }
          }
      }
      this.taulell.quinaAdj();
    /*  ArrayList<ArrayList<Casella>> aux = new ArrayList<ArrayList<Casella>>(0);
      aux = this.taulell.getTaulell();
      for(int i = 0; i < nF;++i){
        for(int j = 0; j < nC; ++j){
          ArrayList<Casella> adjC = new ArrayList<Casella>(0);
          adjC = aux.get(i).get(j).getAdjacencies();
          System.out.println(i+ " , " + j);
          for(int k = 0; k < adjC.size(); ++k){
            System.out.println(adjC.get(k).getValor());
            System.out.print(adjC.get(k).getPosicioI() + " ");
            System.out.print(adjC.get(k).getPosicioJ());
            System.out.println();
          }
        }
      }*/
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

    }
    public void determinarPrincipi(int nF, int nC){
      boolean fixat = false;
      Random r = new Random();

      while(!fixat){
        //System.out.println(fixat);
        int iI = r.nextInt(nF*nF)%nF;
        //System.out.println(i);                                           //ERROR AL RANDOM
        int jI = r.nextInt(nC*nC)%nC;
        if(nF >= 6 && nC >= 6){
            switch(r.nextInt(100)%2){
                case 0:
                    jI = 0;
                    break;
                case 1:
                    jI = nC - 1;
                    break;
                //default:
                //    jI = 0;
            }
            switch(r.nextInt(100)%2){
                case 0:
                    iI = 0;
                    break;
                case 1:
                    iI = nF - 1;
                    break;
                //default:
                //    iI = 0;
            }


        }
        //System.out.println(j);
        ArrayList<ArrayList<Casella>> tau = new ArrayList<ArrayList<Casella>> (0);
        //System.out.println("abans de tau");
        //this.taulell.imprimirTaulell();
        tau = this.taulell.getTaulell();
        if(tau.get(iI).get(jI).getValor().equals("?")){
        tau.get(iI).get(jI).omplirValor("1");
        this.taulell.setIinicial(iI);
        this.taulell.setJInicial(jI);
        this.taulell.llegirTaulellOriginal();
        fixat = this.taulell.resoldreHidato(tau.get(iI).get(jI));
        if(!fixat)tau.get(iI).get(jI).omplirValor("?");
        }


      }
      //this.taulell.imprimirTaulell();

    }
    public void omplirCasella(int i, int j, String valor) {

        if (startTimer) {
            startTimer = false;
            timer.scheduleAtFixedRate(new TimerTask(){

                @Override
                public void run() {
                    if (taulell.checkIfValid()) {
                        timer.cancel();
                        System.out.println("Temps transcorregut: " + time + "s");
                    }
                    else
                        ++time;
                }
            }, 0, 1000);
        }

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