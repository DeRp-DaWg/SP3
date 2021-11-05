import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    String teamName = "";
    Scanner sc;

    public UI(){
        String[] players = {"Rehman", "Jens"};
        //Team team = new Team("TeamA", players);
    }

    public ArrayList<Player> createPlayers(String... playerNames){
        sc = new Scanner(System.in);

        //Get teamname
        System.out.println("Holdnavn: ");
        teamName = sc.nextLine();

        //Get players
        System.out.println("Indtast spillernavne separeret af et komma, fx Ole, Abdi, Hans:");


        ArrayList<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            players.add(player);
        }
        return players;
    }
}
