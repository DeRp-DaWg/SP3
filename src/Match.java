import java.util.ArrayList;

public class Match {
    private Team[] teams;
    private int points;
    ArrayList<String> addMatch = new ArrayList<>();

    public Match(Team[] teams) {
        this.teams = teams;
    }

    public void addPoints() {
        points++;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    public int getPoints() {
        return points;
    }

    public void createMatches(Team team){
        String makeMatch = team + "," + team;
        addMatch.add(makeMatch);
    }
}
