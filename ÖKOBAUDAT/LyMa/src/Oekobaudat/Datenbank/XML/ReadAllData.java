package Oekobaudat.Datenbank.XML;

import java.io.File;

public class ReadAllData {
	
	private static String[] listpath;
	
	public static String[] readAllFile(String filepath) {
		File file= new File(filepath);
		
		listpath=file.list();
		return listpath;
	}
	
}
