/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author suraj
 */
public class VariantKanjiBlock extends JPanel {

    private JLabel label;
    private JTextField kanji;
    private JButton searchButton;

    public VariantKanjiBlock(String label) {
        this.setLayout(new GridLayout(0, 1));

        this.label = new JLabel(label);
        kanji = new JTextField();
        searchButton = new JButton("Search this");

        formatComponents();
        addComponents();

    }

    private void formatComponents() {
        label.setHorizontalAlignment(JLabel.CENTER);
        kanji.setEditable(false);
        kanji.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        kanji.setBackground(Color.WHITE);
        kanji.setHorizontalAlignment(JTextField.CENTER);
    }

    private void addComponents() {
        this.add(label);
        this.add(kanji);
        this.add(searchButton);
    }
    
    public JLabel getLabel(){
        return label;
    }
    
    public String getKanjiVariantLabel(){
        return label.getText(); 
    }
    
    public JTextField getTextField(){
        return kanji; 
    }
    
    public String getVariantKanji(){
        return kanji.getText(); 
    }
    
    public JButton getSearchButton(){
        return searchButton; 
    }

}
