import java.util.Arrays;

public abstract class Tournament {
    private String tournamentName;
    private Team[] teams;
    private Match[] matches;

    public Tournament(String tournamentName, Team[] teams) {
        this.tournamentName = tournamentName;
        this.teams = teams;
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
