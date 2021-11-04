import java.util.Arrays;

public abstract class Tournament {
    public String tournamentName;
    public Team[] teams;
    public Match[] matches;

    public Tournament(String tournamentName, Team[] teams, Match[] matches) {
        this.tournamentName = tournamentName;
        this.teams = teams;
        this.matches = matches;
    }

    public abstract void ArrangeMatches();

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentName='" + tournamentName + '\'' +
                ", teams=" + Arrays.toString(teams) +
                ", matches=" + Arrays.toString(matches) +
                '}';
    }
}
