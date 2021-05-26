package Test.ReadandWritexml;

import java.io.File;

public class ReadAllData {
	
	private static String[] listpath;
	
	public static String[] readAllFile(String filepath) {
		File file= new File(filepath);
		
		listpath=file.list();
		return listpath;
	}
	
}
