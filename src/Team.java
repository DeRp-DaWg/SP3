import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private List<Player> players = new ArrayList<>();
    private int matchTournamentScore = 0;
    private int goalScore = 0;
    private boolean stillInPlay = true;

    public Team(String teamName, int matchTournamentScore, int goalScore, boolean stillInPlay){
        this.teamName = teamName;
        this.matchTournamentScore = matchTournamentScore;
        this.goalScore = goalScore;
        this.stillInPlay = stillInPlay;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getMatchScore() {
        return matchTournamentScore;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(int playerNr) {
        this.players.remove(playerNr - 1);
    }

    @Override
    public String toString() {
        String teamString = "";
        for (Player player: players) {
            teamString += "Spiller " + player.getPlayerNumber() + " navn: " + player.getName() + ", \n";
        }
        return "Team " + teamName + "\n" + teamString;
    }
}
