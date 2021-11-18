import java.util.ArrayList;
import java.util.Arrays;

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
        String[] matchNames = {"Eight-finals", "Quarterfinals", "Semifinals", "Final"};
        int matchCount = 8;
        int iteration = 0;
        while (matchCount != 1) {
            for (int i = 1; i < matchCount+1; i++) {
                System.out.println(matchNames[iteration]+i);
                Match match = new Match(matchNames[iteration]+i);
                matches.add(match);
            }
            iteration++;
            matchCount /= 2;
        }
        System.out.println(matchNames[matchNames.length-1]);
        Match match = new Match(matchNames[matchNames.length-1]);
        matches.add(match);

        //teams = UI.getTeams();
        //TODO: Flyt de her println()'s ind i UI.
        System.out.println(Arrays.toString(teams));
        for (int i = 0; i < matches.size()/2+1; i++) {
            System.out.println("===== "+matches.get(i).getMatchName()+" =====");
            for (int j = 0; j < 2; j++) {
                System.out.println("Team"+(i*2+j)+": "+teams[i*2+j].getTeamName());
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
