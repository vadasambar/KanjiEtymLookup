/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpanel;

import searchpanel.gui.SearchPanel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author suraj
 */
public class ui implements Runnable{
    private JFrame frame; 

    @Override
    public void run() {
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new SearchPanel());
        frame.pack();
        frame.setVisible(true);
    }
    
     public static void main(String[] args) {
         SwingUtilities.invokeLater(new ui());
    }
}
