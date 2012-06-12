package com.daniel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ustawienia {
	
	//TODO: Dopisać wczytywanie temperatur startowych jak i z eksperymentu
	
	
	/**
	 * Plyn w rurze wewnętrznej
	 * indeks 't'
	 */
	public static double ro_t;		//gestosc plynu
	public static double c_t;		//ciepło właściwe płynu
	public static double T_t;		//temperatura plynu zew
	
	
	/**
	 * Plyn w rurze zewnętrznej
	 * indeks 's'
	 */
	public static double ro_s;		//gestosc plynu
	public static double c_s;		//ciepło właściwe płynu
	public static double T_s;		//temperatura plynu zew
	
	
	/**
	 * scianka rury wewnętrznej
	 * indeks 'p'
	 */
	public static double ro_p;		//gestosc rury wew.
	public static double c_p;		//ciepło właściwe rury wew.
	public static double T_p;		//temperatura rury wew.
	
	
	
	/**
	 * scianka rury zewnętrznej
	 * indeks 'p'
	 */
	public static double ro_z;		//gestosc rury zew.
	public static double c_z;		//ciepło właściwe rury zew.
	public static double T_z;		//temperatura rury zew
	
	
	/**
	 * Współczynniki przejmowania ciepła
	 */
	
	public static double alfa_s;	//wsp przejmowania ciepła na powierzchni zewnetrznej rury wewnetrznej
	public static double alfa_3;	//wsp przejmowania ciepła na powierzchni wewnetrznej rury zewnetrznej
	public static double alfa_t;	//wsp przejmowania ciepła na powierzchni wewnetrznej rury wewnetrznej
	
	/**
	 * Średnice
	 */
	public static double d1;	//Średnica wewnetrzna rury wewnętrznej
	public static double d2;	//Średnica zewnetrzna rury wewnętrznej
	public static double d3;	//Średnica wewnetrzna rury zewnętrznej
	public static double d4;	//Średnica zewnetrzna rury zewnętrznej
	
	/**
	 * Prędkości plynów
	 */
	public static double v_t;	//predkosc wewn
	public static double v_s;	//predkosc zewn
	
	/**
	 * rozmiary
	 */
	public static double dlugosc_wymiennika;
	
	
	
	
	
	/**
	 * Parametry metody
	 */
	public static int maksymalna_ilosc_iteracji;
	public static double krok;
	public static double wsp_ekspansji;
	public static double wsp_kontrakcji;
	public static int ilosc_zmiennych;
	
	
	/**
	 * Wyniki z Eksperymentu
	 */
	public static double wynik_T_t;		//temperatura plynu zew
	public static double wynik_T_s;		//temperatura plynu zew
	public static double wynik_T_p;		//temperatura plynu zew
	public static double wynik_T_z;		//temperatura plynu zew
	
	
	
	
	
	
	public static void wczytajDane(){
		try(BufferedReader br = new BufferedReader(new FileReader("ustawienia.dat"))){
			 br.readLine();
			 ro_t = Double.parseDouble(br.readLine());
			 br.readLine();
			 c_t = Double.parseDouble(br.readLine());
			 br.readLine();
			 ro_s = Double.parseDouble(br.readLine());
			 br.readLine();
			 c_s = Double.parseDouble(br.readLine());
			 br.readLine();
			 ro_p = Double.parseDouble(br.readLine());
			 br.readLine();
			 c_p = Double.parseDouble(br.readLine());
			 br.readLine();
			 ro_z = Double.parseDouble(br.readLine());
			 br.readLine();
			 c_z = Double.parseDouble(br.readLine());
			 br.readLine();
			 d1 = Double.parseDouble(br.readLine());
			 br.readLine();
			 d2 = Double.parseDouble(br.readLine());
			 br.readLine();
			 d3 = Double.parseDouble(br.readLine());
			 br.readLine();
			 d4 = Double.parseDouble(br.readLine());
			 br.readLine();
			 v_t = Double.parseDouble(br.readLine());
			 br.readLine();
			 v_s = Double.parseDouble(br.readLine());
			 br.readLine();
			 dlugosc_wymiennika = Double.parseDouble(br.readLine());
			 br.readLine();
			 br.readLine();
			 ilosc_zmiennych = Integer.parseInt(br.readLine());
			 br.readLine();
			 wsp_kontrakcji = Double.parseDouble(br.readLine());
			 br.readLine();
			 wsp_ekspansji = Double.parseDouble(br.readLine());
			 br.readLine();
			 krok = Double.parseDouble(br.readLine());
			 br.readLine();
			 maksymalna_ilosc_iteracji = Integer.parseInt(br.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
