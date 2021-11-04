public class KnockoutTournament extends Tournament {

    public KnockoutTournament(String tournamentName, Team[] teams, Match[] matches) {
        super(tournamentName, teams, matches);
        int opponentScored = 0;
        int goalsScored = 0;
        int score;
        if (goalsScored < opponentScored){
            score = opponentScored-goalsScored;
        }else{
            score = goalsScored-opponentScored;
        }

    }

    @Override
    public void ArrangeMatches() {

    }
}
