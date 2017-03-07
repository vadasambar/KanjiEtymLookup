/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import webpagepanel.ChineseEtymPanel;

/**
 *
 * @author suraj
 */
public class SearchButtonListener implements ActionListener{
    private JTextField input; 
    private ChineseEtymPanel etymPanel; 

    public SearchButtonListener(JTextField input, ChineseEtymPanel etymPanel) {
        this.input = input;
        this.etymPanel = etymPanel; 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {  
        String character = input.getText();
        etymPanel.getEtymology(character); 
    }
    
}
