package fc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    // load cookie text file
    private List<String> textList = new ArrayList<String>();
    private File userfile;

    public void setUserFile(String fileName) {
        this.userfile = new File(
                "./" + fileName);
    }

    public String getText() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(userfile));

        // Add text f rom file to a list
        String itemsInFile;
        while ((itemsInFile = br.readLine()) != null) {
            this.textList.add(itemsInFile);
        }
        // random select text and return
        int min = 0;
        int max = textList.size() - 1;
        Random randomNum = new Random();
        int index = min + randomNum.nextInt(max);
        return textList.get(index);
    }
}
