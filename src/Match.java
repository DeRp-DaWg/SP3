import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Match {
    private Team[] teams = new Team[2];
    private String matchName;
    private int score = 0;
    private Date date;
    private boolean hasSpace = true;

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
            hasSpace = false;
        }
        System.out.println("Team was not added to match.");
    }

    public void setTime(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(date);
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

    public void setScore(int score) {
        this.score = score;
    }

    public String getDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(this.date);
        return currentDate;
    }

    public int findMatchResults() {
        if (score > 0) {
            teams[1].setStillInPlay(false);
            return 1;
        }
        if (score < 0) {
            teams[0].setStillInPlay(false);
            return 2;
        }
        return 0;
    }

    public boolean hasSpace() {
        return hasSpace;
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
            String scoreStr = "";
            if (score > 0) {
                scoreStr += "10-" + Math.abs(score-10);
            }
            else {
                scoreStr += Math.abs(10+score) + "-10";
            }

            str +=
                "score: " + scoreStr + "\n";
        }
        str += date;
        return str;
    }
}
