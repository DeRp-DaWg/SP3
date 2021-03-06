import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private List<String> players = new ArrayList<>();
    private int matchTournamentScore = 0;
    private int goalScore = 0;
    private boolean stillInPlay = true;

    public Team(String teamName, int matchTournamentScore, int goalScore, boolean stillInPlay){
        this.teamName = teamName;
        this.matchTournamentScore = matchTournamentScore;
        this.goalScore = goalScore;
        this.stillInPlay = stillInPlay;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addPlayer(String player) {
        players.add(player);
    }

    public void removePlayer(int playerNr) {
        this.players.remove(playerNr - 1);
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getMatchTournamentScore() {
        return matchTournamentScore;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public boolean isStillInPlay() {
        return stillInPlay;
    }

    public void setStillInPlay(boolean stillInPlay) {
        this.stillInPlay = stillInPlay;
    }

    @Override
    public String toString() {
        String teamString = "";
        for (String player: players) {
            teamString += "Spiller: " + player + ", \n";
        }
        return "Team " + teamName + "\n" + teamString;
    }
}
