package serwisy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lubo on 02.06.14.
 */
public class FileReadingService {
    Context mContext;
    File mainFolder;

    public FileReadingService(Context c) {
        mContext = c;
        mainFolder = new File(Environment.getExternalStorageDirectory(), "/trainer/");
    }

    public String[] getFileList() throws FileNotFoundException {
        if(!mainFolder.exists()) {
            throw new FileNotFoundException("Plik nie został znaleziony");
        }

        ArrayList<String> fileNames = new ArrayList<String>();
        File[] files = mainFolder.listFiles();
        for(File f : files) {
            if(f.isDirectory())
                fileNames.add(f.getName());
        }

        return fileNames.toArray(new String[fileNames.size()]);
    }

    public String readFile(String fileName) throws IOException {
        File sdCard = Environment.getExternalStorageDirectory();
        File trainingFile = new File(sdCard, "/trainer/" + fileName + "/training.csv");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(trainingFile));

        String line;
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line).append("\r\n");
        }

        return stringBuffer.toString();
    }

    public Drawable loadImage(String imageLocation) throws FileNotFoundException {
        File file = new File(mainFolder, imageLocation);
        return Drawable.createFromPath(file.toString());
    }
}
