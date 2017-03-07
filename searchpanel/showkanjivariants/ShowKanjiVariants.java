/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.showkanjivariants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author suraj
 */
public class ShowKanjiVariants {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
//         TODO code application logic here
        Variants variants = new Variants();  // load variants
        Scanner reader = new Scanner(System.in); 
        
        
        System.out.print("Enter character: ");
        char character = reader.nextLine().charAt(0); // chop string to first char
        
        for (Variant variant : variants.getVariantList()) {
            if (variant.getChar() == character) {
                System.out.println(variant.getConversionInfo() + ": " + variant.getConvertedChar());
            }
        }
        
        
        
     
        
    }

}
