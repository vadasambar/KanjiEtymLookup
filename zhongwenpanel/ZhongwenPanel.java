/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhongwenpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author suraj
 */
public class ZhongwenPanel extends JPanel {
    private String character;
  
    public void search(String character){
        this.removeAll();
        this.character = character; 
        
        this.setLayout(new GridLayout(0, 2));
        this.setPreferredSize(new Dimension(600, 800));
        ZhongwenPanelBackend zhongwen = new ZhongwenPanelBackend(character);

        JScrollPane etymDesScroller = new JScrollPane(zhongwen.etymDescription());
        etymDesScroller.getVerticalScrollBar().setUnitIncrement(20);

        JScrollPane etymTreeScroller = new JScrollPane(zhongwen.etymTree());
        // increase scrolling speed aka make each scroll move larger amounts of screen
        // refer: http://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
        etymTreeScroller.getVerticalScrollBar().setUnitIncrement(20);

        this.add(etymDesScroller);
        this.add(etymTreeScroller);

//        frame.pack();
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new ZhongwenPanel());
//    }
}
