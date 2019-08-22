package com.music.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 羊 on 2018/9/24.
 */
public class MyFileUtils {
    public static String fileUpload(MultipartFile file,String DIR,String URL){
        String oldFileName = file.getOriginalFilename();
//        String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis()+"&&"+oldFileName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(DIR, newFileName));
//            return "{\"error\":0,\"url\":\"" + URL + newFileName + "\"}";
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\":1}";
        }
    }


}
