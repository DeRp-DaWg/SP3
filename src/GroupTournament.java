import java.lang.reflect.Array;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupTournament extends Tournament{

    public GroupTournament(String tournamentName, Team[] teams, Match[] matches)
    {
        super(tournamentName, teams);
    }

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

    public Team[] ScoreBoard(){
    Team[] scoreBoard;
    Team[] teams = getTeams();
    for (int i = 0; i > getTeams().length - 1; i++) {
        if (teams.goalScore
    }


    }

    @Override
    public void ArrangeMatches() {

    }
}
