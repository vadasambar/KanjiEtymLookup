/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author suraj
 */
// http://docs.oracle.com/javase/tutorial/uiswing/components/generaltext.html#filter
// http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextComponentDemoProject/src/components/DocumentSizeFilter.java
// https://docs.oracle.com/javase/8/docs/api/javax/swing/text/DocumentFilter.html
public class DocumentCharLimitFilter extends DocumentFilter {

    private int MAX_CHAR_LIMIT;

    public DocumentCharLimitFilter(int maxChars) {
        this.MAX_CHAR_LIMIT = maxChars;
    }

    // You don't need insertString() when entering text in JTextField. 
    // Check this: http://stackoverflow.com/questions/23525441/documentfilter-why-is-replace-invoked-and-not-insertstring
    // and this too: http://stackoverflow.com/questions/6844848/documentfilter-insert-never-called
    // what this method doesn't do, you can't replaces a single character selection with a longer string and it won't be truncated
    // unless you select the entire oldText string. In other words, you cant replace characters anywhere inside the textbook
    // with more characters or longer strict. Number of characters has to be equal or less when only a part of the oldText (textField text) 
    // is selected
    @Override
    public void replace(FilterBypass fb, int offset, int length, String newText, AttributeSet aSet) throws BadLocationException {
        System.out.println(fb.getClass());
        int oldTextLength = fb.getDocument().getLength();
        String oldText = fb.getDocument().getText(0, oldTextLength);
        int lengthOfOldTextMinusSelection = oldText.length() - length;

        // check this http://stackoverflow.com/questions/42512743/pasting-replacing-a-character-using-mouse-selection-in-java-textfield-with-spec?noredirect=1#comment72167562_42512743
        // and this (replace() especially) http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextComponentDemoProject/src/components/DocumentSizeFilter.java
        // to ensure that the characters selected are not counted twice (basically to remove them from oldText)
        if ((lengthOfOldTextMinusSelection + newText.length()) <= MAX_CHAR_LIMIT) {
            super.replace(fb, offset, length, newText, aSet);

            // if entire oldtext is selected, replaced newText should be truncated to MAX_CHAR_LIMIT if it exceeds MAX_CHAR_LIMIT            
        } else if (oldTextLength == length) {
            String toReplace = newText;
            if (newText.length() > MAX_CHAR_LIMIT) {
                toReplace = newText.substring(0, MAX_CHAR_LIMIT);
            }
            super.replace(fb, offset, length, toReplace, aSet);

        } else {
            System.out.println("Entered characters exceed specified limit of " + MAX_CHAR_LIMIT + ". Dame desu yo >_<*");
        }
    }
}
