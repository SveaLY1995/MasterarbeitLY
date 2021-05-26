package Oekobaudat.Datenbank.XML;
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.OutputStreamWriter;
public class report {



	    public static void write(String a, String path){

	        String saveFile = "Reporttest.txt";
	        File file = new File(saveFile);
	        FileOutputStream fos = null;
	        OutputStreamWriter osw = null;

	        try {
	            if (!file.exists()) {
	                boolean hasFile = file.createNewFile();
	                if(hasFile){
	                   // System.out.println("file not exists, create new file");
	                }
	                fos = new FileOutputStream(file);
	            } else {
	               // System.out.println("file exists");
	                fos = new FileOutputStream(file, true);
	            }

	            osw = new OutputStreamWriter(fos, "utf-8");
	            osw.write(a+"dont exists!");
	            osw.write(path+"dont exists!");//写入内容
	            osw.write("\r\n");  //换行
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {   //关闭流
	            try {
	                if (osw != null) {
	                    osw.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (fos != null) {
	                    fos.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	 
	   }
	    
	    public static void writeUnit(String a ){

	        String saveFile = "Unit.txt";
	        File file = new File(saveFile);
	        FileOutputStream fos = null;
	        OutputStreamWriter osw = null;

	        try {
	            if (!file.exists()) {
	                boolean hasFile = file.createNewFile();
	                if(hasFile){
	                   // System.out.println("file not exists, create new file");
	                }
	                fos = new FileOutputStream(file);
	            } else {
	               // System.out.println("file exists");
	                fos = new FileOutputStream(file, true);
	            }

	            osw = new OutputStreamWriter(fos, "utf-8");
	            osw.write(a);
	            osw.write("\r\n");  //换行
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {   //关闭流
	            try {
	                if (osw != null) {
	                    osw.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (fos != null) {
	                    fos.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public static void writefinaunit(String a ){

	        String saveFile = "FinUnit.txt";
	        File file = new File(saveFile);
	        FileOutputStream fos = null;
	        OutputStreamWriter osw = null;

	        try {
	            if (!file.exists()) {
	                boolean hasFile = file.createNewFile();
	                if(hasFile){
	                   // System.out.println("file not exists, create new file");
	                }
	                fos = new FileOutputStream(file);
	            } else {
	               // System.out.println("file exists");
	                fos = new FileOutputStream(file, true);
	            }

	            osw = new OutputStreamWriter(fos, "utf-8");
	            osw.write(a);
	            osw.write("\r\n");  //换行
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {   //关闭流
	            try {
	                if (osw != null) {
	                    osw.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (fos != null) {
	                    fos.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public static void writetest(String a, String name){

	        String saveFile = name;
	        File file = new File(saveFile);
	        FileOutputStream fos = null;
	        OutputStreamWriter osw = null;

	        try {
	            if (!file.exists()) {
	                boolean hasFile = file.createNewFile();
	                if(hasFile){
	                   // System.out.println("file not exists, create new file");
	                }
	                fos = new FileOutputStream(file);
	            } else {
	               // System.out.println("file exists");
	                fos = new FileOutputStream(file, true);
	            }

	            osw = new OutputStreamWriter(fos, "utf-8");
	            osw.write(a);
	            osw.write("\r\n");  //换行
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {   //关闭流
	            try {
	                if (osw != null) {
	                    osw.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (fos != null) {
	                    fos.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


