/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.daniel;

import java.util.Arrays;

/**
 *
 * @author student
 */
public class Kierunek {

    private double[] wektor;
    private double dlugoscSukcesow = 0.0;
    private int licznikPorazek = 0;

    private double krok;

    public Kierunek(int pozycja){
        wektor = new double[Ustawienia.ilosc_zmiennych];
        Arrays.fill(wektor, 0.0);
        wektor[pozycja] = 1.0;
        this.krok = Ustawienia.krok;
    }

    public void sukces(double droga){
        dlugoscSukcesow += droga;
        krok *= Ustawienia.wsp_ekspansji;
    }

    public void porazka(){
        licznikPorazek++;
        krok *= -Ustawienia.wsp_kontrakcji;
    }


    public double getDlugoscSukcesow() {
        return dlugoscSukcesow;
    }

    public int getLicznikPorazek() {
        return licznikPorazek;
    }

    public void setWektor(double[] w) {
        for (int i = 0; i < this.wektor.length; i++) {
            this.wektor[i] = w[i];
        }
        licznikPorazek = 0;
        dlugoscSukcesow = 0.0;
        krok = 1.0;
    }

    public double[] getWektor() {
        return wektor;
    }
    
    public double getKrok(){
        return krok;
    }

}
