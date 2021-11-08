import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    String tournamentType = "";
    String teamName = "";
    Scanner sc;
    String[] playerNames;
    ArrayList<Player> players = new ArrayList<>();
    Player player;
    Team team;
    IO io = new IO("TournamentTestFile.csv");

    public UI() throws IOException {
        createPlayers();
    }

    public void createPlayers() throws IOException {
        sc = new Scanner(System.in);

        //Spørg også om turneringsformanden vil se en oversigt over alle kampene

        //Vælg turneringstype
        System.out.println("Tryk k for knockout-turnering, eller g for gruppeturnering: ");
        tournamentType = sc.nextLine();

        //Get teamname
        System.out.println("Holdnavn: ");
        teamName = sc.nextLine();

        //Get players
        System.out.println("Indtast spillernavne separeret af et komma, fx Ole, Abdi, Hans:");
        playerNames = sc.nextLine().split(",");

        team = new Team(teamName);

        IO io = new IO("tournament");

        for (String playerName : playerNames) {
            player = new Player(playerName);
            team.addPlayer(player);
            //io.addToNewFile(teamName, players); Du skal ændre players her, i parameteren i IO filen til at være arraylist
        }

        io.readFile();
    }

    public void announce(){

    }
}
