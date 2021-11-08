import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Team team1 = new Team("Team 1",0,0,false);
        Team team2 = new Team("Team 2",0,0,false);
        Team[] teams = {team1,team2};



        Match match1 = new Match(teams);
        Match[] matches = {match1};

        Tournament tournament = new GroupTournament("Tournament 1",teams,matches);

    }
}
