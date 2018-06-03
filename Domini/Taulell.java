package Domini;

import java.util.ArrayList;
import java.util.Scanner;

public class Taulell {

    //////////////////////////////////////////////////////////////////
    private int idTaulell;                                          //
                                                                    //
    private int iCasellaInicial;                                    //
    private int jCasellaInicial;                                    //
                                                                    //
    private int numFiles;                                           //
    private int numColumnes;                                        //
                                                                    //
    private String tipusCasella;                                    //
    private String tipusAdjacencia;                                 //
                                                                    //
    private ArrayList<ArrayList<Casella>> taulell;                  //
    private ArrayList<ArrayList<String>> taulellOriginal;           //
    //////////////////////////////////////////////////////////////////

    public Taulell(int id) {
        idTaulell = id;
        taulell = new ArrayList<ArrayList<Casella>> (0);
    }

    /* Getters */

    public int getIdTaulell() {
        return this.idTaulell;
    }

    /* Cuidado, hi ha aliasing pq es un tipus no primitiu */
    public ArrayList<ArrayList<Casella>> getTaulell() {
        return this.taulell;
    }

    public int getICasellaInicial () {
        return this.iCasellaInicial;
    }

    public int getJCasellaInicial () {
        return this.jCasellaInicial;
    }

    public int getNumFiles() {
        return this.numFiles;
    }

    public int getNumColumnes() {
        return this.numColumnes;
    }

    public String getTipusAdjacencia() {
        return new String(this.tipusAdjacencia);
    }

    public String getTipusCasella() {
        return new String(this.tipusCasella);
    }

    public Casella getCasellaByIndexes(int i, int j) {
        return this.taulell.get(i).get(j);
    }

    public ArrayList<ArrayList<String>> getTaulellOriginal() {
        return this.taulellOriginal;
    }
    public void setTipusCasella(String tC){
      this.tipusCasella = new String(tC);
    }
    public void setTipusAdj(String tA){
      this.tipusAdjacencia = new String(tA);
    }
    public void setNumFiles(int nF){
      this.numFiles = nF;
    }
    public void setNumColumnes(int nC){
      this.numColumnes = nC;
    }
    public void setIinicial(int iI){
      this.iCasellaInicial = iI;
    }
    public void setJInicial(int jI){
      this.jCasellaInicial = jI;
    }
    public void setTaulell(ArrayList<ArrayList<Casella>> t){
      this.taulell = t;

    }
    public boolean esValid() {
       int count = countCasellesNum();
       //System.out.println("countCasellesNum() = " + count);
       //System.out.println("(i,j) inicials = (" + this.iCasellaInicial + "," + this.jCasellaInicial + ")");
       return saltarCaselles(taulell.get(iCasellaInicial).get(jCasellaInicial), count);
    }

    private boolean saltarCaselles(Casella c, int count) {
        int valor = Integer.parseInt(c.getValor());
        ArrayList<Casella> adjacencies = c.getAdjacencies();
        boolean trobat = false;
        Casella cSeguent = new Casella();
        for(int i = 0; i < adjacencies.size() && !trobat; i++) {
            Casella cActual = adjacencies.get(i);
            if (esNumero(cActual.getValor()) && Integer.parseInt(cActual.getValor()) == (valor+1)) {
                cSeguent = adjacencies.get(i);
                trobat = true;
            }
        }

        if(count == 1 && countCasellesInterrogant() == 0)  { return true; }
        if(!trobat)     { /*System.out.println("return false, ja no continuem mirant");*/ return false; }
        else            { /*System.out.println("seguim mirant");*/ return saltarCaselles(cSeguent, (count-1)); }
    }

    public int countCasellesNum() {
        int contador = 0;
        for(int i = 0; i < taulell.size(); i++) {
            for(int j = 0; j < taulell.get(0).size(); j++) {
                String valor = taulell.get(i).get(j).getValor();
                 if (esNumero(valor)) contador++; //Ara ja no comptem les ?, només estrictament que sigui num
            }
        }
        return contador;
    }

