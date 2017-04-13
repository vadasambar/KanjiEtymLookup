/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhongwenpanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Image reader and writer for Zhongwenpanel Note: doesn't inherit from Java's
 * ImageIO
 *
 * @author suraj
 */
public class ZhongwenImageIO {
        public static ImageIcon read(String imgLink) throws IOException {
        URL url = new URL(imgLink);
        System.out.println(url + " created...");

        ImageIcon imgIcon = new ImageIcon(url);        
        return imgIcon; 
    }

    private static BufferedImage writeToBufferedImage(Image khariImage) {
        // http://stackoverflow.com/questions/36257318/imageio-read-cant-read-some-images
        // http://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
        // and this..
        // https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
        // create an empty BufferedImage
        BufferedImage img = new BufferedImage(khariImage.getWidth(null), khariImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics g = img.getGraphics();
        // http://stackoverflow.com/questions/10555415/should-i-use-null-for-the-imageobserver-parameter-of-the-graphics-drawimage-fu
        // ImageObserver is null because I don't need it here
        // I am probably drawing the image asynchronously.
        // Check this: http://stackoverflow.com/questions/748175/asynchronous-vs-synchronous-execution-what-does-it-really-mean
        g.drawImage(khariImage, 0, 0, null); // (0, 0) are starting co-ordinates of image drawing

        return img;
    }

    // by default files will be stored in project directory (outside src)
    // by default, images will be stored in "gif" format
    public static void writeTofIle(BufferedImage img, String filePathName) throws IOException {
        File saveImg = new File(filePathName);
        System.out.println("file created ...");

        // write image to file 
        ImageIO.write(img, "gif", saveImg);
        System.out.println("writing image " + filePathName + "...");

    }

}
