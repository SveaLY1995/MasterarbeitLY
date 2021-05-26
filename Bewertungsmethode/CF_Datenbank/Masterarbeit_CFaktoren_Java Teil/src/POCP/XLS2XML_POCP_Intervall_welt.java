package POCP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import jxl.Sheet;
import jxl.Workbook;

import java.util.ArrayList;
import java.util.Collections;

public class XLS2XML_POCP_Intervall_welt {

	public static void main(String[] args) throws Exception {
		XLS2XML_POCP_Intervall_welt file = new XLS2XML_POCP_Intervall_welt();
		file.xls2xml("characterisation_factors.xls","CF_POCP_Intervall_welt.xml");}
	
		//Welt inkl.Europa, nur für high-NOx Hintergrund Spalte F,G,H,keine Begrenzung für baseline oder non-baseline CML 2001

	public double findmax(double[] temp) {
		int length=temp.length;
		double max=temp[0];
		for(int i=1; i<length;i++) {
			if(temp[i]>max) max=temp[i];
		}
		return max;
	}
	
	public double findmin(double[] temp) {
		int length=temp.length;
		double min=temp[0];
		for(int i=1; i<length;i++) {
			if(temp[i]<min) min=temp[i];
		}
		return min;
	}
	
	public void xls2xml(String excelPath, String xmlPath) throws Exception {
		
		Workbook readwb=null;
		
		try {
			
			readwb = Workbook.getWorkbook(new File(excelPath));
			
			// using Dom4j package to build a doc
			Document doc = DocumentHelper.createDocument();
			
			// add a root element into the doc
			Element root = doc.addElement("XML");
			Element Flows = root.addElement("Flows");
			Element ProcessModules = root.addElement("ProcessModules");
			Element FlowValueMapGroups = root.addElement("FlowValueMapGroups");
			Element ProductSystems = root.addElement("ProductSystems");
			Element ImpactCategories= root.addElement("ImpactCategories");
			Element ImpactCategory = ImpactCategories.addElement("ImpactCategory");
			Element Category = ImpactCategory.addElement("Category");
			Category.addText ("Bildung von troposphärischen Ozon");
			Element Indicator = ImpactCategory.addElement("Indicator");
			Indicator.addText ("ethylene äquivalent");
			Element CFactors = root.addElement("CFactors");
			Element LCIAMethods = root.addElement("LCIAMethods");
			Element LCIAMethod= LCIAMethods.addElement("LCIAMethod");
			Element LCIAname= LCIAMethod.addElement("LCIA-Name");
			LCIAname.addText("Bewertungsmethod für POCP");
			Element LCIACategories= LCIAMethod.addElement("LCIA-Categories");
			Element LCIACategory=  LCIACategories.addElement("LCIA-Category");
			 LCIACategory.addText("Bildung von troposphärischen Ozon");
			Element LCIAFactors= LCIAMethod.addElement("LCIA-Factors");
			Element ProductDeclarations = root.addElement("ProductDeclarations");
			Element ImpactValueMapGroups = root.addElement("ImpactValueMapGroupss");
	
			Element Components = root.addElement("Components");
			Element Compositions = root.addElement("Compositions");
			
			

			
//			for(int m = 0; m<readwb.getNumberOfSheets();m++) {
				Sheet sheet = readwb.getSheet(7);
				
				// get the row and the column number of excel
				int nColumns = sheet.getColumns();
				int nRows = sheet.getRows();
				
				
				// loop each row
				for (int i=6; i<nRows; i++) {
					if(sheet.getCell(5,i).getContents()=="" && sheet.getCell(6,i).getContents()=="" && sheet.getCell(7,i).getContents()=="")
						continue;
					else {
					Element Flow = Flows.addElement("Flow");
					Element name = Flow.addElement("FlowName");
				    name.addText (sheet.getCell(0,i).getContents());
					Element typ = Flow.addElement("FlowType");
					typ.addText ("Elementary");
					Element unit = Flow.addElement("FlowUnit");
					unit.addText (sheet.getCell(3,i).getContents());}
				
					}
				 
				for (int i=6; i<nRows; i++) {
					
					if(sheet.getCell(5,i).getContents()=="" && sheet.getCell(6,i).getContents()=="" && sheet.getCell(7,i).getContents()=="")
						continue;
					else {
//					
				Element CFactor =CFactors.addElement("CFactor");
				Element CFName =CFactor.addElement("CFName");
				String subname =sheet.getCell(0,i).getContents();
				CFName.addText ("CF_POCP_welt_"+subname);
				
				
				Element CFFlow =CFactor.addElement("CFFlow");
				CFFlow.addText (sheet.getCell(0,i).getContents());
			    Element CFCategory =CFactor.addElement("CFCategory");
			    CFCategory.addText ("Bildung von troposphärischen Ozon");
			    Element CFMainValue=CFactor.addElement("CFMainValue");
			    Element CFLowerBound=CFactor.addElement("CFLowerBound");
			    Element CFUpperBound=CFactor.addElement("CFUpperBound");
			    
	
			    	int count = 0; // 计算数组的大小 insgesamt
			    	for(int j=5; j<nColumns;j++) {		    		
			    		if(sheet.getCell(j,i).getContents().trim().equals("")) {continue;}
			    		else { count++;
			    		}}
			    	
			    	if(count==0) {continue;}
			    	else
			    	{
			    	double [] valueOfCell =new double[count];
			    	
			    	int index=0;
			    	for(int j=5; j<nColumns; j++) {
			    		
			    		if(sheet.getCell(j,i).getContents()=="") {continue;}
			    		else { String temp_Sting=sheet.getCell(j,i).getContents();
			    				double temp_num = Double.parseDouble(temp_Sting);
			    				
			    			 valueOfCell[index]=temp_num;
			    			 index++;
			    		}
			    	}
			    	double max=findmax(valueOfCell);
			    	double min=findmin(valueOfCell);
			    	double avr= (max+min)/2;
			    	String lowerbound = Double.toString(min);
				    CFLowerBound.addText(lowerbound);

					String upperbound = Double.toString(max);
				    CFUpperBound.addText(upperbound);
					
					String cfmv= Double.toString(avr);
				       CFMainValue.addText(cfmv);}
			    	
			    	Element LCIAFactor =LCIAFactors.addElement("LCIA-Factor");
					String subname1 =sheet.getCell(0,i).getContents();
					LCIAFactor.addText ("CF_POCP_welt_"+subname1);
			    }
			    
			
					}
				
					
	

			
			// define xml format
			OutputFormat format = new OutputFormat();
			format.setIndentSize(2);  
            format.setNewlines(true);
            format.setTrimText(true); 
            format.setPadText(true);
            format.setNewLineAfterDeclaration(false);
            
   
            XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format);
            writer.write(doc);
            System.out.println("dom4j CreateDom4j success!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			readwb.close();
		}
		
		
	}

		}

