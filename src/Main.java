import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        Team team1 = new Team("Team 1", ui.createPlayers("Player 1", "Player 2"));
        Team team2 = new Team("Team 2", ui.createPlayers("Player 3", "Player 4"));
        Team[] teams = {team1,team2};
        Tournament tournament = new GroupTournament("Tournament 1",teams);
    }


}
