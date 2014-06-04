package serwisy;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lubo on 02.06.14.
 */
public class FileReadingService {
    Context mContext;
    public FileReadingService(Context c) {
        mContext = c;
    }

    public String[] getFileList() {
        File folder = new File(mContext.getFilesDir().toString());

        ArrayList<String> fileNames = new ArrayList<String>();
        File[] files = folder.listFiles();
        for(File f : files) {
            if(f.isFile())
                fileNames.add(f.getName());
        }

        return fileNames.toArray(new String[fileNames.size()]);
    }

    public String readFile(String fileName) throws IOException {
        String pathToFile = mContext.getFilesDir() + "/" + fileName;
        FileInputStream fos = mContext.openFileInput(pathToFile);

        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        while(fos.read(buffer, 0, 1024) > 0) {
            sb.append(new String(buffer));
        }

        return sb.toString();
    }
}
