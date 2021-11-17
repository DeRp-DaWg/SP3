import java.util.ArrayList;

public class Match {
    private Team[] teams;
    private String matchName;
    private int score;

    public Match(Team[] teams, String matchName) {
        this.teams = teams;
        this.matchName = matchName;
    }

    public Match(Team[] teams, String matchName, int score) {
        this.teams = teams;
        this.matchName = matchName;
        this.score = score;
    }

    public Team[] getTeams() {
        return teams;
    }

    public String getMatchName() {
        return matchName;
    }
}
