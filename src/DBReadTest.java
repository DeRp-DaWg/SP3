import java.io.IOException;

public class DBReadTest {
    public static void main(String[] args) throws IOException {
        //Team team1 = new Team("Team 1",0,0,false);
        //Team team2 = new Team("Team 2",0,0,false);

        IO io = new IO();
        io.readData();
        Team[] teams = new Team[1];
        teams[0] = new Team("HEJ");
        Tournament tournament = new KnockoutTournament("Tournament Name!", teams);
        tournament.ArrangeMatches();

        //Match match1 = new Match(teams);
        //Match[] matches = {match1};

        //Tournament tournament = new GroupTournament("Tournament 1",teams,matches);
    }
}
