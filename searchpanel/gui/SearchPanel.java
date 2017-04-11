/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.PlainDocument;
import searchpanel.showkanjivariants.KanjiVariantMachine;
import webpagepanel.ChineseEtymPanel;
import zhongwenpanel.ZhongwenPanel;

/**
 *
 * @author suraj
 */
public class SearchPanel extends JPanel {

    private JTextField inputBox;
    private JButton searchButton;
    private int MAX_CHAR_LIMIT = 1; 
    private List<VariantKanjiBlock> variantBlockList;

    public SearchPanel() {
//        this.setPreferredSize(new Dimension(500, 400));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        variantBlockList = new ArrayList<>();

        inputBox = new JTextField();
//        inputBox.setText("y");
        formatTextField();

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(90, 40));
        searchButton.setAlignmentX(CENTER_ALIGNMENT); // http://stackoverflow.com/questions/2560784/how-to-center-elements-in-the-boxlayout-using-center-of-the-element
        
        
        addListeners();
        addComponents();
    }

    private void formatTextField() {
        PlainDocument doc = (PlainDocument) inputBox.getDocument();
        doc.setDocumentFilter(new DocumentCharLimitFilter(MAX_CHAR_LIMIT)); // limit input to 1 char

        inputBox.setPreferredSize(new Dimension(80, 40));
        inputBox.setMaximumSize(inputBox.getPreferredSize()); // http://stackoverflow.com/questions/2709220/how-do-i-keep-jtextfields-in-a-java-swing-boxlayout-from-expanding    
        inputBox.setFont(new Font("", Font.BOLD, 30));
        inputBox.setAlignmentX(CENTER_ALIGNMENT); // center the text field
        inputBox.setHorizontalAlignment(JTextField.CENTER); // center the text 
    }

    private JPanel createVariantPanel() {
        JPanel variantPanel = new JPanel();
        createVariantList(); 
        
        for (VariantKanjiBlock variant : variantBlockList) {
            variantPanel.add(variant);
        }
        return variantPanel;
    }

    private void addListeners() {
        inputBox.getDocument().addDocumentListener(new InputListener(variantBlockList, inputBox));         
    }

    private void createVariantList() {
        //  Can't change to simpler names because my unihan conversion text file has same names. Fuck. 
        variantBlockList.add(new VariantKanjiBlock("kSemanticVariant"));
        variantBlockList.add(new VariantKanjiBlock("kSimplifiedVariant"));
        variantBlockList.add(new VariantKanjiBlock("kSpecializedSemanticVariant"));
        variantBlockList.add(new VariantKanjiBlock("kTraditionalVariant"));
        variantBlockList.add(new VariantKanjiBlock("kZVariant"));
    }

    private void addComponents() {
        this.add(inputBox);
        this.add(searchButton);
        this.add(createVariantPanel());
    }   
    
    
    public void setSearchButtonListener(ChineseEtymPanel etymPanel, ZhongwenPanel zhongwen ){
        searchButton.addActionListener(new SearchButtonListener(inputBox, etymPanel, zhongwen));
    }
}
