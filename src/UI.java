import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class UI {
    private String tournamentType = "";
    private String teamName = "";
    private String getPlayerNames = "";
    private int matchTournamentScore = 0;
    private int goalScore = 0;
    private boolean stillInPlay = true;
    private Scanner sc;
    private ArrayList<String> players = new ArrayList<>();
    private String player;
    private Team team;
    IO io = new IO();
    Tournament tournament;
    int countTeams = 1;

    public UI() throws IOException {
        createPlayers();
    }

    public void createPlayers() throws IOException {
        sc = new Scanner(System.in);

        System.out.println("Tast k for at se kamptider eller o for at oprette en ny turnering: ");
        String chooseOption = sc.nextLine();

        if(chooseOption.equals("k")){
            System.out.println("Vis data");
            return;
        }


        //Turneringsformanden

        while (teamName.toLowerCase() != "done"){
            //Get teamname
            System.out.println("Holdnavn: ");
            teamName = sc.nextLine();

            if(teamName.toLowerCase().equals("done")){
                System.out.println("\nDine hold er gemt!");
                break;
            }

            //Get players
            System.out.println("Indtast spillernavne separeret af et komma, fx Ole, Abdi, Hans.");
            getPlayerNames = sc.nextLine();
            String[] splitPlayerNames = getPlayerNames.split(",");

            team = new Team(teamName,matchTournamentScore,goalScore,stillInPlay);
            IO io = new IO();

            io.addTeam(teamName);
            io.addPlayer(splitPlayerNames, countTeams);
            countTeams++;

            System.out.println("\nTilføj et til hold eller skriv done hvis du er færdig\n");
        }

    }

    public void createTeams() {
        ArrayList<String> names = new ArrayList<>();
        System.out.print("Indtast holdets navn: ");
    }

    public void announce() {

    }

    public void createTournament() {
        Team[] teams = new Team[2];
        teams[0] = new Team("Test team name");
        tournament = new KnockoutTournament("Test tournament name", teams);
    }

    public void createTournamentFromDB() {
        tournament = io.readData();
    }
}
