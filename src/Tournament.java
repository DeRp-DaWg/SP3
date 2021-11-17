import java.util.ArrayList;
import java.util.Arrays;

public abstract class Tournament {
    private String tournamentName;
    private Team[] teams;
    private ArrayList <Match> matches;

    public Tournament(String tournamentName, Team[] teams) {
        this.tournamentName = tournamentName;
        this.teams = teams;
    }

    public abstract void ArrangeMatches();

    public String getTournamentName() {
        return tournamentName;
    }

    public Team[] getTeams() {
        return teams;
    }

    public ArrayList <Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentName='" + tournamentName + '\'' +
                ", teams=" + Arrays.toString(teams) +
                ", matches=" + matches +
                '}';
    }
}
