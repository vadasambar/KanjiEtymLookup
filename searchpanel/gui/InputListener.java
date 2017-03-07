/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.gui;

import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import searchpanel.showkanjivariants.KanjiVariantMachine;
import searchpanel.showkanjivariants.ShowKanjiVariants;
import searchpanel.showkanjivariants.Variant;

/**
 *
 * @author suraj
 */
public class InputListener implements DocumentListener{
    private List<VariantKanjiBlock> variantBlockList;
    private KanjiVariantMachine kanjiVariantMachine; 
    private JTextField inputBox; 
    private Map<String, Character> variants; 

    public InputListener(List<VariantKanjiBlock> variantList, JTextField inputBox) {
        this.variantBlockList = variantList;
        kanjiVariantMachine = new KanjiVariantMachine(); 
        this.inputBox = inputBox; 
    }
    

    @Override
    public void insertUpdate(DocumentEvent e) {
        flushLastVariants(); // set all variant fields to blank for new input
        
        char kanjiToSearch = inputBox.getText().charAt(0); 
        System.out.println("kanji to search: " + inputBox.getText());
        variants = kanjiVariantMachine.getAllVariants(kanjiToSearch); 
        
        
        for (VariantKanjiBlock variantBlock : variantBlockList) {
            String variantInfo = variantBlock.getKanjiVariantLabel(); 
            
            if (variants.containsKey(variantInfo)) {
                String variantKanjiCharacter = variants.get(variantInfo) + "";
                variantBlock.getTextField().setText(variantKanjiCharacter);
            }
        }
        
        System.out.println("after loop: " + inputBox.getText());
    }
    
    private void flushLastVariants(){
        for (VariantKanjiBlock variant : variantBlockList) {
            variant.getTextField().setText("");
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        flushLastVariants();
        System.out.println("after flushing: " + inputBox.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // text components don't fire this function. 
    }
    
}
