/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhongwenpanel;

import java.nio.charset.Charset;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author suraj
 */
public class Big5Character {

    private byte[] byteData;
    private String character;

    public Big5Character(String character) {
        this.character = character;
        // big5 is a part of extended Charset. You don't get it in standard charset.
        byteData = character.getBytes(Charset.forName("Big5")); // http://docs.oracle.com/javase/1.5.0/docs/guide/intl/encoding.doc.html
    }

    public String hexCode() {
        String hexCode = DatatypeConverter.printHexBinary(byteData); // http://stackoverflow.com/a/21992128/6874596
        return hexCode;
    }    

    // convert from big5 to ASCII using big5's byteData
    // AB43 (big5 hex) => «C (ascii, used for making zhongwen links)
    public String asciiCode() {
        String asciiCode = new String(byteData, Charset.forName("US-ASCII"));
        return asciiCode;
    }

    public byte[] byteData() {
        return byteData;
    }

    @Override
    public String toString() {
        return character;
    }

}
