package Test.ReadandWritexml;


import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXML {
	

  public static void create(File Fileoutput,LinkedHashMap<String,String> processname,LinkedHashMap<String,String> QuantRef,LinkedHashMap<String,String>ImpactCategory,LinkedHashMap<String, LinkedHashMap<String, String>> lCIAres) {
	
	  LinkedHashMap<String,String> unit= CName.unitchange();
	  
	  try {
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = factory.newDocumentBuilder();
	Document document = db.newDocument();
	Element root = document.createElement("XML");
    document.appendChild(root);
	//standalone="no"?
	//document.setXmlStandalone(true);
	Element Flows = document.createElement("Flows");
	root.appendChild(Flows);	
	Element ProcessModules = document.createElement("ProcessModules");
	root.appendChild(ProcessModules);	
	Element ProcessModuleGroups = document.createElement("ProcessModuleGroups");
	root.appendChild(ProcessModuleGroups);	
	Element ProductSystems = document.createElement("ProductSystems");
	root.appendChild(ProductSystems);	
	Element ImpactCategories = document.createElement("ImpactCategories");
	root.appendChild(ImpactCategories);	
	Element CFactors = document.createElement("CFactors");
	root.appendChild(CFactors);	
	Element LCIAMethods = document.createElement("LCIAMethods");
	root.appendChild(LCIAMethods);	
	Element ProductDeclarations = document.createElement("ProductDeclarations");
	root.appendChild(ProductDeclarations);	
	Element Components = document.createElement("Components");
	root.appendChild(Components);
	
	Element ProductDeclaration= document.createElement("ProductDeclaration");
	ProductDeclarations.appendChild(ProductDeclaration);
	Element PDName= document.createElement("PD-Name");
	Element PDUnit= document.createElement("PD-Unit");
	ProductDeclaration.appendChild(PDName);
	ProductDeclaration.appendChild(PDUnit);
	
	String PDNa =processname.get("de")+"(de)";
	if(processname.get("de")==null) {
		PDNa=processname.get("en")+"(en)";
	}
	PDName.setTextContent(PDNa);
	
	String FinUint =unit.get(QuantRef.get("einheit_0"));	
	if(FinUint==null) {
		
	}else {
		
		PDUnit.setTextContent(FinUint);
		
	}
		
	//report.writeUnit(QuantRef.get("einheit_0"));
	
	
		
	writeImpactCategories(document,ImpactCategories,ImpactCategory);
	writeComponents(document,Components,processname,QuantRef);
	writeImpactValuesVector(document,ProductDeclaration,lCIAres);
	
	TransformerFactory tff = TransformerFactory.newInstance();
	Transformer tf = tff.newTransformer();
	
	tf.setOutputProperty(OutputKeys.INDENT, "yes");
	tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	
	
	tf.transform(new DOMSource(document), new StreamResult(new File(Fileoutput.toString())));

  } catch (Exception e) {
	e.printStackTrace();
	System.out.println("Éú³É"+Fileoutput.toString()+".xmlÊ§°Ü");
}
}

private static void writeImpactCategories(Document document, Element impactCategories, LinkedHashMap<String, String> impactCategory) {
	// TODO Auto-generated method stub
	LinkedHashMap<String,String> methode= CName.methodechange();	
	
	for(String Cg:impactCategory.keySet()) {
		
		for(String met:methode.keySet()) {	
			if(Cg.equals(met)) {
				Element ImpactCategory= document.createElement("ImpactCategory");
				impactCategories.appendChild(ImpactCategory);
				Element Category=document.createElement("Category");
				Element Indicator=document.createElement("Indicator");
				ImpactCategory.appendChild(Category);
				ImpactCategory.appendChild(Indicator);
		
				Category.setTextContent(methode.get(met));
				Indicator.setTextContent(impactCategory.get(Cg));
			}
		}
		
	}
}

private static void writeImpactValuesVector(Document document, Element ProductDeclaration, LinkedHashMap<String,LinkedHashMap<String,String>> lCIAres) {
	// TODO Auto-generated method stub
	
	DecimalFormat df = new DecimalFormat("#.##");//Genauigkeit kontroll
	LinkedHashMap<String,String> methode= CName.methodechange();	
	
	
	Element ImpactValuesVector= document.createElement("ImpactValuesVector");
	ProductDeclaration.appendChild(ImpactValuesVector);
	
	for(String CN:lCIAres.keySet()) {
		
	
		
		//System.out.println("--------------");
		//System.out.println("CN:"+CN);
		
		
		for(String met:methode.keySet()) {	
			//System.out.println("met:"+met);
			//System.out.println(CN.equals(met));
		
			if(CN.equals(met)) {
			
			Element IVVEntry= document.createElement("IVV-Entry");
			ImpactValuesVector.appendChild(IVVEntry);
			Element ICName= document.createElement("ImpactCategorie-Name");
			Element ICVMainValue= document.createElement("ICV-MainValue");
			Element ICVLowerBound= document.createElement("ICV-LowerBound");
			Element ICVUpperBound= document.createElement("ICV-UpperBound");
	
	
			IVVEntry.appendChild(ICName);
			IVVEntry.appendChild(ICVMainValue);
			IVVEntry.appendChild(ICVUpperBound);
			IVVEntry.appendChild(ICVLowerBound);
	
			LinkedHashMap<String,String> ICV=lCIAres.get(CN);
	
			double Sum=0;

	
			for(String cl:ICV.keySet()) {
		
				Sum=Sum+Double.parseDouble(ICV.get(cl));
			}
	
		String MV =String.valueOf(df.format(Sum));
	
	
		ICName.setTextContent(methode.get(met));
		ICVMainValue.setTextContent(MV);
		ICVLowerBound.setTextContent(MV);
		ICVUpperBound.setTextContent(MV);
	}
	}
	
		}
	
}

private static void writeComponents(Document document, Element components, LinkedHashMap<String,String> processname, LinkedHashMap<String,String> quantRef) {
	// TODO Auto-generated method stub
	LinkedHashMap<String,String> unit= CName.unitchange();	
	//LinkedHashMap<String,String> methode= CName.methode;	
	
	Element Component= document.createElement("Component");
	Element CName= document.createElement("Component-Name");
	Element CUnit= document.createElement("Component-Unit");
	Element ComponentReference= document.createElement("Component-Reference");
	Element ComRefMainValue= document.createElement("ComRef-MainValue");
	Element ComRefLowerBound= document.createElement("ComRef-LowerBound");
	Element ComRefUpperBound= document.createElement("ComRef-UpperBound");
	
	
	components.appendChild(Component);
	Component.appendChild(CName);
	Component.appendChild(CUnit);
	Component.appendChild(ComponentReference);
	Component.appendChild(ComRefMainValue);
	Component.appendChild(ComRefLowerBound);
	Component.appendChild(ComRefUpperBound);
	
	String Na =processname.get("de");
	if(Na==null) {
		Na=processname.get("en")+"(en)";
	}
	CName.setTextContent("Komponent_"+Na);
	String FinUint =unit.get(quantRef.get("einheit_0"));	
	if(FinUint==null) {
		System.out.println(Na+"cant get unit!");
		 
	}else {
		
		CUnit.setTextContent(FinUint);
	}
	
	 

	String RefNa =quantRef.get("de");
	if(RefNa==null) {
		RefNa=quantRef.get("en")+"(en)";
	}	
	ComponentReference.setTextContent(RefNa);
	ComRefMainValue.setTextContent(quantRef.get("Amount"));
	ComRefLowerBound.setTextContent(quantRef.get("Amount"));
	ComRefUpperBound.setTextContent(quantRef.get("Amount"));
}


}

