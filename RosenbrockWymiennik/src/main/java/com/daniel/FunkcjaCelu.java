package com.daniel;


/**
 *
 * @author daniel
 */
public class FunkcjaCelu {
	
	
	private static double[] temperaturyEksperyment = new double[]{Ustawienia.wynik_T_t, Ustawienia.wynik_T_p , Ustawienia.wynik_T_s ,Ustawienia.wynik_T_z};


    public static double getValue(double[] point){
    	Obliczenia o = new Obliczenia(point);
    	double[] temperatury = o.start();
    	double blad = 0.0;
    	for(int i=0;i<4;i++){
    		blad += (temperatury[i]-temperaturyEksperyment[i])*(temperatury[i]-temperaturyEksperyment[i]);
    	}
    	return blad;
    }

    public static class Obliczenia{
		
    	
    	private double alfa_s;	//wsp przejmowania ciepła na powierzchni zewnetrznej rury wewnetrznej
    	private double alfa_3;	//wsp przejmowania ciepła na powierzchni wewnetrznej rury zewnetrznej
    	private double alfa_t;	//wsp przejmowania ciepła na powierzchni wewnetrznej rury wewnetrznej
    	
    	
		private Sekcja[] sekcje;
		private double czas;
		private double wsp_Beta = 0.1;
		
		
		
		private Sekcja sekcjaINIT;
		private double dlugosc_sekcji;
		private int ilosc_sekcji = 20;
		
		
		/**
		 * Konstruktor nowego watku liczacego
		 */
		public Obliczenia(double[] wspolczynniki){
			
			alfa_t = wspolczynniki[0];
			alfa_s = wspolczynniki[1];
			alfa_3 = wspolczynniki[2];
			
			
			
			sekcje = new Sekcja[ilosc_sekcji];
			for(int i=0;i<ilosc_sekcji;i++){
				sekcje[i] = new Sekcja(Ustawienia.T_t, Ustawienia.T_s, Ustawienia.T_p, Ustawienia.T_z);
			}
			
			
			dlugosc_sekcji = Ustawienia.dlugosc_wymiennika / (double) ilosc_sekcji;	//[m]
			double czas1 = dlugosc_sekcji/Ustawienia.v_t;	//[s]
			double czas2 = dlugosc_sekcji/Ustawienia.v_s;	//[s]
			czas = Math.abs(czas1 < czas2 ? czas1 : czas2);
			czas /= 3;
		}

		public double[] start() {
			double suma = 0.0;
			do{
				//obliczenie delty temperatur
				for(int i=0;i<sekcje.length;i++){
					Sekcja s = sekcje[i];
					Sekcja s2 = (i==0 ? sekcjaINIT : sekcje[i-1]);
					
					double temp1;
					double temp2;
					double temp3;
					
					temp1 = 4*alfa_t*(s.T_p-s.T_t)/(Ustawienia.ro_t*Ustawienia.c_t*Ustawienia.d1);
					temp2 = Ustawienia.v_t*(s.T_t-s2.T_t)/dlugosc_sekcji;
					s.dT_t = (temp1-temp2) * czas;
					
					
					temp1 = 4*Ustawienia.d1*alfa_t*(s.T_t-s.T_p)/(Ustawienia.ro_p*Ustawienia.c_p*(Ustawienia.d2*Ustawienia.d2-Ustawienia.d1*Ustawienia.d1));
					temp2 = 4*Ustawienia.d2*alfa_s*(s.T_s-s.T_p)/(Ustawienia.ro_p*Ustawienia.c_p*(Ustawienia.d2*Ustawienia.d2-Ustawienia.d1*Ustawienia.d1));
					s.dT_p = (temp1 + temp2) * czas;
					
					temp1 = 4*Ustawienia.d2*alfa_s*(s.T_p-s.T_s)/(Ustawienia.ro_s*Ustawienia.c_s*(Ustawienia.d3*Ustawienia.d3-Ustawienia.d2*Ustawienia.d2));
					temp2 = 4*Ustawienia.d3*alfa_3*(s.T_z-s.T_s)/(Ustawienia.ro_s*Ustawienia.c_s*(Ustawienia.d3*Ustawienia.d3-Ustawienia.d2*Ustawienia.d2));
					temp3 = Ustawienia.v_s*(s.T_s-s2.T_s)/dlugosc_sekcji;
					s.dT_s = (temp1+temp2-temp3) * czas;
					
					
					temp1 = 4*Ustawienia.d3*alfa_3*(s.T_s-s.T_z)/(Ustawienia.ro_z*Ustawienia.c_z*(Ustawienia.d4*Ustawienia.d4-Ustawienia.d3*Ustawienia.d3));
					s.dT_z = (temp1) * czas;
					
				}
				
				
				
				//zaktualizowanie temperatur w sekcjach
				for(Sekcja s : sekcje){
					s.T_t += s.dT_t*wsp_Beta;
					s.T_p += s.dT_p*wsp_Beta;
					s.T_s += s.dT_s*wsp_Beta;
					s.T_z += s.dT_z*wsp_Beta;
					
					suma += s.dT_t*wsp_Beta;
					suma += s.dT_p*wsp_Beta;
					suma += s.dT_s*wsp_Beta;
					suma += s.dT_z*wsp_Beta;
				}
				
				
				
				
				
				/*int a = JOptionPane.showConfirmDialog(MainWindow.this, "Kontynuowac?", "hhh", JOptionPane.YES_NO_OPTION);
				if(a == JOptionPane.NO_OPTION) break;*/
				
			}while(suma>0.02);
			
			Sekcja s = sekcje[sekcje.length-1];	//ostatnia sejcja
			return new double[]{s.T_t,s.T_p,s.T_s,s.T_z};
		}

	}


}
