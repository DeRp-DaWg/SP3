import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IO {
    private String getData = "";
    private String fileName = "tournament.csv";
    private File myFile = new File(fileName);
    private FileWriter writeFile;

    public IO() throws IOException {
        if(!myFile.exists()){
            myFile.createNewFile();
        }

        writeFile = new FileWriter(fileName);
        Scanner sc = new Scanner(myFile);
        while (sc.hasNextLine()){
            getData = sc.nextLine() + getData;
        }

    }

    public void add(String teamName, String[] teamPlayers) throws IOException {
        writeFile.write(teamName + ", " + teamPlayers);
        writeFile.close();
    }
}
