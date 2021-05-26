package Oekobaudat.Datenbank.XML;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class MainAction {

public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String oldordner = ".\\ILCD\\processes";	
        String newordner = ".\\CreateTest";	
        int S=0;
		String[] path= ReadAllData.readAllFile(oldordner);
		for(int i=0;i<path.length;i++) {
			
		
			String C=path[i];
			String Fileinput = oldordner+"\\"+C;
			String Fileoutput = newordner+"\\"+C;
			
			mainaction(Fileinput,Fileoutput);
			S++;
		}
		System.out.println("--------Fertig!--------");
		System.out.println("----build "+S+" xml-----");
		
	}
private static void mainaction(String Fileinput,String Fileoutput) throws Exception {
		Element docEle= Readfile(Fileinput);
	
		LinkedHashMap<String,String> rename = new LinkedHashMap<String,String>();
		
		LinkedHashMap<String,String> processname = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String> QuantRef = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String> ImpactCategory=new LinkedHashMap<String,String>();
		LinkedHashMap<String,LinkedHashMap<String,String>> LCIAres = new LinkedHashMap<String,LinkedHashMap<String,String>>(); 
		

		 
		 getXmlName(docEle,processname,rename);
		 getXmlQuantRef(docEle,QuantRef);
		 getLCIAres(docEle,ImpactCategory,LCIAres);
		 
		
		
		 CreateXML.create(new File(Fileoutput), processname, QuantRef, ImpactCategory, LCIAres);	
	}

