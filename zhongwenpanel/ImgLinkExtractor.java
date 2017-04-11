/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhongwenpanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 * Extract img links from Jsoup Document passed as an argument
 *
 ** @author suraj
 */
public class ImgLinkExtractor {
    private Document doc;

    public ImgLinkExtractor(Document doc) {
        this.doc = doc;
    }

    public List<String> getImgLinks() throws IOException {
        List<Node> frameNodes = getFrameNodesFromDoc();

//        System.out.println("frame nodes: " + frameNodes);
//        System.out.println("/-------------------------------------------/");
        List<String> imgLinks = new ArrayList<>();

        for (Node frameNode : frameNodes) {
            String imgLink = extractImgLinkOutOfFrameNode(frameNode);
            imgLinks.add(imgLink);
        }

        int NON_IMAGE_LINK = 2; // third link does not return image (it's not image)
        imgLinks.remove(NON_IMAGE_LINK);

        return imgLinks;
    }

    private List<Node> getFrameNodesFromDoc() throws IOException {            

        // get the top frameset element
        Element frameSet = doc.select("frameset").first(); // contains all the frames
        List<Node> frameNodes = new ArrayList<>();

        for (Node node : frameSet.childNodes()) {

            if (node.toString().contains("SRC")) { // nodes with SRC i.e., links
                frameNodes.add(node);
            }
        }

        return frameNodes;
    }

    private String extractImgLinkOutOfFrameNode(Node frameNode) {
        char quote = '"';
        String regex = quote + ""; // regex = "; 

        // <frame SRC="../../d/171/d67.htm" NAME="f3"> => ../../d/171/d67.htm 
        String linkPart = frameNode.toString().split(regex)[1];

        // ../../d/171/d67.htm => ../../d/171/d67
        String dotHtmRemoved = linkPart.split("\\.[a-z]")[0]; // pattern = .[a-z]

        String addDotGif = dotHtmRemoved + ".gif";
        String imgLink = addDotGif.replace("../..", "http://zhongwen.com");

        return imgLink;
    }

}
