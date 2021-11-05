import java.util.ArrayList;

public class Match {
    private Team[] teams;
    private int points;
    ArrayList<String> myMatch = new ArrayList<>();

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

    public ArrayList<String> createMatches(Team team){
        String makeMatch = team + "," + team;
        myMatch.add(makeMatch);
        return myMatch;
    }
}
