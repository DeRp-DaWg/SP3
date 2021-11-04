public class Match {
    private Team[] teams;
    private int points;

    public Match(Team[] teams) {
        this.teams = teams;
    }

    public void addPoints() {
        points++;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    public int getPoints() {
        return points;
    }
}