    public void readTaulell() {

        Scanner reader = new Scanner(System.in);
        String line;
        String[] parsedLine;

        line = reader.nextLine();
        parsedLine = line.split(",");

        this.tipusCasella       = new String(parsedLine[0]);
        this.tipusAdjacencia    = new String(parsedLine[1]);
        this.numFiles           = Integer.parseInt(parsedLine[2]);
        this.numColumnes        = Integer.parseInt(parsedLine[3]);

        for (int i = 0; i < numFiles; ++i) {

            line        = new String(reader.nextLine());
            parsedLine  = line.split(",");

            ArrayList<Casella> vAux = new ArrayList<Casella> (0);

            for (int j = 0; j < numColumnes; ++j) {

                Casella cAux = new Casella();
                cAux.omplirValor(parsedLine[j]);
                cAux.setPosicioI(i);
                cAux.setPosicioJ(j);
                vAux.add(cAux);
            }

            this.taulell.add(vAux);
        }
        quinaAdj();
      }
      public void quinaAdj(){
        //System.out.println(this.tipusCasella);
        //System.out.println(this.tipusAdjacencia);
        //System.out.println(this.numFiles);
        //System.out.println(this.numColumnes);
        switch (this.tipusCasella) {

            case "Q":
                if (this.tipusAdjacencia.equals("C"))
                    determinarAdjacenciaQC();
                else
                    determinarAdjacenciaQCV();
                break;

            case "T":
                if (this.tipusAdjacencia.equals("C"))
                    determinarAdjacenciaTC();
                else
                    determinarAdjacenciaTCV();
                break;

            case "H":
                arreglarHexagon();          /* Cal arreglar la representacio de l'Hexagon */
                determinarAdjacenciaH();    /* Per Hexagons nomes pot ser per Costat (C) */
                break;

            default:
                System.out.println("Error al switch de Taulell.java");
                break;
        }

        llegirTaulellOriginal();
        determinarIJCasellaInicial();
        //reader.close();
    }

    public void imprimirTaulell() {
        for(int i = 0; i < numFiles; i++){
            for(int j = 0; j < numColumnes; j++){
                 System.out.print(taulell.get(i).get(j).getValor() + " ");
            }
            System.out.println(" ");
        }
    }

