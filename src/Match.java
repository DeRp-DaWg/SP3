import java.util.ArrayList;

public class Match {
    private Team[] teams;

    ArrayList<String> myMatch = new ArrayList<>();

    public Match(Team[] teams) {
        this.teams = teams;
    }

    public ArrayList<String> createMatches(Team team){
        String makeMatch = team + "," + team;
        myMatch.add(makeMatch);
        return myMatch;
    }
}
