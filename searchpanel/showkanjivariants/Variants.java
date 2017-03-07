/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.showkanjivariants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suraj
 */
public class Variants {

    private BufferedReader reader;
    private char ch;
    private char variant;
    private String info;
    private List<Variant> variants;

    public Variants() {
        variants = new ArrayList<Variant>();
        try {
            reader = new BufferedReader(new FileReader("src/searchpanel/Unihan_Variants.txt"));
            readFrom(reader);

        } catch (IOException e) {
            System.err.print("Some error. Either unihan file not found or something else ;(");
            e.printStackTrace();

        }
    }

    private void readFrom(BufferedReader reader) throws IOException {
        String line = "";

        while (((line = reader.readLine()) != null)) { 
            
            // http://stackoverflow.com/questions/41781442/two-conditions-in-while-with-buffered-reader
            if (line.startsWith("U")) { // make sure only lines starting with 'U' are read 
                Variant variant = new Variant(line);
                variants.add(variant);
            }
            
        }
    }
    
    
    //  CHECK THIS
    @Override
    public String toString() {
        return variants.toString(); // prints a line that's too long and takes a lot of time. 
    }
    
    public List<Variant> getVariantList(){
        return variants; 
    }

}
