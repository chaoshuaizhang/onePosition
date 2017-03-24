package cn.shopin.oneposition.util;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zcs on 2017/3/17.
 */

public class FileUtil {
    public static String file2Str(String path) {
        String str = "";
        File file = new File(path);
        if (file.exists()) {
            try {
                Log.d("TTAAGG","文件存在");
                FileInputStream fis = new FileInputStream(file);
                byte[] b = new byte[fis.available()];
                str = new String(b, "utf-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Log.d("TTAAGG","文件不存在");
        }
        return str;
    }
}
