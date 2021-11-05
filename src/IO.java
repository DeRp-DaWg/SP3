import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IO {
    private String getData = "";
    private FileWriter writeFile;
    private File myFile;
    private Scanner sc;

    public IO(String fileName) throws IOException {
        File myFile = new File("Resources/"+fileName);
        if(!myFile.exists()){
            myFile.createNewFile();
        }

        writeFile = new FileWriter(fileName);
        sc = new Scanner(myFile);
        //while (sc.hasNextLine()){
        //    getData = sc.nextLine() + getData;
        //}
    }

    public void addToFile(String teamName, String[] players) throws IOException {
        writeFile.write(getData + "\n" + teamName + ", " + players);  //TILFØJ MERE
        writeFile.close();
    }

    public void readFromFile() {
        String[] lines = new String[4];
        int count = 0;
        while (sc.hasNextLine()){
            lines[count] = sc.nextLine();
            count++;
        }
        //for (String line : lines) {
        //    System.out.println(line);
        //}
        String[][] text = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            text[i] = lines[i].split(",");
        }
        for (String[] string : text) {
            String teamName = string[0];
            String[] teamMembers = string[1].split("\\|");
            String[] matchText = string[2].split("\\|");
            for (String match : matchText) {
                String[] test = match.split("\\.");
            }
            for (int i = 0; i < matchText.length; i++) {
                System.out.print(matchText[i]);
            }
        }
    }
}
