import java.util.ArrayList;

public class Match {
    private Team[] teams = new Team[2];
    private String matchName;
    private int score;

    public Match(String matchName) {
        this.matchName = matchName;
    }

    public Match(Team[] teams, String matchName) {
        this.teams = teams;
        this.matchName = matchName;
    }

    public Match(Team[] teams, String matchName, int score) {
        this.teams = teams;
        this.matchName = matchName;
        this.score = score;
    }

    public void addTeam(Team team) {
        for (int i = 0; i < teams.length; i++) {
            if (teams[i] == null) {
                teams[i] = team;
                return;
            }
        }
        System.out.println("Team was not added to match.");
    }

    public Team[] getTeams() {
        return teams;
    }

    public String getMatchName() {
        return matchName;
    }

    public int getScore() {
        return score;
    }
}
