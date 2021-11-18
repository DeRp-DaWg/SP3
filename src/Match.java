import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void setTime(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(date);
        System.out.println(currentDate);
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

    public String getDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(this.date);
        return currentDate;
    }

    @Override
    public String toString() {
        String str =
                "===== "+matchName+" =====" + "\n";
        try {
            str +=
                teams[0].getTeamName();
        }
        catch (NullPointerException e) {
            str += "TBA";
        }
        str +=
                " vs ";
        try {
            str +=
                teams[1].getTeamName();
        }
        catch (NullPointerException e) {
            str += "TBA";
        }
        str += "\n";
        if (score != 0) {
            str +=
                "score: " + score + "\n";
        }
        str += date;
        return str;
    }
}
