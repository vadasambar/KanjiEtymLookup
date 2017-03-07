/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.showkanjivariants;

/**
 *
 * @author suraj
 */
public class Variant {

    private String lineFromVariantsFile;
    private String charCode;
    private String conversionInfo;
    private String convertedCharCode;

    public Variant(String lineFromVariantsFile) {
        this.lineFromVariantsFile = lineFromVariantsFile;
        assignFieldVariables(lineFromVariantsFile);
    }

    // will work only if the line is from Unihan_Variants.txt or something with similar format 
    private void assignFieldVariables(String line) {
        charCode = line.split("	")[0];  //U+3400	kSemanticVariant	U+4E18
        conversionInfo = line.split("	")[1];
        convertedCharCode = line.split("	")[2];
    }

    public String getCharCode() { // hex char code which is the same as unicode charcode
        return charCode;
    }

    public String getConversionInfo() {
        return conversionInfo;
    }

    public String getConvertedCharCode() {
        return convertedCharCode;
    }  
    
    // get character from unicode
    public char getChar() {
        int hexadecimal = 16; // base/index
        return getDisplayChar(charCode, hexadecimal); 
    }

    // get converted character from its unicode
    public char getConvertedChar() {
        int hexadecimal = 16;
        return getDisplayChar(convertedCharCode, hexadecimal); 
    }
    
    // convert unicode to displayable character
    private char getDisplayChar(String code, int base){
        String hexString = code.substring(2, 6); // U+3434 => 3434 
        
        char ch = (char) Integer.parseInt(hexString, base); // converting from hex/unicode to character
        return ch; 
    }
    
    
    @Override
    public String toString() {
        return getChar() + " is a " + getConversionInfo() + " of " + getConvertedChar() + "\n";
    }

}