public static Element Readfile(String Fileinput) {
		File fileinput =new File(Fileinput);
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 try {
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 try {
				 Document doc = db.parse(fileinput);
				 Element docEle = doc.getDocumentElement();
					return docEle;
			 } catch (SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
		 }catch (ParserConfigurationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				}
			return null;	
		}	 
		
private static void  getXmlName(Element docEle,LinkedHashMap<String,String> a, LinkedHashMap<String,String> rename) {
	
	 NodeList nl = docEle.getElementsByTagName("common:UUID");
	 String UUID=nl.item(0).getTextContent();
	
		
		NodeList baseName = docEle.getElementsByTagName("baseName");
		 for(int i=0;i<baseName.getLength();i++) {
			 	
			 String bN=baseName.item(i).getTextContent();
			 String lang ="";
			 
				for(int k=0;k<baseName.item(i).getAttributes().getLength();k++) {
					if(baseName.item(i).getAttributes().item(k).getNodeName().equals("xml:lang")) {
						lang=baseName.item(i).getAttributes().item(k).getTextContent();
					}
				}
					a.put(lang, bN);
		 }
		 
		 String PDNa =a.get("de")+"(de)";
			if(a.get("de")==null) {
				PDNa=a.get("en")+"(en)";
			}
		report.writetest(UUID+" = "+PDNa, "Konversion_UUID_Produktname.txt");
		rename.put(UUID, PDNa);
		 
	}
		
private static void  getXmlQuantRef(Element docEle,LinkedHashMap<String,String> b) throws SAXException {
		 NodeList exchanges = docEle.getElementsByTagName("exchange");
		 NodeList referenceToReferenceFlow = docEle.getElementsByTagName("referenceToReferenceFlow");
		 String flowID =referenceToReferenceFlow.item(0).getTextContent();
		 for(int i=0;i<exchanges.getLength();i++) {
			 if(exchanges.item(i).getAttributes().getNamedItem("dataSetInternalID").getTextContent().equals(flowID)) {
				 NodeList exchange = exchanges.item(i).getChildNodes();
				 for(int j =0; j<exchange.getLength();j++) {
					 if(exchange.item(j).getNodeName().equals("meanAmount")) {
							String Amount =  exchange.item(j).getTextContent();
							b.put("Amount", Amount);
					 }
					 if(exchange.item(j).getNodeName().equals("referenceToFlowDataSet")) {
						 NodeList referenceToFlowDataSet =exchange.item(j).getChildNodes();
						 for(int t=0;t<referenceToFlowDataSet.getLength();t++) {
							 if(referenceToFlowDataSet.item(t).getNodeName().equals("common:shortDescription")) {
								 Node shortDescription=(Node) referenceToFlowDataSet.item(t).getChildNodes();
								 b.put(shortDescription.getAttributes().getNamedItem("xml:lang").getTextContent(), shortDescription.getTextContent());
							 }
							 
						 }
						
						String path=exchange.item(j).getAttributes().getNamedItem("refObjectId").getTextContent();
						b.put("refObjectId", path); 
						String Fileinput = ".\\ILCD\\flows\\"+path+".xml";
						
						if(new File(Fileinput).exists()) {
								
								Element flow= Readfile(Fileinput);
								NodeList FflowProperties = flow.getElementsByTagName("f:flowProperty");//???
								for(int k=0; k<FflowProperties.getLength();k++) {
										NodeList flowProperty = FflowProperties.item(k).getChildNodes();
										for(int l=0; l<flowProperty.getLength();l++) {
											if(flowProperty.item(l).getNodeName().equals("f:referenceToFlowPropertyDataSet")) {
												
												NodeList refToFPDSet=flowProperty.item(l).getChildNodes();
												String einheit=refToFPDSet.item(l).getTextContent();
												
												b.put("einheit_"+k, einheit);
												
											}
										}	
								}
								NodeList flowProperties = flow.getElementsByTagName("flowProperty");
								for(int k=0; k<flowProperties.getLength();k++) {
								NodeList flowProperty = flowProperties.item(k).getChildNodes();
								for(int l=0; l<flowProperty.getLength();l++) {
									if(flowProperty.item(l).getNodeName().equals("referenceToFlowPropertyDataSet")) {
										
										NodeList refToFPDSet=flowProperty.item(l).getChildNodes();
										String einheit=refToFPDSet.item(l).getTextContent();
										
										b.put("einheit_"+k, einheit);
									}
								}
								}
					}else {System.out.printf(Fileinput+" dont exists! ");
					//report.writetest(Fileinput, "Flowsmangel.txt");
					}
					
							 }
						 }
					 }
				 }
	}
	
private static void  getLCIAres(Element docEle,LinkedHashMap<String,String> impactCategory, LinkedHashMap<String, LinkedHashMap<String, String>> lCIAres) {
		
		String methode="";
		String lang="";
	  	String Ind="";
	  	String amount="";
	  	String module="";
	  	NodeList LCIAres = docEle.getElementsByTagName("LCIAResult");
	  	for(int i = 0;i<LCIAres.getLength();i++) {
	  		 
	  		 LinkedHashMap<String, String> me= new LinkedHashMap<String,String>(); 
	  		 LinkedHashMap<String, String> In=new LinkedHashMap<String,String>(); 
	  		 LinkedHashMap<String, String> mo=new LinkedHashMap<String,String>();
	  		 NodeList nl=LCIAres.item(i).getChildNodes();
	  		 for(int j = 0;j<nl.getLength();j++) {
	  			 if(nl.item(j).getNodeName().equals("referenceToLCIAMethodDataSet")) {
	  				 NodeList nl2= nl.item(j).getChildNodes();
	  				 for(int k=0;k< nl2.getLength();k++) {
	  					 if(nl2.item(k).getNodeName().equals("common:shortDescription")) {
	  						 
	  						 if(nl2.item(k).hasAttributes()) {
	  							 if(nl2.item(k).getAttributes().getNamedItem("xml:lang").getTextContent().isEmpty()) {
	  								 methode = nl2.item(k).getTextContent();
	  								 me.put("de", methode);
	  							 }
	  						else {	 
	  							 methode = nl2.item(k).getTextContent(); 
	  							 lang= nl2.item(k).getAttributes().getNamedItem("xml:lang").getTextContent();
	  							 me.put(lang, methode);
	  									 
	  							}			 
	  						 }else {
	  							 methode = nl2.item(k).getTextContent();
	  							 me.put("de", methode);
	  						 }
	  					 }
	  				 }
	  			 }
	  			 if(nl.item(j).getNodeName().equals("common:other")) {
	  				 NodeList nl2= nl.item(j).getChildNodes();
	  				 for(int k=0;k< nl2.getLength();k++) {
	  					 if(nl2.item(k).getNodeName().equals("epd:referenceToUnitGroupDataSet")) {
	  						 NodeList nl3=nl2.item(k).getChildNodes();
	  						 for(int l=0;l<nl3.getLength();l++) {
	  							 if(nl3.item(l).getNodeName().equals("common:shortDescription")) {
	  								 if(nl3.item(l).hasAttributes()) {
	  									 if(nl3.item(l).getAttributes().getNamedItem("xml:lang").getTextContent().isEmpty()) {
	  									 Ind = nl2.item(k).getTextContent();
	  									 In.put("de", Ind); 
	  									 }
	  								 else {
	  									Ind=nl3.item(l).getTextContent();
	  									 lang=nl3.item(l).getAttributes().getNamedItem("xml:lang").getTextContent();
	  									 In.put(lang, Ind);
	  									 }
	  										 
	  									 } else {
	  										 Ind = nl2.item(k).getTextContent();
	  										 In.put("de", Ind); 
	  									 }
	  							 }
	  						 }
	  						 
	  					 }
	  					 if(nl2.item(k).getNodeName().equals("epd:amount")) {
	  						 
	  						 amount= nl2.item(k).getTextContent();
	  						 if(amount.isEmpty()) {
	  							 amount="0";
	  						 }
	  						 module=nl2.item(k).getAttributes().getNamedItem("epd:module").getTextContent();
	  						 mo.put(module, amount);
	  					 }
	  							
	  				 }
	  			 }
	  		 }
	  		// System.out.println("me"+me);
	  		// System.out.println("In"+In);
	  		 
	  		 	methode= me.get("de");
	  			if(methode==null) {
	  				methode=me.get("en");	
	  			}

	  			
	  		Ind= In.get("de");
	  			if(Ind ==null) {
	  				Ind=In.get("en");
	  			}
	  			lCIAres.put(methode, mo);
	  			impactCategory.put(methode, Ind);
	  				 }
	  		 }
												
}
