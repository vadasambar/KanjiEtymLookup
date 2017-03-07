/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webpagepanel.imagelogic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import webpagepanel.enums.VariantType;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Takes all image links and converts them into VariantImage Maps Variant era
 * (Oracle, Bronze etc.,) to VariantImage Returns group of images according to
 * era.
 *
 * @author suraj Parameters: ImageLinks (extended List of links) object
 */
public class VariantImageGroup {

    private Path imgDirPath;
    private Map<VariantType, List<VariantImage>> typeMappedToVariantImages;
    private VariantType type;

    public VariantImageGroup(Path imgDirPath) {
        this.imgDirPath = imgDirPath;
        typeMappedToVariantImages = new HashMap<>();
        createMapping();
        System.out.println("variant iamge group desu");

    }


    private void createMapping() {
        DirectoryStream<Path> pathStream = null; 
        try {
            pathStream = Files.newDirectoryStream(imgDirPath);

        } catch (IOException e) {
        }

        for (Path file : pathStream) {
            String fileLocation = file.toString();
            VariantImage variantImg = new VariantImage(fileLocation);
            VariantType variantType = variantImg.getType();

            if (!typeMappedToVariantImages.containsKey(variantType)) {
                typeMappedToVariantImages.put(variantType, new ArrayList<>());

            }
            typeMappedToVariantImages.get(variantType).add(variantImg);
        }
    }

    public List<VariantImage> getOracle() {
        return typeMappedToVariantImages.get(type.ORACLE);
    }

    public List<VariantImage> getLSTSeal() {
        return typeMappedToVariantImages.get(type.LST_SEAL);
    }

    public List<VariantImage> getSeal() {
        return typeMappedToVariantImages.get(type.SEAL);
    }

    public List<VariantImage> getBronze() {
        return typeMappedToVariantImages.get(type.BRONZE);
    }

}
