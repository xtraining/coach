package com.zhiqin.coach.admin.util;

/**
 * Company:福建邮科
 * Title:
 * Description: 
 * @author ilikeido
 * @version 1.0
 * @createtime 2009-8-18
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtils {

	public static File download(String url, String fileName) throws IOException { 
		String path = null;
		String filePath = Config.getProperty("download_file_path");
		URL theURL = new URL(url);
        URLConnection con = theURL.openConnection();   
        if (fileName != null) {   
            byte[] buffer = new byte[4 * 1024];   
            int read;   
            path = filePath + File.separator + fileName;   
            File fileFolder = new File(filePath);   
            if(!fileFolder.exists()){   
                fileFolder.mkdir();   
            }   
            InputStream in = con.getInputStream();   
            FileOutputStream os = new FileOutputStream(path);   
            while ((read = in.read(buffer)) > 0) {   
                os.write(buffer, 0, read);   
            }   
            os.close();   
            in.close();   
        }  
        return new File(path);
    }  
	
	public static void main(String[] args) throws IOException {
		download("http://fdfs.xmcdn.com/group4/M07/61/E2/wKgDs1Q6sArjc1UKABuZgWj2jn0241.mp3", "a.mp3");
	}

}