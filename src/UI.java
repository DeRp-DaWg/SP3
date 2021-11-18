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
    private static Team[] teams;
    IO io = new IO();
    Tournament tournament;
    int countTeams = 1;

    public UI() throws IOException {

        System.out.print("Vil du lave en ny turnering? Y/N  ");
        if (sc.nextLine().toLowerCase().equals("y")) {
            createPlayers();
            createTournament();
        } else {
            createTournamentFromDB();
        }
        tournament.ArrangeMatches();
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
        teams = new Team[16];
        while (countTeams < 17){
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
            teams[countTeams-1] = team;

            io.addTeam(teamName);
            io.addPlayer(splitPlayerNames, countTeams);
            countTeams++;

            System.out.println("\nTilføj et til hold eller skriv done hvis du er færdig\n");
        }

    }

    public void createTournament() {
        System.out.println();
        System.out.print("Indtast turneringens navn: ");
        String tournamentName = sc.nextLine();
        System.out.println();
        tournament = new KnockoutTournament(tournamentName, teams);
    }

    public void createTournamentFromDB() {
        tournament = io.readData();
    }

    public static Team[] getTeams() {
        return teams;
    }
}
