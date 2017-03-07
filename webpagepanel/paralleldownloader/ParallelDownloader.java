/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.paralleldownloader;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 *
 * @author suraj
 */

// check net tab of firebug on chineseetymology.org in firefox. This class is inspired by that. 
// Basically all images downloades start around the same time with slight delays in starting. 
public class ParallelDownloader {

    private Path newDirPath;
    private Set<String> links;

    public ParallelDownloader(Set<String> links) {
        this.links = links;
        this.run();
    }

    public void run() {
        System.out.println("inside run");
        createNewDirectory();

        for (String link : links) {
            new Thread(new ImageThreadRunnable(link)).start();
        }
        
        int seconds = 0; 
        while (!allLinksDownloaded() && seconds < 10) {     
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.print("thread interrupted error inside || downloader");
            }
            
            seconds += 2; 
            System.out.println("Seconds: " + seconds);
        }
        System.out.println("done with for loop inside run() :)");

    }

    private void createNewDirectory() {
        String newDirLocation = System.getProperty("user.dir") + "/src/images";
        newDirPath = Paths.get(newDirLocation);

        try {

            // https://docs.oracle.com/javase/tutorial/essential/io/delete.html 
            // first delete all the files in a directory and only then you can delete the directory. 
            if (Files.exists(newDirPath)) {
                DirectoryStream<Path> dirStream = Files.newDirectoryStream(newDirPath);
                for (Path file : dirStream) {
                    Files.delete(file);
                }
                Files.delete(newDirPath); // delete if already exists and proceed to create new
            }

            Files.createDirectory(newDirPath);
            System.out.println("new dir created");

        } catch (Exception e) {
            System.out.println("dir exception");
        }

    }

    public boolean allLinksDownloaded() {
        double minDownloadPercent = 0.8; // if 80% or more images are downloaded, function will return true
        System.out.println("inside all links downloaded function");
        int count = 0;
        try {
            DirectoryStream<Path> dirStream = Files.newDirectoryStream(newDirPath);

            for (Path filePath : dirStream) {
                if (Files.exists(filePath)) {

                    count++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // branch of VOLATILE
        System.out.println("total links: " + links.size());
        System.out.println("links downloaded: " + count);
        
        // To ensure function returns true if more than minDownloadPercent images are downloaded 
        return count >= minDownloadPercent * links.size(); // minDownloadPercent is set to 80% 
    }

    public Path getPath() {
        return this.newDirPath;
    }

//    public static void main(String[] args) {
//        HashSet<String> links = new HashSet<>(); 
//      
//        // VOLATILE CHECK THIS FIRST
//        try {
//            File file = new File("src/links.txt");
//            Scanner reader = new Scanner(file);
//
//            while (reader.hasNextLine()) {
//                String line = reader.nextLine();
//                links.add(line);
//            }
//            System.out.println("inside main");
//            System.out.println("Size of set: " + links.size());
//            ParallelDownloader p = new ParallelDownloader(links);
//           
//            int count = 0;
//            while (!p.allLinksDownloaded()) {                
//                Thread.sleep(2000);
//                count += 2; 
//                System.out.println("Seconds: " + count);
//            }
//
//        } catch (Exception e) {
//        }
//
//    }

}
