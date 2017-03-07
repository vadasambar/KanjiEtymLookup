package webpagepanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import webpagepanel.erapanels.BronzePanel;
import webpagepanel.erapanels.LSTSealPanel;
import webpagepanel.erapanels.OraclePanel;
import webpagepanel.erapanels.SealPanel;
import webpagepanel.imagelogic.Links;
import webpagepanel.imagelogic.VariantImageGroup;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import webpagepanel.paralleldownloader.ParallelDownloader;

/**
 *
 * @author suraj
 */
public class ChineseEtymPanel extends JPanel {

    private String character;
    private Links links;
    private JLabel description;
    private SealPanel seal;
    private LSTSealPanel LSTSeal;
    private BronzePanel bronze;
    private OraclePanel oracle;
    
    
    public ChineseEtymPanel() {
        this.setLayout(new GridLayout()); // http://stackoverflow.com/questions/11009773/jscrollpane-does-not-appear-when-using-it-on-a-jpanel
        System.out.println("etym panel desu");
//        this.character = character;
//        createComponents();
//        addComponents();
    }

    public JPanel getEtymology(String character) {
        this.removeAll();
        this.character = character;
        createComponents();
        addComponents();
        this.revalidate();
        this.repaint();
        return this; 
    }

    private void createComponents() {
        links = new Links(character);

        description = new JLabel(links.getDescription());
        description.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        description.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // add spaces at top and bottom for aesthetic reasons

        createSubPanels();
    }

    private void createSubPanels() {
//        Long currentTime = System.currentTimeMillis() / 1000;
        Set<String> imgLinks = links.extractImgLinks();
        ParallelDownloader imgDownloader = new ParallelDownloader(imgLinks);
        Path imgDirPath = imgDownloader.getPath();
        System.out.println(imgDirPath.toString());

        VariantImageGroup variantImages = new VariantImageGroup(imgDirPath);

        seal = new SealPanel(variantImages);
        LSTSeal = new LSTSealPanel(variantImages);
        bronze = new BronzePanel(variantImages);
        oracle = new OraclePanel(variantImages);
    }

    private void addComponents() {
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));

        outerPanel.add(description);
        outerPanel.add(seal);
        outerPanel.add(LSTSeal);
        outerPanel.add(bronze);
        outerPanel.add(oracle);

        JScrollPane scroller = new JScrollPane(outerPanel);
        scroller.getVerticalScrollBar().setUnitIncrement(20); // to speed up scrolling 
        // http://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
        this.add(scroller);

    }

}
