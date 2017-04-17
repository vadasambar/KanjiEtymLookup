/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wikipanel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author suraj
 */
public class WikipediaPanel extends JPanel {

    public WikipediaPanel() {
        run();
    }

    public void run() {
//        String character = "葉";
        String character = "世";
        String base = "https://en.wiktionary.org/wiki/";
        String finalLink = base + character;

        try {
            Document doc = Jsoup.connect(finalLink).timeout(0).get(); // timeeout(0) for no time outs (infinite time) 

            Element chineseHeading = doc.getElementById("Chinese");
            Element chineseParent = chineseHeading.parent();

            System.out.println("======================");
            System.out.println("chinese parent");
            System.out.println("======================");
            System.out.println(chineseParent);

            System.out.println("======================");
            System.out.println("sibling");
            System.out.println("======================");
            System.out.println(chineseParent.nextElementSibling());

            // VOLATILE
            // part from <h2> Chinese </h2> to <h3> Pronunciation </h3>
            // "Chinese" amd "Pronunciation" parts excluded
            // since I don't need them
            Elements chineseToPronuncPart = new Elements();
            List<String> chineseToPronText = new ArrayList<>(); // only text part of chineseToPronuncPart

            Element nextElement = chineseParent.nextElementSibling();
            int count = 1;
            while (true) {
                System.out.println("");
                System.out.println("inside while");
                System.out.println("----------------------");
                System.out.println("sibling " + count);
                System.out.println("======================");
                System.out.println(nextElement);
//                System.out.println(nextElement.child(0).id());

                // find the tag with id = "Pronunciation"
                if (nextElement.child(0).id().contains("Pronunciation")) {
//                    System.out.println("Mitsuketa!!");
//                    System.out.println("======================");
//                    System.out.println(nextElement);
                    break;
                }

                // table tags that come between "Chinese" and "Pronunciation" are 
                // not required since they don't contain etymology info in texts 
                // (they are either images or something else)
                // SUPER VOLATILE
                // skip table elements since I don't need them
                if (nextElement.tagName().equals("table")) {
                    nextElement = nextElement.nextElementSibling();
                    count++;
                } else {
                    // else needed here because I want to skip this part
                    // if the above "if" statement is executed and let it go
                    // over the loop again to check for id == "Pronunciation"
                    chineseToPronuncPart.add(nextElement);

                    // get text from elements (combine all child node/element text)
                    chineseToPronText.add(nextElement.text() + "\n");
                    nextElement = nextElement.nextElementSibling();
                    count++;
                }
            }

            System.out.println("");
            System.out.println("Chinese to Pronunciation part");
            System.out.println("=================================");

            String etymTextJoined = "";
            for (String text : chineseToPronText) {
                etymTextJoined += text;
            }

            System.out.println(etymTextJoined);
            System.out.println("out of while");

            JTextArea guiDisplayText = new JTextArea(etymTextJoined);
            
            // VOLATILE 
            int columnWidth = 50; // arbitrarily (trial and error) chosen 
            guiDisplayText.setColumns(columnWidth); 
            guiDisplayText.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
            guiDisplayText.setEditable(false);
            guiDisplayText.setLineWrap(true);
            guiDisplayText.setOpaque(false); // set color to transparent
            this.add(guiDisplayText);
        } catch (Exception e) {
        }
    }
}
