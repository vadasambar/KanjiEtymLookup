/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.erapanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import webpagepanel.imagelogic.VariantImage;
import webpagepanel.enums.VariantType;

/**
 * super class for OraclePanel, SealPanel etc.,
 *
 *
 */
public class EraPanel extends JPanel {

    private int MAX_IMAGES_PER_ROW = 10;
    private JLabel title;
    private JPanel variantImagesPanel;

    public EraPanel(List<VariantImage> variantList, VariantType type) {
        // Seal + " Characters" => Seal Characters 
        String label = type.toString() + " Characters";
        title = new JLabel(label);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        variantImagesPanel = new JPanel();
        createVariantImagesPanel(variantList) ;

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // some space between two era panels. Looks better
        this.add(title);
        this.add(variantImagesPanel);
    }

    private void createVariantImagesPanel(List<VariantImage> variantImageList) {
        // if variant list is null, post a "None known message" and set flow layout
        if (variantImageList == null) {
            JLabel message = new JLabel("None known");
            message.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            variantImagesPanel.add(message);
            return;
        }
        
        // set grid layout if images exceed max images per row 
        if (variantImageList.size() >= MAX_IMAGES_PER_ROW) {
            variantImagesPanel.setLayout(new GridLayout(0, MAX_IMAGES_PER_ROW));
        }

        for (VariantImage varImage : variantImageList) {
            variantImagesPanel.add(varImage);
        }

        // size hint respected by box layout (parent layout, not grid layout). 
        // Otherwise, it expands horizontally when window is expanded. (not good)  
        variantImagesPanel.setMaximumSize(variantImagesPanel.getPreferredSize());

    }

}
