public class KnockoutTournament extends Tournament {

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

    public void determineWinnerTeamOutcome(boolean team1Won, int team1CurrentScore, int team2CurrentScore) {
        if (team1Won) {
            winnerTeamNewTotalScore(team1CurrentScore);
        } else {
            winnerTeamNewTotalScore(team2CurrentScore);
        }
    }

    public void determineLoserTeamOutcome(boolean team2Lost, int team1GoalScore, int team2GoalScore) {
        if (team2Lost) {
            loserTeamGoalScore(team1GoalScore,team2GoalScore);
        } else {
            loserTeamGoalScore(team2GoalScore,team1GoalScore);
        }
    }

    @Override
    public void ArrangeMatches() {
       Match[] Matches = getMatches();
       Team[] Teams = getTeams();
for (int i = 0; i <= getTeams().length / 2; i++) {
    Team[] tempTeams = new Team[2];
    tempTeams[i*2] = Teams[i*2];
    tempTeams[i*2+1] = Teams[i*2+1];
    Matches[i] = new Match(tempTeams);
}
    }
}
