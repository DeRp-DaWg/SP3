import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private String tournamentType = "";
    private String teamName = "";
    private int matchTournamentScore = 0;
    private int goalScore = 0;
    private boolean stillInPlay = true;
    private Scanner sc;
    private String[] playerNames;
    private ArrayList<String> players = new ArrayList<>();
    private String player;
    private Team team;
    IO io = new IO();
    Tournament tournament;

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

        team = new Team(teamName,matchTournamentScore,goalScore,stillInPlay);

        IO io = new IO();
        //io.addPlayer(playerNames, 2);


    }

    public void createTeams() {
        ArrayList<String> names = new ArrayList<>();
        System.out.print("Indtast holdets navn: ");
    }

    public void announce(){

    }
}
