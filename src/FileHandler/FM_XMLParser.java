package FileHandler;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class FM_XMLParser {

	
	
	/********* GET AND SET PROJECT INFO **********/
	
	
	// update XML file updateXMLFile
	public abstract void updateXMLFile();	// Set XML values setXml()
    // get info from xml file
	public abstract void getXMLInfo();
	// Get XML values getXml()
	public abstract Document setXMLInfo();
	// Create an xml file
	public abstract void createXMLFile();
	
	/******** CREATE XML DOCUMENT ********/
	
	
	protected void createXMLDocument(File xmlDocument, String rootElement) {
		
		try {
			Document doc = new Document();
			Element root = new Element(rootElement);
			doc.addContent(root);
			
			XMLOutputter outputter = new XMLOutputter();
			FileWriter writer = new FileWriter(xmlDocument);
			outputter.output(doc, writer);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	/******* SET VALUES *********/
	

	protected void addChild_OneElement_OneValue(Element parent, String elementName, String textValue) {
		Element child = new Element(elementName);
		child.setText(textValue);
		parent.addContent(child);
	}
	
	protected void addChildren_WithSameElement_DifferentValues(Element parent, String elementName, String[] textValues) {
		if(textValues != null) {
			for(int i = 0; i < textValues.length; i++) {
				addChild_OneElement_OneValue(parent, elementName, textValues[i]);
			}			
		}
	}

	protected void addChildren_OneElement_SameAttribute_DifferentAttributeValues(Element custElement, String attrName, String elementTag, String[][] todolistValues) {
		for(int i = 0; i < todolistValues.length; i++) {
			addChild_OneElement_OneAttribute_OneValue(custElement, trimString(elementTag), attrName, todolistValues[i][0], todolistValues[i][1]);
		}
	}

	protected void addChild_OneElement_OneAttribute_OneValue(Element parent, String elementName, String attribute, String attributeValue, String textValue) {

		Element child = new Element(elementName);
		child.setAttribute(new Attribute(trimString(attribute), trimString(attributeValue)));
		child.setText(textValue);
		parent.addContent(child);
	}
	
	
	/******* GET VALUES *********/
	

	protected String getXMLData_WithOneElement(Element el, String elementName) {
		return el.getChildText(elementName);
	}

	protected String[][] getXMLData_WithOneElement_DifferentAttributeValues(Document doc, String attrName, String[] attrValues) {


		int iter = 0;
        String[][] doubleArray = new String[attrValues.length][2];

        for(int j = 0; j < attrValues.length; j++) {
            String query = "//*[@" + trimString(attrName) + "= '" + trimString(attrValues[j]) + "']";
            XPathExpression<Element> xpe = XPathFactory.instance().compile(query, Filters.element());

            if (xpe.evaluateFirst(doc) != null) {
                doubleArray[iter][0] = attrValues[j];
                doubleArray[iter][1] = xpe.evaluateFirst(doc).getValue();
                iter++;
            }
        }

        String[][] newDoubleArray = new String[iter][2];
        for(int i = 0; i < iter; i++) {
            newDoubleArray[i][0] = doubleArray[i][0];
            newDoubleArray[i][1] = doubleArray[i][1];
        }

		return newDoubleArray;
	}
	
	protected String[] getXMLData_WithOneElement_DifferentValues(Element el, String elementName) {
		List<Element> lElements = el.getChildren(elementName);
		String[] values = new String[lElements.size()];
		
		for(int i = 0; i < lElements.size(); i++) {
			values[i] = lElements.get(i).getText();
		}
		return values;
	}

	protected  String[][] getXMLData_WithOneElement_DifferentValues_SameAttribute_DifferentAttributeValues(Element el, String elementName
    , String attributeName){
        List<Element> lElements = el.getChildren(elementName);
        String[][] values = new String[lElements.size()][2];

        for(int i = 0; i < lElements.size(); i++) {
            values[i][0] = lElements.get(i).getAttributeValue(attributeName);
            values[i][1] = lElements.get(i).getText();
        }
        return values;
    }
	
	
	/******** GET DOC ********/
	
	protected Document getDoc(File xmlDocument) {

		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(xmlDocument);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	

	/******** FIXES ATTRIBUTES AND NAMES FOR XML ********/
	
	
	protected String trimString(String s){
		String trimString = s.replaceAll("\\s", "_");
		return trimString;
	}
	
	protected String unTrimString(String s){
		String trimString = s.replaceAll("_", " ");
		return trimString;
	}
}
