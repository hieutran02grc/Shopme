package com.shopme.admin;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AmazonS3UtilTests {

    @Test
    public void testListFolder(){
        String folderName = "user-photos/10";
        AmazonS3Util.listFolder(folderName);
    }

    @Test
    public void testUploadFile() throws FileNotFoundException {
        String folderName = "test-upload";
        String fileName = "check.txt";
        String filePath = "C:\\Users\\hieut\\OneDrive\\Pictures\\memes\\" + fileName;

        InputStream inputStream = new FileInputStream(filePath);

        AmazonS3Util.uploadFile(folderName, fileName, inputStream);
    }

    @Test
    public void testDeleteFile(){
        String fileName = "test-upload/check.txt";
        AmazonS3Util.deleteFile(fileName);
    }

    @Test
    public void testRemoveFolder(){
        String folderName = "test-upload";
        AmazonS3Util.removeFolder(folderName);
    }
}
