import java.util.ArrayList;
import java.util.Arrays;

public class KnockoutTournament extends Tournament {
    ArrayList<Match> Matches = getMatches();
    Team[] Teams = getTeams();

    public KnockoutTournament(String tournamentName, Team[] teams) {
        super(tournamentName, teams);
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

    // team1/2number = teamets plads i Matches arraylisten
    public void determineWinnerTeamOutcome(int team1number, int team2number, boolean team1Won, int team1CurrentScore, int team2CurrentScore) {
        if (team1Won) {
            winnerTeamNewTotalScore(team1CurrentScore);
            Matches.remove(team2number);
        } else {
            winnerTeamNewTotalScore(team2CurrentScore);
            Matches.remove(team1number);
        }
    }

    public void determineLoserTeamOutcome(int team1number, int team2number, boolean team2Lost, int team1GoalScore, int team2GoalScore) {
        if (team2Lost) {
            loserTeamGoalScore(team1GoalScore,team2GoalScore);
            Matches.remove(team2number);
        } else {
            loserTeamGoalScore(team2GoalScore,team1GoalScore);
            Matches.remove(team1number);
        }
    }

    @Override
    public void ArrangeMatches() {
for (int i = 0; i <= getTeams().length / 2; i++) {
    Team[] tempTeams = new Team[2];
    tempTeams[i*2] = Teams[i*2];
    tempTeams[i*2+1] = Teams[i*2+1];
    Match tempMatch = new Match(tempTeams, "tempMatch");
    Matches.set(i, tempMatch);
}
    }

    @Override
    public String toString() {
        return "KnockoutTournament{" +
                "Matches=" + Matches +
                ", Teams=" + Arrays.toString(Teams) +
                '}';
    }
}
