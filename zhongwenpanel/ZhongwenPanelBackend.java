/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhongwenpanel;

import com.sun.imageio.plugins.gif.GIFImageReader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JPanel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.bind.DatatypeConverter;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 *
 * @author suraj
 */
public class ZhongwenPanelBackend {

    private final String BASE_LINK = "http://zhongwen.com/cgi-bin/zipux.cgi?=%";
    private final int LENGTH_OF_BIG5_HEX = 4;
//    private Map<String, BufferedImage> imageResults; 
//    private List<BufferedImage> imageResults;
    private List<ImageIcon> imageResults;
    private String character; 

//    public ZhongwenPanel() {
////        imageResults = new HashMap<>(); 
////        imageResults = new ArrayList<BufferedImage>(); 
//        imageResults = new ArrayList<ImageIcon>();
//
//    }
    
    public ZhongwenPanelBackend(String character){
        this.character = character;
        imageResults = new ArrayList<ImageIcon>();
        run();
    }

    private boolean big5HexLookGood(String big5Hex) {
        if (big5Hex.length() == LENGTH_OF_BIG5_HEX) { // make sure the code is only 4 chars long AE43 or A6AE for example
            return true;
        }
        return false;
    }

    private String getUserInput() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a character: ");
        String character = reader.nextLine();

        return character;
    }

    public void run() {
        // TODO code application logic here        
//        String character = getUserInput();
        String finalLink = new ZhongWenLinkBuilder(character).getFinalLink();

        // VOLATILE CHECK THIS FIRST 
        try {
            List<String> imgLinks = getImgLinks(finalLink);

            for (String imgLink : imgLinks) {
                System.out.println(imgLink);
//                String[] linkSplits = imgLink.split("/");
//                String imgId = linkSplits[linkSplits.length - 1];

//                System.out.println("img id: " + imgId);

                try {
//                    BufferedImage img = ZhongwenImageIO.read(imgLink); 
                    ImageIcon img = ZhongwenImageIO.read(imgLink); 
                    
//                    System.out.println(imgId + " created...");
                    imageResults.add(img); // to read the buffered images later

//                    ZhongwenImageIO.writeTofIle(img, imgId);
                } catch (Exception e) {
//                    System.out.println("IO error for: " + imgId);
                    System.out.println("IO error desu :/");
                }
            }
            

        } catch (Exception e) {
            System.err.println("character not found on zhongwen. :/");
            e.printStackTrace();
        }

    }

    private List<String> getImgLinks(String finalLink) throws IOException {
        List<String> imgLinks = new ArrayList<>();

        String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:50.0) Gecko/20100101 Firefox/50.0";
        Document doc = Jsoup.connect(finalLink)
                .userAgent(userAgent)
                .get();

        imgLinks = new ImgLinkExtractor(doc).getImgLinks();
        return imgLinks;
    }

//    public static void main(String[] args) {
//        new ZhongwenPanel().run();
//    }
    
    // this needs refactoring 
    //START
    public JPanel etymDescription() {
        int etymDescription = 0;
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        
        JLabel imgLabel = new JLabel(imageResults.get(etymDescription), JLabel.CENTER);
        panel.add(imgLabel);
        
        return panel;
    }
    
    public JPanel etymTree(){
        int etymTree = 1;
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        
        JLabel imgLabel = new JLabel(imageResults.get(etymTree), JLabel.CENTER);
        panel.add(imgLabel);
        return panel;
    }
    // END

}
