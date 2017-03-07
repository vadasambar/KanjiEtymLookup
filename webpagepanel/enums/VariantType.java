/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.enums;

/**
 *
 * @author suraj
 */

// http://stackoverflow.com/questions/3978654/best-way-to-create-enum-of-strings
public enum VariantType {
    LST_SEAL("LST Seal"), SEAL("Seal"), BRONZE("Bronze"), ORACLE("Oracle");
    
    private final String text; 

    private VariantType(final String text) {
        this.text = text; 
    }
    
    @Override
    public String toString(){
        return text; 
    }
}
