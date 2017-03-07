/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.imagelogic;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 * special background thread for downloading images 
 * @author suraj
 */
// https://docs.oracle.com/javase/tutorial/uiswing/concurrency/simple.html
public class ImageThread extends SwingWorker<ImageIcon, Void>{
    private String link; 

    public ImageThread(String link) {
        this.link = link; 
    }   
    

    @Override
    protected ImageIcon doInBackground() throws Exception {
        return new ImageIcon(new URL(link));
    }
    
}
