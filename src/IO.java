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
    private String fileName;

    public IO(String fileName) throws IOException {
        this.fileName = fileName;
        myFile = new File("Resources/"+fileName+".txt");
        if(!myFile.exists()){
            myFile.createNewFile();
        }

        writeFile = new FileWriter(fileName);

    }

    public void addToNewFile(String teamName, String[] players) throws IOException {
        writeFile.write(getData + "\n" + teamName + ", " + players);  //TILFØJ MERE
        writeFile.close();
    }

    //Test
    public void readFile() throws FileNotFoundException {
        sc = new Scanner(myFile);
        while (sc.hasNextLine()){
            getData = sc.nextLine() + getData;
        }
        System.out.println("\n" + getData);
    }

    /*
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
        Team[] teams = new Team[4];
        //Match[] matches = new Match[3];
        for (int i = 0; i < text.length; i++) {
            String teamName = text[i][0];
            String[] teamMembers = text[i][1].split("\\|");
            String[] matchText = text[i][2].split("\\|");
            String[][] matches = new String[2][];
            for (int j = 0; j < matchText.length; j++) {
                matches[j] = matchText[j].split("\\.");
            }

            //teams[i] = new Team(teamName);
            for (String teamMember : teamMembers) {
                Player player = new Player(teamMember);
                teams[i].addPlayer(player);
            }
        }
        //for (int i = 0; i < text.length; i++) {
        //    Team team = new Team(teamName);
        //
        //}
        //Tournament tournament = new KnockoutTournament(fileName, teams, matches);
        //return tournament;
    }*/

}
