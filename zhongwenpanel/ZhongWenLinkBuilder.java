/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhongwenpanel;

import java.nio.charset.Charset;
import javax.xml.bind.DatatypeConverter;

/**
 * Construct a final link from big5Character hex and ascii
 *
 * @author suraj
 */
public class ZhongWenLinkBuilder {
    private final String BASE_LINK = "http://zhongwen.com/cgi-bin/zipux.cgi?=%";
    private final int LENGTH_OF_BIG5_HEX = 4;
    private String character;

    public ZhongWenLinkBuilder(String character) {
        this.character = character;
    }

    public String getFinalLink() {
        Big5Character big5 = new Big5Character(character);

        String alphabetFoundInASCIIForm = getAlphabetFromCharInASCII(big5.asciiCode());

        // first part of big5HEx = AB (if it was AB43)
        // String firstPartofBig5Hex = big5HexCode.substring(0, 2);
        // String secondPartOfBig5Hex = big5HexCode.substring(2, big5HexCode.length());
        // final GET link. I have to send modified big5HexCode to this link
        String finalLink = constructFinalLink(big5.hexCode(), alphabetFoundInASCIIForm);

        System.out.println("second part added to final link: " + finalLink);
        return finalLink;

    }

    // if charInASCII has an alphabet, big5hex is modified, 
    // e.g., big5hex = AB43
    // charinASCII = «C  (43 is converted into alphabet here)
    // so, midified big5hex AB48 => ABC
    private String getAlphabetFromCharInASCII(String charInASCII) {
        String foundAlphabet = null;

        for (char c : charInASCII.toCharArray()) {
            // check to see if the character is an alphabet
            // https://docs.oracle.com/javase/tutorial/i18n/text/charintro.html
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                System.out.println("Found alphabet: " + c);
                foundAlphabet = "" + c;
            }
        }
        return foundAlphabet;
    }

    private String constructFinalLink(String big5HexCode, String alphabetFoundInASCIIForm) {
        // first part of big5HEx = AB (if it was AB43)        
        String firstPartofBig5Hex = big5HexCode.substring(0, 2);
        // second part of big5Hex = 43 (if it was AB43)
        String secondPartOfBig5Hex = big5HexCode.substring(2, big5HexCode.length());

        // http://.../zipux.cgi?=%AB
        String firstPartOfFinalLink = getFirstPartOfFinalLink(firstPartofBig5Hex);
        // C
        String secondPartOfFinalLink = getSecondPartOfFinalLink(secondPartOfBig5Hex, alphabetFoundInASCIIForm);

        // http://.../zipux.cgi?=%ABC
        String finalLink = firstPartOfFinalLink + secondPartOfFinalLink;
        return finalLink;
    }

    private String getFirstPartOfFinalLink(String firstPartofBig5Hex) {
        String firstPartOfFinalLink = BASE_LINK + firstPartofBig5Hex; // add first part of big5Hex
        return firstPartOfFinalLink;
    }

    private String getSecondPartOfFinalLink(String secondPartOfBig5Hex, String alphabetFoundInASCIIForm) {
        String secondPartOfFinalLink = "";

        // modify secondPart of Big5Hex and add it to the final link
        if (alphabetFoundInASCIIForm != null) {
            // %AB43 => %ABC
            secondPartOfBig5Hex = alphabetFoundInASCIIForm;
            secondPartOfFinalLink = secondPartOfBig5Hex;

            return secondPartOfFinalLink;
        }

        // %A6E5 => %A6%E5
        secondPartOfFinalLink = "%" + secondPartOfBig5Hex; // second % of .../../zipux.cgi?=%A6%E5
        return secondPartOfFinalLink;
    }

    // use this for debugging
    private boolean big5HexLookGood(String big5Hex) {
        if (big5Hex.length() == LENGTH_OF_BIG5_HEX) { // make sure the code is only 4 chars long AE43 or A6AE for example
            return true;
        }
        return false;
    }

}
