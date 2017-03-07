/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.erapanels;

import java.awt.Dimension;
import webpagepanel.erapanels.EraPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import webpagepanel.imagelogic.VariantImageGroup;
import webpagepanel.enums.VariantType;

/**
 *
 * @author suraj
 */
public class LSTSealPanel extends EraPanel {

    public LSTSealPanel(VariantImageGroup allVariants) {
        super(allVariants.getLSTSeal(), VariantType.LST_SEAL);

    }

}
