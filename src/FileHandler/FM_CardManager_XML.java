package FileHandler;

import assets.Constants;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FM_CardManager_XML extends FM_XMLParser{

    private ArrayList<FM_CardManager_Info> cards = new ArrayList<>();
    private File xmlDocument;

    public FM_CardManager_XML(String cardName, boolean creatingNewProject){
        xmlDocument = new File(Constants.pref.get(Constants.PREF_SV_MainPath, null) + cardName + ".xml");

        if(!creatingNewProject){
            getXMLInfo();
        }
    }

    public ArrayList<FM_CardManager_Info> getCards() {
        return cards;
    }

    @Override
    public void updateXMLFile() {
        org.jdom2.Document doc = setXMLInfo();
        XMLOutputter outputter = new XMLOutputter();

        try {
            FileWriter writer = new FileWriter(xmlDocument);
            outputter.output(doc, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getXMLInfo() {
        Document doc = getDoc(xmlDocument);
        Element root = doc.getRootElement();

        List<Element> projectElements = root.getChildren(FM_CardManager_Values.CHILD);
        ArrayList<FM_CardManager_Info> data = new ArrayList<>();

        for(Element pe: projectElements) {

            FM_CardManager_Info card = new FM_CardManager_Info();
            data.add(card);

            card.setCardTitle(getXMLData_WithOneElement(pe, FM_CardManager_Values.TITLE));
            card.setCardDescription(getXMLData_WithOneElement(pe, FM_CardManager_Values.DESCRIPTION));
            card.setCardCopyData(getXMLData_WithOneElement(pe, FM_CardManager_Values.COPY_DATA));
        }

        cards = data;
    }

    @Override
    public Document setXMLInfo() {
        Document doc = new Document();
        Element root = new Element(FM_CardManager_Values.ROOT);
        doc.addContent(root);

        for(FM_CardManager_Info card: cards){
            Element custElement = new Element(FM_CardManager_Values.CHILD);
            root.addContent(custElement);

            addChild_OneElement_OneValue(custElement, FM_CardManager_Values.TITLE, card.getCardTitle());
            addChild_OneElement_OneValue(custElement, FM_CardManager_Values.DESCRIPTION, card.getCardDescription());
            addChild_OneElement_OneValue(custElement, FM_CardManager_Values.COPY_DATA, card.getCardCopyData());
        }

        return doc;
    }

    @Override
    public void createXMLFile() {
        createXMLDocument(xmlDocument, FM_CardManager_Values.ROOT);
        updateXMLFile();
    }
}
