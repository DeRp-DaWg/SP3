import java.util.ArrayList;

public class UI {
    public UI(){
        String[] players = {"Rehman", "Jens"};
        //Team team = new Team("TeamA", players);
    }

    public static ArrayList<Player> createPlayers(String... playerNames) {
        ArrayList<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }
}
