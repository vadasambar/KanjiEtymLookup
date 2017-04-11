/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabbedwindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import searchpanel.gui.SearchPanel;
import webpagepanel.ChineseEtymPanel; 
import zhongwenpanel.ZhongwenPanel;
/**
 *
 * @author suraj
 */
public class KanjiEtymLookup implements Runnable {

    /**
     * @param args the command line arguments
     */
    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("Kanji Etymology Lookup");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane(); 
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        
        JTabbedPane tabber = new JTabbedPane();
        ImageIcon icon = new ImageIcon("src/sadness.png"); // resized image from the internet  
        
        SearchPanel searchPanel = new SearchPanel();
        ChineseEtymPanel etymPanel = new ChineseEtymPanel();
        ZhongwenPanel zhongwen = new ZhongwenPanel();
        searchPanel.setSearchButtonListener(etymPanel, zhongwen);
        
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("Tab 二"));

        JPanel tab3 = new JPanel();
        tab3.add(new JLabel("Tab 三"));
        tab3.setPreferredSize(new Dimension(500, 400));

        tabber.addTab("Tab 一", icon, etymPanel);
        tabber.addTab("Tab 二", icon, zhongwen);
        tabber.addTab("Tab 三", icon, tab3);
        
        contentPane.add(searchPanel);
        contentPane.add(tabber);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new KanjiEtymLookup());
    }

}
