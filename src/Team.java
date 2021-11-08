import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int matchScore = 0;
    private int goalScore = 0;
    private boolean lostLastMatch = false;

    public Team(String teamName, int matchScore, int goalScore, boolean lostLastMatch){
        this.teamName = teamName;
        this.matchScore = matchScore;
        this.goalScore = goalScore;
        this.lostLastMatch = lostLastMatch;
    }

    // Den her metode behøver kun at være her midlertidigt for at gøre det nemmere at teste programmet.
    // Undgå at bruge den for meget og brug AddPlayer() i stedet.
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getMatchScore() {
        return matchScore;
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
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", players=" + players +
                '}';
    }
}
