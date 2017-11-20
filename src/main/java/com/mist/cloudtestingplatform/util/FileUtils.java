package com.mist.cloudtestingplatform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Prophet on 2017/11/20.
 */
public class FileUtils {

    public static byte[] bytesFromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

}
