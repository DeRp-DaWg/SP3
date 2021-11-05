import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    String teamName = "";
    Scanner sc;
    String[] playerNames;
    ArrayList<Player> players = new ArrayList<>();
    Player player;

    public UI(){
        String[] players = {"Rehman", "Jens"};
        //Team team = new Team("TeamA", players);
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

        Team team = new Team(teamName);

        for (String playerName : playerNames) {
            player = new Player(playerName);
            team.addPlayer(player);
        }

    }
}
