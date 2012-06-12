/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.daniel;

/**
 *
 * @author Daniel
 */
public class MojeWektory {

    
    public static double pomnozWektory(double[] w1, double[] w2){
        double iloczyn=0.0;
        for (int i = 0; i < w1.length; i++) {
            iloczyn += w1[i]*w2[i];
        }
        return iloczyn;
    }



    public static double[] dodajWektory(double[] w1, double[] w2){
        double[] suma = new double[w1.length];
        for (int i = 0; i < w1.length; i++) {
            suma[i] = w1[i] + w2[i];
        }
        return suma;
    }

    public static double[] odejmijWektory(double[] w1, double[] w2){
        double[] roznica = new double[w1.length];
        for (int i = 0; i < w1.length; i++) {
            roznica[i] = w1[i] - w2[i];
        }
        return roznica;
    }

    public static double getNormaWektora(double[] w){
        double suma = 0;
        for (int i = 0; i < w.length; i++) {
            suma += w[i]*w[i];
        }
        return Math.sqrt(suma);
    }

    public static double odlegloscPunktow(double[] w1, double[] w2){
        double suma = 0.0;
        for (int i = 0; i < w1.length; i++) {
            suma += (w1[i]-w2[i])*(w1[i]-w2[i]);
        }
        suma = Math.sqrt(suma);
        return suma;
    }

    public static double[] podzielWektorPrzezSkalar(double skalar, double[] w){
        double[] temp = new double[w.length];
        for (int i = 0; i < w.length; i++) {
            temp[i] = w[i]/skalar;
        }
        return temp;
    }

    public static double[] pomnozWektorPrzezSkalar(double skalar, double[] w){
        double[] temp = new double[w.length];
        for (int i = 0; i < w.length; i++) {
            temp[i] = w[i]*skalar;
        }
        return temp;
    }

}
