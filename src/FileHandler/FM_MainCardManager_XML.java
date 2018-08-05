package FileHandler;

import assets.Constants;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FM_MainCardManager_XML extends FM_XMLParser {

    private ArrayList<FM_MainCardManager_Info> cards = new ArrayList<>();

    private File xmlDocument;

    public FM_MainCardManager_XML(boolean creatingANewDocument){
        xmlDocument = new File(Constants.pref.get(Constants.PREF_SV_MainPath, null) + File.separator + Constants.DOC_MainCard_XML);
        if(!creatingANewDocument){
            getXMLInfo();
        }
    }

    public ArrayList<FM_MainCardManager_Info> getCards(){
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

        List<Element> projectElements = root.getChildren(FM_MainCardManager_Values.CHILD);
        ArrayList<FM_MainCardManager_Info> data = new ArrayList<>();

        for(Element pe: projectElements) {

            FM_MainCardManager_Info card = new FM_MainCardManager_Info();
            data.add(card);

            card.setCardTitle(getXMLData_WithOneElement(pe, FM_MainCardManager_Values.TITLE));
            card.setCardDescrption(getXMLData_WithOneElement(pe, FM_MainCardManager_Values.DESCRIPTION));
            card.setCardFavoriteSTatus(getXMLData_WithOneElement(pe, FM_MainCardManager_Values.FAVORITE_STATUS));
            card.setCardID(getXMLData_WithOneElement(pe, FM_MainCardManager_Values.ID));
        }

        cards = data;
    }

    @Override
    public Document setXMLInfo() {
        Document doc = new Document();
        Element root = new Element(FM_MainCardManager_Values.ROOT);
        doc.addContent(root);

        for(FM_MainCardManager_Info card: cards){
            Element custElement = new Element(FM_MainCardManager_Values.CHILD);
            root.addContent(custElement);

            addChild_OneElement_OneValue(custElement, FM_MainCardManager_Values.TITLE, card.getCardTitle());
            addChild_OneElement_OneValue(custElement, FM_MainCardManager_Values.DESCRIPTION, card.getCardDescription());
            addChild_OneElement_OneValue(custElement, FM_MainCardManager_Values.FAVORITE_STATUS, card.getCardFavoriteStatus());
            addChild_OneElement_OneValue(custElement, FM_MainCardManager_Values.ID, card.getCardID());
        }

        return doc;
    }


    public void createXMLFile(){
        createXMLDocument(xmlDocument, FM_MainCardManager_Values.ROOT);
        updateXMLFile();
    }

    /**
     * Organizes the card list into alphabetical order
     * @return
     */
    public void reorganizeCardAlphabetically(){

        String[] simpleList = new String[cards.size()];

        for(int i = 0; i < cards.size(); i++){
            simpleList[i] = cards.get(i).getCardTitle();
        }

        Arrays.sort(simpleList);

        ArrayList<FM_MainCardManager_Info> newList = new ArrayList<>();

        for(int i = 0; i < cards.size(); i++){
            int j = 0;
            while(true){
                if(simpleList[i].equalsIgnoreCase(cards.get(j).getCardTitle())){
                    newList.add(cards.get(j));
                    break;
                }
                j++;
            }
        }

        cards = newList;
    }

    public ArrayList<FM_MainCardManager_Info> getFavCards() {
        return getCardsStats(true);
    }

    public ArrayList<FM_MainCardManager_Info> getNonFavCards() {
        return getCardsStats(false);
    }

    private ArrayList<FM_MainCardManager_Info> getCardsStats(boolean fav){
        ArrayList<FM_MainCardManager_Info> newList = new ArrayList<>();

        for(FM_MainCardManager_Info card: cards){
            if(card.getCardFavoriteStatus().equalsIgnoreCase(Boolean.toString(fav))){
                newList.add(card);
            }
        }
        return newList;
    }

    public void changeCardFavStats(String cardId) {
        for(FM_MainCardManager_Info card: cards){
            if(card.getCardID().equalsIgnoreCase(cardId)){
                if(card.getCardFavoriteStatus().equalsIgnoreCase(Boolean.toString(true))){
                    card.setCardFavoriteSTatus(Boolean.toString(false));
                }else{
                    card.setCardFavoriteSTatus(Boolean.toString(true));
                }
            }
        }
    }
}
