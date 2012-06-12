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
public class MacierzObrotu {

    private static double[][] macierzQ;
    private static double[][] macierzLambda;
    private static double[][] macierzD;
    private static double[][] macierzV;


    public static void stworzMacierzLambda(double[] lambda){
        macierzLambda = new double[Ustawienia.ilosc_zmiennych][Ustawienia.ilosc_zmiennych];
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
            for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
                macierzLambda[i][j] = j<=i ? lambda[i] : 0.0;
            }
        }
    }

    

    public static void stworzMacierzD(double[][] macD){
        macierzD = macD;
    }

    public static void stworzMacierzQ(){
        macierzQ = new double[Ustawienia.ilosc_zmiennych][Ustawienia.ilosc_zmiennych];
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
            for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
                double suma = 0.0;
                for (int k = 0; k < Ustawienia.ilosc_zmiennych; k++) {
                    suma += macierzD[i][k]*macierzLambda[k][j];
                }
                macierzQ[i][j] = suma;
            }
        }
    }

    public static void stworzMacierzV(){
        macierzV = new double[Ustawienia.ilosc_zmiennych][Ustawienia.ilosc_zmiennych];
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
            double[] temp = stworzWektorV(i);
            double skalar = MojeWektory.getNormaWektora(temp);
            temp = MojeWektory.podzielWektorPrzezSkalar(skalar, temp);
            for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
                macierzV[j][i] = temp[j];
            }
        }
    }

    private static double[] stworzWektorV(int j){
        double[] kolumnaQ = getKolumnaMacierzyQ(j);
        double[] suma = new double[Ustawienia.ilosc_zmiennych];
        Arrays.fill(suma, 0.0);
        for (int k = 0; k < j; k++) {
            suma = MojeWektory.dodajWektory(suma, MojeWektory.pomnozWektorPrzezSkalar(MojeWektory.pomnozWektory(getKolumnaMacierzyQ(j), getKolumnaMacierzyD(k)), getKolumnaMacierzyD(k)));
        }
        return MojeWektory.odejmijWektory(kolumnaQ, suma);
    }

    private static double[] getKolumnaMacierzyD(int i){
        double[] temp = new double[Ustawienia.ilosc_zmiennych];
        for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
            temp[j] = macierzD[j][i];
        }
        return temp;
    }

    private static double[] getKolumnaMacierzyQ(int i){
        double[] temp = new double[Ustawienia.ilosc_zmiennych];
        for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
            temp[j] = macierzQ[j][i];
        }
        return temp;
    }

    public static double[] getKolumnaMacierzyV(int i){
        double[] temp = new double[Ustawienia.ilosc_zmiennych];
        for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
            temp[j] = macierzV[j][i];
        }
        return temp;
    }








    public static void wypiszMacierz(int m){
        //macierzLambda = new double[StaleProgramu.ILOSC_PRODUKTOW][StaleProgramu.ILOSC_PRODUKTOW];
        double[][] t = new double[1][1];
        switch(m){
            case 0:
                t = macierzLambda;
                break;
            case 1:
                t = macierzD;
                break;
            case 2:
                t = macierzQ;
                break;
        }
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
            for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
                System.out.print(t[i][j]+" ");
            }
            System.out.println();
        }
    }


}
