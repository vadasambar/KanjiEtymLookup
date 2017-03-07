/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.imagelogic;

import webpagepanel.enums.VariantType;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

/**
 * JLabel showing variant image (image icon) and its id (text).
 *
 * @author suraj Parameters : String link
 */
public class VariantImage extends JLabel {

    private ImageIcon icon;
    private String imageCaption = "";
    private VariantType type;

    public VariantImage(String link) {
        super(null, null, SwingConstants.CENTER);
        setFormatting();

        icon = new ImageIcon(link); 
        icon = scaleTo(50, 50, icon); // height = 50, width = 50 

        String caption = getImageCaption(link);
        this.setIcon(icon);
        this.setText(caption);

    }

    private void setFormatting() {
        this.setOpaque(true); // set opaque by default is false for JLabel

        // compound border. Outer border creates empty space. 
        Border insideBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
        Border outsideBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

        // center the text below image
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalTextPosition(JLabel.CENTER);
    }

    private String getImageCaption(String link) {
        String[] linkParts = link.split("/");

        int lastPart = linkParts.length - 1;
        String lastPartOfLink = linkParts[lastPart];

        String removeDotGifFromLastPart = lastPartOfLink.split("\\.")[0]; // "\\." => "." http://stackoverflow.com/questions/3674930/java-regex-meta-character-and-ordinary-dot
        imageCaption = removeDotGifFromLastPart.toUpperCase(); // b19493 => B19493

        return imageCaption;
    }

    // // http://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    private ImageIcon scaleTo(int width, int height, ImageIcon icon) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        return scaledIcon;
    }

    public VariantType getType() {
        if (imageCaption.startsWith("S")) {
            return type.SEAL;

        } else if (imageCaption.startsWith("L")) {
            return type.LST_SEAL;

        } else if (imageCaption.startsWith("B")) {
            return type.BRONZE;

        } else if (imageCaption.startsWith("J")) {
            return type.ORACLE;
        }

        return null;
    }

    public String getCaption() {
        return imageCaption;
    }

    public Image getImage() {
        return icon.getImage();
    }

}
