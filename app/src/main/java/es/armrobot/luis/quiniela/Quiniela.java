package es.armrobot.luis.quiniela;

/**
 * Clase para la generacion de quinielas aleatorias
 */

public class Quiniela {

    //Atributos - probabilidades (1 a 14)
    private double p1;
    private double px;
    private double p2;



    //Atributos - probabilidades para el pleno al quince
    private double pq0;
    private double pq1;
    private double pq2;
    private double pqM;

    /**
     * La suma de p1 y px debe ser menor que 1. En caso de que sea mayor se pone p1 a 0.4 y px 0.2
     * @param p1 probabilidad de que salga un 1
     * @param px probabilidad de que salga un empate
     */
    public Quiniela(double p1, double px, double pq0, double pq1, double pq2){
        setProbabilidades(p1, px);
        setProbabilidadesPleno(pq0, pq1, pq2);
    }

    /**
     * Método que genera una quiniela aleatoria teniendo en cuenta las probabilidades determinadas
     * en los atributos
     * @return String que contiene el resultado de la quiniela
     */
    public String dameQuiniela(){
        String res="";
        double a;
        for(int i=0; i<14; i++) {
            a = Math.random();
            if (a < p1)
                res = res+"1\n";
            else if (a < p1 + px)
                res = res+"X\n";
            else
                res = res+"2\n";
        }
        for(int i=0; i<2; i++) {
            a = Math.random();
            if (a < pq0)
                res = res + "0\n";
            else if (a < pq0 + pq1)
                res = res + "1\n";
            else if (a < pq0 + pq1 + pq2)
                res = res + "2\n";
            else
                res = res + "M\n";
        }
        return res;
    }// dameQuiniela


    /**
     * Metodo para obtener la probabliddad de que el partido sea 1
     * @return probabilidad p1
     */
    public double getP1(){
        return p1;
    }// getP1

    /**
     * Metodo para obtener la probabliddad de que el partido sea 2
     * @return probabilidad p2
     */
    public double getP2(){
        return p2;
    }// getP2

    /**
     * Metodo para obtener la probabliddad de que el partido sea x
     * @return probabilidad px
     */
    public double getPx(){
        return px;
    }// getPx

    /**
     * Metodo para obtener la probabliddad del pleno al 15 para 0 goles
     * @return probabilidad pq0
     */
    public double getPq0() {
        return pq0;
    }

    /**
     * Metodo para obtener la probabliddad del pleno al 15 para 1 gol
     * @return probabilidad pq1
     */
    public double getPq1() {
        return pq1;
    }

    /**
     * Metodo para obtener la probabliddad del pleno al 15 para 2 goles
     * @return probabilidad pq2
     */
    public double getPq2() {
        return pq2;
    }

    /**
     * Metodo para obtener la probabliddad del pleno al 15 para m goles
     * @return probabilidad pqM
     */
    public double getPqM() {
        return pqM;
    }


    /**
     * Establece las probabilidades para los partidos del 1 al 14
     * La suma de p1 y px debe ser menor que 1. En caso de que sea mayor se pone p1 a 0.4 y px 0.2
     * La probabilidad de que gane el equipo visitante se calcula automáticamente con la siguiente
     * fórmula 1-p1-px.
     * @param p1 Probabilidad para que gane el local
     * @param px Probabilidad para que el empate
     */
    public void setProbabilidades(double p1, double px){
        if(p1+px>1){
            this.p1 = 0.4;
            this.px = 0.2;
            this.p2 = 1 - p1 - px;
        }else {
            this.p1 = p1;
            this.px = px;
            this.p2 = 1 - p1 - px;
        }//if
    }// setProbabilidades

    public void setProbabilidadesPleno(double pq0, double pq1, double pq2){
        if(pq0+pq1+pq2>1){
            this.pq0 = 0.2;
            this.pq1 = 0.3;
            this.pq2 = 0.2;
            this.pqM = 1-this.pq0-this.pq1-this.pq2;
        }else{
            this.pq0 = pq0;
            this.pq1 = pq1;
            this.pq2 = pq2;
            this.pqM = 1-pq0-pq1-pq2;
        }// if
    }// setProbabilidadesPleno

}// Quiniela


