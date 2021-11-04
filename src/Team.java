import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Player> players = new ArrayList<Player>();

    public Team(String teamName, ArrayList<Player> players){
        this.teamName = teamName;
        this.players = players;
    }

    public void AddPlayer(Player player) {
        this.players.add(player);
    }

    public void RemovePlayer(int playerNr) {
        this.players.remove(playerNr - 1);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", players=" + players +
                '}';
    }
}
