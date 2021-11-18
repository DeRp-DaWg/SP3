import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Match {
    private Team[] teams = new Team[2];
    private String matchName;
    private int score;
    private Date date;

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

    public void setTime(String Dateinput) throws ParseException {
        String tempDate = Dateinput;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = format.parse(tempDate);
        this.date = date;
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
