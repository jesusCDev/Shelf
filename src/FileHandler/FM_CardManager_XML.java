package FileHandler;

import assets.Constants;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FM_CardManager_XML extends FM_XMLParser{

    ArrayList<FM_MainCardManager_Info> cards = new ArrayList<>();
    File xmlDocument;

    public FM_CardManager_XML(String cardId, boolean creatingNewProject){
        xmlDocument = new File(Constants.pref.get(Constants.PREF_SV_MainPath, null) + File.separator + cardId + ".xml");
        if(!creatingNewProject){
            getXMLInfo();
        }
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

    }

    @Override
    public Document setXMLInfo() {
        return null;
    }

    @Override
    public void createXMLFile() {

    }
}
