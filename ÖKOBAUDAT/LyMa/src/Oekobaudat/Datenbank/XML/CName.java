package Oekobaudat.Datenbank.XML;

import java.util.LinkedHashMap;


public class CName {
	 public static LinkedHashMap<String,String> unit= new LinkedHashMap<String,String>();
	 public static LinkedHashMap<String,String> methode= new LinkedHashMap<String,String>();
	 
	 public static LinkedHashMap<String,String> unitchange(){
		unit.put("Länge","m"); 
		unit.put("Fläche","m2"); 
		unit.put("Stück","Items"); 
		unit.put("Number of pieces","Items"); 
		unit.put("Mass","kg"); 
		unit.put("Masse","kg"); 
		unit.put("Gewicht","kg"); 
		unit.put("Length","m"); 
		unit.put("Energy (net calorific value)","MJ"); 
		unit.put("Area","m2"); 
		unit.put("Volumen","m3"); 
		unit.put("Volume","m3"); 
		unit.put("Time","a"); 
		unit.put("Quadratmeter","m2"); 
		unit.put("kgkm","kgkm"); 
		unit.put("Piece","Items"); 
		unit.put("Referenzfenster 1,23m x 1,48m, Rahmenanteil zwischen 25% und 35%","Items"); 
		unit.put("Kilogramm","kg"); 
		unit.put("Kilogram","kg"); 
		unit.put("Cubicmeter","m3"); 
		unit.put("Stück pro Produkt","Items"); 
		 
		return unit;
		 
	 }
	 
	 public static LinkedHashMap<String,String> methodechange(){
		 methode.put("Potenzial für den abiotischen Abbau nicht fossiler Ressourcen (ADPE)", "Verknappung von abiotischen Ressourcen(Elemente) "); 
		 methode.put("Versauerungspotenzial von Boden und Wasser (AP)", "Versauerung"); 
		 methode.put("Eutrophierungspotenzial (EP)", "Eutrophierung"); 
		 methode.put("Globales Erwärmungspotenzial (GWP)", "Klimawandel"); 
		 methode.put("Potenzial für den abiotischen Abbau fossiler Brennstoffe (ADPF)", "Verknappung von fossiler Ressourcen"); 
		 methode.put("Abbau Potential der stratosphärischen Ozonschicht (ODP)", "Abbau der stratosphärischen Ozonschicht"); 
		 methode.put("Bildungspotential für troposphärisches Ozon (POCP)", "Bildung von troposphärischen Ozon"); 
		 methode.put("Abbaupotenzial der stratosphärischen Ozonschicht (ODP)", "Abbau der stratosphärischen Ozonschicht");
		 methode.put("Versauerungspotential (AP)", "Versauerung");
		 methode.put("Eutrophierungspotential (EP)", "Eutrophierung ");
		 methode.put("Bildungspotenzial für troposphärisches Ozon (POCP)", "Bildung von troposphärischen Ozon");
		 methode.put("Global warming potential (GWP)", "Klimawandel");
		 methode.put("Depletion potential of the stratospheric ozone layer (ODP)", "Abbau der stratosphärischen Ozonschicht ");
		 methode.put("Acidification potential of land and water (AP)", "Versauerung");
		 methode.put("Eutrophication potential (EP)", "Eutrophierung ");
		 methode.put("Formation potential of tropospheric ozone photochemical oxidants (POCP)", "Bildung von troposphärischen Ozon");
		 methode.put("Abiotic depletion potential for non fossil resources (ADPE)", "Verknappung von abiotischen Ressourcen(Elemente) ");
		 methode.put("Abiotic depletion potential for fossil resources (ADPF)", "Verknappung von fossiler Ressourcen");
		 methode.put("Versauerungspotenzial (AP)", "Versauerung");
		 
		 
		 
		 
		 
		return methode;
		 
	 }
     

}
