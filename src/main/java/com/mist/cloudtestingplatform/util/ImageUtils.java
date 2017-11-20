package com.mist.cloudtestingplatform.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * Created by Prophet on 2017/11/20.
 */
public class ImageUtils {

    public static void compressImage(File file) throws IOException {
        Thumbnails.of(file).scale(0.8f).outputQuality(0.5f).toFile(file);
    }

    public static void compressImage(File file, long target) throws IOException {
        while (file.length() > target) {
            Thumbnails.of(file).scale(0.8f).outputQuality(0.5f).toFile(file);
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:/Pictures/images/pcie6320.jpg");
        System.out.println(file.length());
        compressImage(file, 65 * 1000);
        System.out.println(file.length());
        System.out.println(file.getPath());
    }

}
