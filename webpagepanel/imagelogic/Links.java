/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.imagelogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Gives image links only for Chineseetymology.org
 *
 * @author suraj
 */
public class Links {

    private Document pageDOM;  // Document Object Model
    private final String baseLink = "http://chineseetymology.org/CharacterEtymology.aspx?submitButton1=Etymology&characterInput=";

    public Links(String character) {

                try {
                    String pageLink = baseLink + character;
                    pageDOM = Jsoup.connect(pageLink).timeout(0).get(); // timeout(0) to set no timeout limit (infinity)
                    extractImgLinks();

                } catch (IOException e) {
                    System.err.print("IO error desu yo. Shimatta ne.");
                }

        
        System.out.println("image links desu");
    }

    public Set<String> extractImgLinks() {
        //get image link elements ka list by text common to all image links
        Elements imgTags = pageDOM.getElementsByAttributeValueContaining("src", "/CharacterImages"); 
        Set<String> imgLinkList = new HashSet<>(); 
        
        // add links to list
        for (Element imgTag : imgTags) {
            imgLinkList.add(imgTag.absUrl("src")); // get absolute URLs
        }
      
        return imgLinkList;
    }
    
    public String getDescription(){
        Element description = pageDOM.getElementById("etymologyLabel"); // "etymologyLabel" is id of description's html element
        return description.text(); 
    }

}
