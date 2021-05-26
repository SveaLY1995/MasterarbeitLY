package Test.ReadandWritexml;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetElement{
	
	
	 public static Element Readfile(String Fileinput) {
		 File fileinput =new File(Fileinput);
	if(fileinput.exists()) {
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 try {
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 try {
				 Document doc = db.parse(fileinput);
				 Element docEle = doc.getDocumentElement();
				 return docEle;			 
			 } catch (SAXException | IOException e1) {
				e1.printStackTrace();
				}
		 }catch (ParserConfigurationException e2) {
			e2.printStackTrace();
			}	
	}else {System.out.printf(fileinput+" dont exists!");}
		 return null;
	}	 

	
	 public static void getXmlName(Element docEle,LinkedHashMap<String,String> a) {
		 //NodeList UUID = docEle.getElementsByTagName("common:UUID");
		 NodeList baseName = docEle.getElementsByTagName("baseName");
		 if(baseName != null) {
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
		 //return a;
		 }
	 }
	 
	 public static void  getXmlQuantRef(Element docEle,LinkedHashMap<String,String> b, String path) {
		 NodeList exchanges = docEle.getElementsByTagName("exchange");
		 NodeList referenceToReferenceFlow = docEle.getElementsByTagName("referenceToReferenceFlow");
		 String flowID =referenceToReferenceFlow.item(0).getTextContent();
		 
		 for(int i=0;i<exchanges.getLength();i++) {
			 if(exchanges.item(i).getAttributes().item(0).getTextContent().equals(flowID)) {
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
						 
						 
						 
						String flowpath=exchange.item(j).getAttributes().getNamedItem("refObjectId").getTextContent();							
						//b.put("refObjectId", path); 
						
						String Flieinput = ".\\ILCD\\flows\\"+flowpath+".xml";
				if(new File(Flieinput).exists()) {	
						Element flow= Readfile(Flieinput);
						
				
						NodeList fflowProperties = flow.getElementsByTagName("f:flowProperty");
						for(int k=0; k<fflowProperties.getLength();k++) {
								NodeList flowProperty = fflowProperties.item(k).getChildNodes();
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
			}else {System.out.println(Flieinput+" dont exists! "+path+" flow deficiency! Write in Report. ");report.write(Flieinput,path);}
					 }
				 }
			 }
		 }	
		 }				
	 
	 public static void  getLCIAres(Element docEle,LinkedHashMap<String,String> impactCategory, LinkedHashMap<String, LinkedHashMap<String, String>> lCIAres) {
			
			String SET= "en";
			String methode="";
			String lang="";
		  	String Ind="";
		  	String amount="";
		  	String module="";
	NodeList LCIAres = docEle.getElementsByTagName("LCIAResult");

	 //System.out.println("LCIAreses:"+LCIAreses.item(1).getTextContent()+"--end--");
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
								 //System.out.println("nl3:"+nl3.item(l).hasAttributes());
								
								 
								 if(nl3.item(l).hasAttributes()) {
									 if(nl3.item(l).getAttributes().getNamedItem("xml:lang").getTextContent().isEmpty()) {
									 Ind = nl2.item(k).getTextContent();
									 In.put("de", Ind); 
									 }
								 else {
									Ind=nl3.item(l).getTextContent();
									// System.out.println("Ind:"+Ind);
									 
								 lang=nl3.item(l).getAttributes().getNamedItem("xml:lang").getTextContent();
								 In.put(lang, Ind);
								 }
									 
								 } else {
									 Ind = nl2.item(k).getTextContent();
									 In.put("de", Ind); 
								 }
								 //
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
		 System.out.println("me"+me);
		 System.out.println("In"+In);
		 
		 	methode= me.get("de");
			if(methode==null) {
				methode=me.get("en");	
				//if(methode.isEmpty()) {
				//	methode=me.get("unkown");
				//}
			}
			System.out.println(methode);
			
		Ind= In.get("de");
			if(Ind ==null) {
				Ind=In.get("en");
				//if(Ind.isEmpty()) {
				//	Ind=In.get("unkown");
				//}
			}
		lCIAres.put(methode, mo);
		impactCategory.put(methode, Ind);
			 }
	 }
	}
