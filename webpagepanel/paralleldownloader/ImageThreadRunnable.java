/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.paralleldownloader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author suraj
 */
public class ImageThreadRunnable implements Runnable {

    private String link;

    public ImageThreadRunnable(String link) {
        this.link = link;
    }

    @Override
    public void run() {
        String[] linkSplit = link.split("/");
        String imageId = linkSplit[linkSplit.length - 1];
        imageId = imageId.toUpperCase();

        try {
            URL url = new URL(link);
            BufferedImage img = ImageIO.read(url);
            System.out.println(imageId);

            File outputFile = new File("src/images/" + imageId);
            ImageIO.write(img, "gif", outputFile);
        } catch (Exception e) {

        }
    }

}
