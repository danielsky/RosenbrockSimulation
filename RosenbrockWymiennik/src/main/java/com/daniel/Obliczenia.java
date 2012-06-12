/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author student
 */
public class Obliczenia implements Runnable {

	private MainWindow mw;
	
	//Aktualnie najlepsze rozwiązanie
    private double[] punkt;
    private double wynik;

    public Obliczenia(MainWindow mw) {
    	this.mw = mw;
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
        	listaKierunkow.add(new Kierunek(i));
        }
        punkt = new double[Ustawienia.ilosc_zmiennych];
        Arrays.fill(punkt, 0.0);

        wynik = FunkcjaCelu.getValue(punkt);
    }

    public void run() {
        MainWindow.addLog("Liczę...", Color.BLACK);
        try {
            for (int licznik = 0; licznik < Ustawienia.maksymalna_ilosc_iteracji; licznik++) {
                do {
                    for (Kierunek k : listaKierunkow) {
                    	
                        double[] przesuniecie = MojeWektory.pomnozWektorPrzezSkalar(k.getKrok(), k.getWektor());
                        //System.out.println(k.getKrok());
                        double[] tempPunkt = MojeWektory.dodajWektory(punkt, przesuniecie);
                        double tempWynik = FunkcjaCelu.getValue(tempPunkt);
                        if (wynik > tempWynik) {
                            k.sukces(MojeWektory.odlegloscPunktow(tempPunkt, punkt));
                            wynik = tempWynik;
                            punkt = tempPunkt;
                        } else {
                            k.porazka();
                        }
                    }
                } while (!czyWykonacObrotMacierzy());
                
                if(wynik < 1.0) break;

                //sekwencja obrocenia kierunkow

                MacierzObrotu.stworzMacierzD(getMacierz());
                MacierzObrotu.stworzMacierzLambda(getWektorSukcesow());
                MacierzObrotu.stworzMacierzQ();

                /*System.out.println("Macierz Lambda:");
                MacierzObrotu.wypiszMacierz(0);
                System.out.println("Macierz D:");
                MacierzObrotu.wypiszMacierz(1);
                System.out.println("Macierz Q:");
                MacierzObrotu.wypiszMacierz(2);*/

                MacierzObrotu.stworzMacierzV();
                System.out.println("Obrot macierzy nr " + licznik);
                for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
                    double[] temp = MacierzObrotu.getKolumnaMacierzyV(i);
                    listaKierunkow.get(i).setWektor(temp);
                    //System.out.println(Arrays.toString(temp));
                }
                System.out.println("===========================================================\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Obliczony punkt:");
        System.out.println(Arrays.toString(punkt));
        MainWindow.addLog("Koniec obliczeń", Color.BLACK);
    }

    private boolean czyWykonacObrotMacierzy() {
        for (Kierunek k : listaKierunkow) {
            if (k.getDlugoscSukcesow() == 0.0 && k.getLicznikPorazek() == 0) return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    //kierunki
    
    private ArrayList<Kierunek> listaKierunkow = new ArrayList<Kierunek>();
    
    public double[][] getMacierz(){
        double tab[][] = new double[Ustawienia.ilosc_zmiennych][Ustawienia.ilosc_zmiennych];
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
            double[] wektor = listaKierunkow.get(i).getWektor();
            for (int j = 0; j < Ustawienia.ilosc_zmiennych; j++) {
                tab[j][i] = wektor[j];
            }
        }
        return tab;
    }

    public double[] getWektorSukcesow(){
        double[] tabLambda = new double[Ustawienia.ilosc_zmiennych];
        for (int i = 0; i < Ustawienia.ilosc_zmiennych; i++) {
            tabLambda[i] = listaKierunkow.get(i).getDlugoscSukcesow();
        }
        return tabLambda;
    }
}
