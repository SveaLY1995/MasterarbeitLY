package Test.ReadandWritexml;

import java.io.File;
import java.util.LinkedHashMap;

import org.w3c.dom.Element;

public class MainAction {
	
       private static LinkedHashMap<String,String> methode= new LinkedHashMap<String,String>();
       private static LinkedHashMap<String,String> processname= new LinkedHashMap<String,String>();
       private static LinkedHashMap<String,String> QuantRef= new LinkedHashMap<String,String>();
       private static LinkedHashMap<String,String> ImpactCategory= new LinkedHashMap<String,String>();
       private static LinkedHashMap<String, LinkedHashMap<String, String>> lCIAres=new LinkedHashMap<String, LinkedHashMap<String, String>>();
		
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String Fileinput = ".\\ILCD\\processes";
		
		String[] path= ReadAllData.readAllFile(Fileinput);
		
		
		for(int i=0;i<path.length;i++) {
			System.out.println("path[i]:"+path[i]);
		/*	Element docEle= GetElement.Readfile(Fileinput+"\\"+path[i]);
			GetElement.getXmlName(docEle,processname);
			GetElement.getXmlQuantRef(docEle, QuantRef,path[i]);
			GetElement.getLCIAres(docEle,ImpactCategory, lCIAres);
		
		+	System.out.println("processname:"+processname);
			System.out.println("QuantRef:"+QuantRef);
			System.out.println("lCIAres:"+lCIAres);
			System.out.println("ImpactCategory:"+ImpactCategory);
			
		
		String name = path[i] ;
		File fileoutput= new File(".\\CreateTest\\"+name);
		new CreateXML();
		CreateXML.create(fileoutput,processname,QuantRef,ImpactCategory,lCIAres);
		
		for(String M :QuantRef.keySet()) {
			 report.writetest(M+"---xml:"+path[i], "QuantRef.txt");
		 }
		
		for(String M :ImpactCategory.keySet()) {
			 report.writetest(M+"---xml:"+path[i], "ImpactCategory.txt");
		 }
		
		for(String M :lCIAres.keySet()) {
			 report.writetest(M+"---xml:"+path[i], "lCIAres.txt");
		 }
		*/
			GetAll.getall(path[i]);
		}	System.out.println("--------Fertig!--------");
		
	}

	
}
