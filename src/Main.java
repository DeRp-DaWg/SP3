public class Main {
    public static void main(String[] args) {
        String[] players1 = {"Player 1", "Player 2"};
        String[] players2 = {"Player 3", "Player 4"};
        Team team1 = new Team("Team 1", players1);
        Team team2 = new Team("Team 2", players2);
        Team[] teams = {team1,team2};
        Tournament tournament = new GroupTournament("Tournament 1",teams);
    }
}
