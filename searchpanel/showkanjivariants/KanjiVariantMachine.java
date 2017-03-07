/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.showkanjivariants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author suraj
 */
public class KanjiVariantMachine {
    private Variants variants;

    public KanjiVariantMachine() {
        variants = new Variants(); 
    }
    
    public Map<String, Character> getAllVariants(char kanji){
        Map<String, Character> allVariants = new HashMap<>();
        
        for (Variant variant : variants.getVariantList()) {
            if (variant.getChar() == kanji) {
                allVariants.put(variant.getConversionInfo(), variant.getConvertedChar());
            }
        }
        
        return allVariants; 
    }
    
    
}
