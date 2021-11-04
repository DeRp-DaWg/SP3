import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IO {
    private String getData = "";

    public IO() throws IOException {
        String fileName = "tournament.txt";
        File myFile = new File(fileName);
        if(!myFile.exists()){
            myFile.createNewFile();
        }

        FileWriter writeFile = new FileWriter(fileName);

        Scanner sc = new Scanner(myFile);
        while (sc.hasNextLine()){
            getData = sc.nextLine() + getData;
        }

        writeFile.write("");
        writeFile.close();
    }
}
