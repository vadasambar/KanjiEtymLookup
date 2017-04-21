/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wikipanel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class WictionaryPanel extends JPanel {
    private String character;

//    public WictionaryPanel() {
//       this.add(search("著")); 
//    }
    
    // this changes state of the object and returns it
    // search and add the results "this"
    public JPanel search(String character) {
        this.character = character;
        searchForEtymology();
        return this;
    }

    public void searchForEtymology() {
        Document doc = searchCharacterOnWictionary();
        Element chineseHeading = doc.getElementById("Chinese");
        Element chineseParent = chineseHeading.parent();  // element wrapping chineseHeading
        // part from <h2> Chinese </h2> to <h3> Pronunciation </h3>
        // "Chinese" amd "Pronunciation" parts excluded
        // since I don't need them
        List<String> etymPartText = new ArrayList<>();
        Element nextElement = chineseParent.nextElementSibling();

        while (true) {
            // if "Pronunciation" part not found, exit at hr tag 
            if (nextElement.tagName().contains("hr")) {
                break;
            } else if (nextElement.child(0).id().contains("Pronunciation")) {
                break;
            }

            // table tags that come between "Chinese" and "Pronunciation" are 
            // not required since they don't contain etymology info in texts 
            // (they are either images or something else)
            // skip table elements since I don't need them
            if (nextElement.tagName().equals("table")) {
                nextElement = nextElement.nextElementSibling();

            } else {
                // else needed here because I want to skip this part
                // if the above "if" statement is executed and let it go
                // over the loop again to check for id == "Pronunciation"
                // get text from elements (combine all child node/element text)
                etymPartText.add(nextElement.text() + "\n");
                nextElement = nextElement.nextElementSibling();
            }
        }

        String etymTextJoined = removeHeadings(etymPartText);
        JTextArea guiDisplayText = createGUIDisplayText(etymTextJoined);

        this.add(guiDisplayText);
    }

    private Document searchCharacterOnWictionary() {
        String base = "https://en.wiktionary.org/wiki/";
        String finalLink = base + character;

        Document doc = null;
        try {
            doc = Jsoup.connect(finalLink).timeout(0).get(); // timeeout(0) for no time outs (infinite time)  
        } catch (Exception e) {
            System.err.print("error in wikipanel, chinese heading T___T");
            e.printStackTrace();
        }
        return doc;
    }

    private JTextArea createGUIDisplayText(String text) {
        JTextArea guiDisplayText = new JTextArea(text);
        int columnWidth = 50; // arbitrarily (trial and error) chosen 
        guiDisplayText.setColumns(columnWidth);
        guiDisplayText.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        guiDisplayText.setEditable(false);
        guiDisplayText.setLineWrap(true);
        guiDisplayText.setWrapStyleWord(true); // wrap the lines at words (i.e., don't cut words while wrapping)

        return guiDisplayText;
    }

    private String removeHeadings(List<String> textParts) {
        String etymTextJoined = "";

        for (String text : textParts) {
            if (!text.contains("[edit]")) { // ignore Glyph origin[edit] and Etymology[edit]
                etymTextJoined += text;
            }
        }
        return etymTextJoined;
    }
}
