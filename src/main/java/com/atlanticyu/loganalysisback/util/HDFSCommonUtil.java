package com.atlanticyu.loganalysisback.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.URI;

public class HDFSCommonUtil {
    public static void copyfileToHdfs(Configuration config, String url, File file)
    {
        try {
            FileSystem fs = FileSystem.get(URI.create(url), config);
            Path src = new Path(file.getPath());
            // 要上传到hdfs的目标路径
            //Path dst = new Path(url+"/test");
            Path dst = new Path(url);
            fs.copyFromLocalFile(src, dst);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[30000];
            while ((bytesRead = ins.read(buffer, 0, 30000)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static public File MultipartFileToFile(MultipartFile file)
    {
        File f = null;
        try {

            if (file.equals("") || file.getSize() <= 0) {
                file = null;
            } else {
                InputStream ins = file.getInputStream();
                f = new File(file.getOriginalFilename());
                inputStreamToFile(ins, f);
            }
            return f;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return f;
        }
    }
}
