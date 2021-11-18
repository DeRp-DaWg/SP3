import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class KnockoutTournament extends Tournament {
    ArrayList<Match> matches = getMatches();
    Team[] teams = getTeams();

    public KnockoutTournament(String tournamentName, Team[] teams) {
        super(tournamentName, teams);
    }

    public KnockoutTournament(String tournamentName, Team[] teams, ArrayList<Match> matches) {
        super(tournamentName, teams);
        matches = matches;
    }

    // For use in determineWinnerTeamOutcome() method
    public int winnerTeamNewTotalScore(int winnerTeamCurrentScore) {
        return winnerTeamCurrentScore + 2;
    }

    // For use in determineLoserTeamOutcome() method
    public int loserTeamGoalScore(int winnerTeamMatchScore, int loserTeamMatchScore) {
        int goalScore = winnerTeamMatchScore - loserTeamMatchScore;
        return goalScore;
    }

    public void determineWinnerTeamOutcome(int team1number, int team2number, boolean team1Won, int team1CurrentScore, int team2CurrentScore) {
        if (team1Won) {
            winnerTeamNewTotalScore(team1CurrentScore);
            matches.remove(team2number);
        } else {
            winnerTeamNewTotalScore(team2CurrentScore);
            matches.remove(team1number);
        }
    }

    public void determineLoserTeamOutcome(int team1number, int team2number, boolean team2Lost, int team1GoalScore, int team2GoalScore) {
        if (team2Lost) {
            loserTeamGoalScore(team1GoalScore, team2GoalScore);
            matches.remove(team2number);
        } else {
            loserTeamGoalScore(team2GoalScore, team1GoalScore);
            matches.remove(team1number);
        }
    }

    @Override
    public void ArrangeMatches() {
        Date date = new Date();
        String[] matchNames = {"Eight-finals", "Quarterfinals", "Semifinals", "Final"};
        int matchCount = 8;
        int iteration = 0;
        long time = 0;
        long hours = 2;
        while (matchCount != 1) {
            for (int i = 1; i < matchCount+1; i++) {
                Match match = new Match(matchNames[iteration]+i);
                time = date.getTime();
                time += 1000*60*60*hours;
                date.setTime(time);
                try {
                    match.setTime(date);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                matches.add(match);
            }
            iteration++;
            matchCount /= 2;
        }
        Match match = new Match(matchNames[matchNames.length-1]);
        time = date.getTime();
        time += 1000*60*60*hours;
        date.setTime(time);
        try {
            match.setTime(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        matches.add(match);

        //teams = UI.getTeams();
        //TODO: Flyt de her println()'s ind i UI.
        for (int i = 0; i < matches.size()/2+1; i++) {
            for (int j = 0; j < 2; j++) {
                matches.get(i).addTeam(teams[i*2+j]);
            }
        }

        //for (int i = 0; i <= getTeams().length / 2; i++) {
        //    Team[] tempTeams = new Team[2];
        //    tempTeams[i * 2] = Teams[i * 2];
        //    tempTeams[i * 2 + 1] = Teams[i * 2 + 1];
        //    Match tempMatch = new Match(tempTeams, "tempMatch");
        //    Matches.set(i, tempMatch);
        //}
    }
}
