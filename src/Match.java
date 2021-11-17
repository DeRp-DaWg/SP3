import java.util.ArrayList;

public class Match {
    private Team[] teams;
    private String matchName;

    ArrayList<String> myMatch = new ArrayList<>();

    public Match(Team[] teams, String matchName) {
        this.teams = teams;
        this.matchName = matchName;
    }

    public Team[] getTeams() {
        return teams;
    }

    public String getMatchName() {
        return matchName;
    }
}