    private void determinarAdjacenciaQC(){

        for(int i = 0; i < numFiles; i++){
            for(int j = 0; j < numColumnes; j++){
                if(posValida(i+1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                if(posValida(i-1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                if(posValida(i, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                if(posValida(i, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
            }
        }
    }

    /* Compte, més endavant potser tenim problemes amb l'aliasing, estem afegint al vector d'ajacencies un punter a Casella (caldria fer new amb creadora per copia, etc per evitar-ho)*/

    private void determinarAdjacenciaQCV(){

        for(int i = 0; i < numFiles; i++){
            for(int j = 0; j < numColumnes; j++){
                if(posValida(i+1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                if(posValida(i-1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                if(posValida(i, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                if(posValida(i, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));

                if(posValida(i+1, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j+1));
                if(posValida(i-1, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j-1));
                if(posValida(i+1, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j-1));
                if(posValida(i-1, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j+1));
            }
        }
    }

    /* TRIANGLES */ //la primera fila té index 0
    /* Fila parella --> el primer triangle te base a baix, el següent [j+1] base a dalt, i es va repetint */
    /* Fila senar   --> el primer triangle te base a dalt, el següent [j+1] base a baix, i es va repetint */

    private void determinarAdjacenciaTC(){

        for(int i = 0; i < numFiles; i++){
            for(int j = 0; j < numColumnes; j++){
                if(i%2 == 0){                       //comenSa per triangle amb base abaix
                    if(j%2 == 0){                   //base abaix
                        if(posValida(i+1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                        if(posValida(i, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                        if(posValida(i, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));

                    }
                    else {                          //base adalt
                        if(posValida(i-1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                        if(posValida(i, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                        if(posValida(i, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
                    }
                }
                else {
                    if(j%2 != 0){                   //base abaix
                        if(posValida(i+1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                        if(posValida(i, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                        if(posValida(i, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));

                    }
                    else {                          //base adalt
                        if(posValida(i-1, j)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                        if(posValida(i, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                        if(posValida(i, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
                    }
                }
            }
        }
    }

    private void determinarAdjacenciaTCV(){

       for(int i = 0; i < numFiles; i++){
            for(int j = 0; j < numColumnes; j++){

                /* Base a baix */
                if ( (i%2 == 0 && j%2 == 0) || (i%2 != 0 && j%2 != 0) ) {

                    if(posValida(i-1, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j-1));
                    if(posValida(i-1, j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                    if(posValida(i-1, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j+1));

                    if(posValida(i, j-2))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-2));
                    if(posValida(i, j-1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
                    if(posValida(i, j+1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                    if(posValida(i, j+2))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+2));

                    if(posValida(i+1, j-2)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j-2));
                    if(posValida(i+1, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j-1));
                    if(posValida(i+1, j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                    if(posValida(i+1, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j+1));
                    if(posValida(i+1, j+2)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j+2));
                }

                /* Base a dalt */
                else {
                    if(posValida(i-1, j-2)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j-2));
                    if(posValida(i-1, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j-1));
                    if(posValida(i-1, j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                    if(posValida(i-1, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j+1));
                    if(posValida(i-1, j+2)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j+2));

                    if(posValida(i, j-2))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-2));
                    if(posValida(i, j-1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
                    if(posValida(i, j+1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                    if(posValida(i, j+2))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+2));

                    if(posValida(i+1, j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j-1));
                    if(posValida(i+1, j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                    if(posValida(i+1, j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j+1));
                }
            }
        }
    }

    private void determinarAdjacenciaH() {

        for (int i = 0; i < numFiles; i++) {
            for (int j = 0; j < numColumnes; j++) {

                if (j%2 == 0) {
                    if (posValida(i-1,j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                    if (posValida(i,j-1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
                    if (posValida(i,j+1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                    if (posValida(i+1,j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j-1));
                    if (posValida(i+1,j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                    if (posValida(i+1,j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j+1));
                }
                else {
                    if (posValida(i+1,j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i+1).get(j));
                    if (posValida(i,j-1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j-1));
                    if (posValida(i,j+1))   taulell.get(i).get(j).addAdjacencia(taulell.get(i).get(j+1));
                    if (posValida(i-1,j-1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j-1));
                    if (posValida(i-1,j))   taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j));
                    if (posValida(i-1,j+1)) taulell.get(i).get(j).addAdjacencia(taulell.get(i-1).get(j+1));
                }
            }
        }
    }

    private void arreglarHexagon() {

        ArrayList<ArrayList<Casella>> taulellNou = new ArrayList<ArrayList<Casella>> (0);

        for (int j = 0; j < numColumnes; ++j) {

            ArrayList<Casella> vAux = new ArrayList<Casella> (0);

            for (int i = (numFiles-1); i >= 0; --i) {

                Casella cAux = new Casella();
                cAux.omplirValor(this.taulell.get(i).get(j).getValor());
                vAux.add(cAux);
            }

            taulellNou.add(vAux);

        }

        /* numFiles <--> numColumnes (swap) */
        int aux = this.numFiles;
        this.numFiles = this.numColumnes;
        this.numColumnes = aux;

        this.taulell = new ArrayList<ArrayList<Casella>> (0);
        for (int i = 0; i < this.numFiles; ++i) {

            ArrayList<Casella> vAux = new ArrayList<Casella> (0);

            for (int j = 0; j < this.numColumnes; ++j) {

                Casella cAux = new Casella();
                cAux.omplirValor(taulellNou.get(i).get(j).getValor());
                cAux.setPosicioI(i);
                cAux.setPosicioJ(j);
                vAux.add(cAux);
            }

            this.taulell.add(vAux);
        }
    }

    /* Sempre trobara una casella inicial (una casella amb valor 1) */
    private void determinarIJCasellaInicial () {
        boolean trobat = false;
        for (int i = 0; i < this.numFiles && !trobat; ++i) {
            for (int j = 0; j < this.numColumnes && !trobat; ++j) {
                if (taulell.get(i).get(j).getValor().equals("1")) {
                    trobat = true;
                    this.iCasellaInicial = i;
                    this.jCasellaInicial = j;
                }
            }
        }
    }

    public boolean resoldreHidato(Casella cActual) {

        //imprimirTaulell();
        //System.out.println("numInterrogants = " + numInterrogants.intValue());
        //System.out.println();

        if (countCasellesInterrogant() == 0) return esValid();

        ArrayList<Casella> adjActual = cActual.getAdjacencies();
        String val = Integer.toString(Integer.parseInt(cActual.getValor()) + 1); /* valor Casella actual + 1 */

        int testNum = cActual.searchAdjacencia(val);
        if (testNum != -1) {
            return resoldreHidato(adjActual.get(testNum));
        }

        for (int i = 0; i < adjActual.size(); ++i) {

            int indexI = adjActual.get(i).getPosicioI();
            int indexJ = adjActual.get(i).getPosicioJ();

            if (adjActual.get(i).getValor().equals("?")) {

                adjActual.get(i).omplirValor(val);
                this.taulell.get(indexI).get(indexJ).omplirValor(val);

                if (resoldreHidato(this.taulell.get(indexI).get(indexJ))) {
                    return true;
                }

                else {
                    adjActual.get(i).omplirValor("?");
                    this.taulell.get(indexI).get(indexJ).omplirValor("?");
                }
            }

        }
        //System.out.println("Arribo al return false (al final) del backtracking");
        return false;
    }

    public int countCasellesInterrogant () {
        int contador = 0;
        for(int i = 0; i < numFiles; i++) {
            for(int j = 0; j < numColumnes; j++) {
                String valor = taulell.get(i).get(j).getValor();
                 if (valor.equals("?")) contador++;
            }
        }
        return contador;
    }

    private boolean posValida(int i, int j) {

        return ((i >= 0 && i < this.numFiles) && (j >= 0 && j < this.numColumnes));
    }

    private boolean esNumero (String s) {
        boolean numero = true;
        try {
		Integer.parseInt(s);
	}
        catch(NumberFormatException exc){
            numero = false;
        }
        return numero; //( !s.equals("_") && !s.equals("#") && !s.equals("*") && !s.equals("?") );
    }

    public void modificarCasella(int i, int j, String valor) {
        taulell.get(i).get(j).omplirValor(valor);
    }

    public void canviarPerInterrogant(int i, int j) {
        if(!taulellOriginal.get(i).get(j).equals("?"))  System.out.println("Aquesta posicio no es modificable");
        else taulell.get(i).get(j).omplirValor("?");
    }

    public void resetTaulell(){
        for(int i = 0; i < numFiles; i++){
            for(int j = 0; j < numColumnes; j++){
                String valor = new String(this.taulellOriginal.get(i).get(j));
                this.taulell.get(i).get(j).omplirValor(valor);
            }
        }
    }

    public void llegirTaulellOriginal() {

        ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>> (0);

        for(int i = 0; i < numFiles; ++i) {

            ArrayList<String> auxLinia = new ArrayList<String> (0);

            for (int j = 0; j < numColumnes; ++j) {

                auxLinia.add(this.taulell.get(i).get(j).getValor());
            }
            aux.add(auxLinia);
        }
        this.taulellOriginal = aux;
    }
}
