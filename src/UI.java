import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    String teamName = "";
    Scanner sc;
    String[] playerNames;
    ArrayList<Player> players = new ArrayList<>();
    Player player;
    Team team;
    IO io = new IO("TournamentTestFile.csv");

    public UI() throws IOException {
        String[] players = {"Rehman", "Jens"};
        createPlayers();
    }

    public void createPlayers(){
        sc = new Scanner(System.in);

        //Get teamname
        System.out.println("Holdnavn: ");
        teamName = sc.nextLine();

        //Get players
        System.out.println("Indtast spillernavne separeret af et komma, fx Ole, Abdi, Hans:");
        playerNames = sc.nextLine().split(",");

        team = new Team(teamName);

        for (String playerName : playerNames) {
            player = new Player(playerName);
            team.addPlayer(player);
            //io.addToFile(teamName, players); Du skal ændre players her, i parameteren i IO filen til at være arraylist
        }
    }

    public void announce(){

    }
}
