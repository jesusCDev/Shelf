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
import java.util.Random;

public class FM_StackManager_XML extends FM_XMLParser {

    private ArrayList<FM_StackManager_Info> stacks = new ArrayList<>();

    private File xmlDocument;

    public FM_StackManager_XML(boolean creatingANewDocument){
        xmlDocument = new File(Constants.pref.get(Constants.PREF_SV_MainPath, null) + Constants.DOC_Stack_XML);
        if(!creatingANewDocument){
            getXMLInfo();
        }
    }

    public ArrayList<FM_StackManager_Info> getStacks(){
        return stacks;
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

        List<Element> projectElements = root.getChildren(FM_StackManager_Values.CHILD);
        ArrayList<FM_StackManager_Info> data = new ArrayList<>();

        for(Element pe: projectElements) {

            FM_StackManager_Info stack = new FM_StackManager_Info();
            data.add(stack);

            stack.setStackTitle(getXMLData_WithOneElement(pe, FM_StackManager_Values.TITLE));
            stack.setStackDescrption(getXMLData_WithOneElement(pe, FM_StackManager_Values.DESCRIPTION));
            stack.setStackFavoriteSTatus(getXMLData_WithOneElement(pe, FM_StackManager_Values.FAVORITE_STATUS));
            stack.setStackID(getXMLData_WithOneElement(pe, FM_StackManager_Values.ID));
        }

        stacks = data;
    }

    @Override
    public Document setXMLInfo() {
        Document doc = new Document();
        Element root = new Element(FM_StackManager_Values.ROOT);
        doc.addContent(root);

        for(FM_StackManager_Info stack: stacks){
            Element custElement = new Element(FM_StackManager_Values.CHILD);
            root.addContent(custElement);

            addChild_OneElement_OneValue(custElement, FM_StackManager_Values.TITLE, stack.getStackTitle());
            addChild_OneElement_OneValue(custElement, FM_StackManager_Values.DESCRIPTION, stack.getStackDescription());
            addChild_OneElement_OneValue(custElement, FM_StackManager_Values.FAVORITE_STATUS, stack.getStackFavoriteStatus());
            addChild_OneElement_OneValue(custElement, FM_StackManager_Values.ID, stack.getStackID());
        }

        return doc;
    }


    public void createXMLFile(){
        createXMLDocument(xmlDocument, FM_StackManager_Values.ROOT);
        updateXMLFile();
    }

    /**
     * Organizes the stack list into alphabetical order
     * @return
     */
    public void reorganizeStackAlphabetically(){

        String[] simpleList = new String[stacks.size()];

        for(int i = 0; i < stacks.size(); i++){
            simpleList[i] = stacks.get(i).getStackTitle();
        }

        Arrays.sort(simpleList);

        ArrayList<FM_StackManager_Info> newList = new ArrayList<>();

        for(int i = 0; i < stacks.size(); i++){
            int j = 0;
            while(true){
                if(simpleList[i].equalsIgnoreCase(stacks.get(j).getStackTitle())){
                    newList.add(stacks.get(j));
                    break;
                }
                j++;
            }
        }

        stacks = newList;
    }

    public ArrayList<FM_StackManager_Info> getFavStacks() {
        return getStacksStats(true);
    }

    public ArrayList<FM_StackManager_Info> getNonFavStacks() {
        return getStacksStats(false);
    }

    private ArrayList<FM_StackManager_Info> getStacksStats(boolean fav){
        ArrayList<FM_StackManager_Info> newList = new ArrayList<>();

        for(FM_StackManager_Info stack: stacks){
            if(stack.getStackFavoriteStatus().equalsIgnoreCase(Boolean.toString(fav))){
                newList.add(stack);
            }
        }
        return newList;
    }

    public void changeStackFavStats(String stackId) {
        for(FM_StackManager_Info stack: stacks){
            if(stack.getStackID().equalsIgnoreCase(stackId)){
                if(stack.getStackFavoriteStatus().equalsIgnoreCase(Boolean.toString(true))){
                    stack.setStackFavoriteSTatus(Boolean.toString(false));
                }else{
                    stack.setStackFavoriteSTatus(Boolean.toString(true));
                }
            }
        }
    }

    public void deleteStack(String stackId) {
        for(FM_StackManager_Info stack: stacks){
            if(stack.getStackID().equalsIgnoreCase(stackId)){
                deleteStackTextFile(stackId);
                stacks.remove(stack);
                break;
            }
        }
    }

    private void deleteStackTextFile(String stackName){
        File f = new File(Constants.pref.get(Constants.PREF_SV_MainPath, null) + File.separator + stackName + ".xml");
        f.delete();
    }

    public FM_StackManager_Info getStack(String stackID) {
        for(FM_StackManager_Info stack: stacks){
            if(stackID.equalsIgnoreCase(stack.getStackID())){
                return stack;
            }
        }
        return null;
    }

    public String idCreator(int length){
        StringBuilder sb = new StringBuilder();
        Random ranNum = new Random();

        for(int i = 0; i < length; i++){
            sb.append(ranNum.nextInt(Integer.MAX_VALUE));
        }
        return sb.toString();
    }
}
