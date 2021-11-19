import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

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
        sc = new Scanner(System.in);
    }

    public void start() throws SQLException {
        while(true){
            System.out.print("\nVil du lave en ny turnering? Y/N:  ");
            String ask = sc.nextLine().toLowerCase();
            if (ask.equals("y")) {
                try {
                    createPlayers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                createTournament();
                tournament.ArrangeMatches();
                for (Match match : tournament.getMatches()) {
                    io.insertMatchToDb(match);
                }
            }

            if (ask.equals("n")) {
                System.out.println("Vil du ændre positionen på et eksisterende team? Y/N:  ");
                ask = sc.nextLine().toLowerCase();
                if (ask.equals("y")) {
                    System.out.println("Skriv finalenavnet efterfulgt af et komma, og to forskellige teams der også er separeret af et komma. Fx quarterfinals1, 1, 3: ");
                    ask = sc.nextLine();
                    String[] splitAsk = ask.split(",");
                    String splitAskFirst = splitAsk[0].replace(" ", "");
                    int splitAskSecond = Integer.parseInt(splitAsk[1].replace(" ", ""));
                    int splitAskThird = Integer.parseInt(splitAsk[2].replace(" ", ""));
                    io.updateTeamScore(splitAskFirst, splitAskSecond, splitAskThird);
                    System.out.println("Dine data er nu gemt!");
                } else {
                    createTournamentFromDB();
                    teams = tournament.getTeams();

                    tournament.ArrangeMatches();
                    io.clearTable("Matches");
                    for (Match match : tournament.getMatches()) {
                        io.insertMatchToDb(match);
                    }
                    printAllMatches();
                }
                System.out.println("Vil du tilføje en score for en kamp? Y/N");
                ask = sc.nextLine().toLowerCase();
                if (ask.equals("y")) {
                    printAllMatches();
                    System.out.println("Skriv navnet på kampen: ");
                    String inputMatchName = sc.nextLine();
                    for (Match match : tournament.getMatches()) {
                        if (match.getMatchName().equals(inputMatchName)) {
                            System.out.println("Hvor mange point havde vinderen?");
                            int score = Integer.parseInt(sc.nextLine());
                            System.out.println("Hvem vandt? 1 eller 2");
                            int winningTeam = Integer.parseInt(sc.nextLine());
                            if (winningTeam == 1) {
                                match.setScore(score);
                                tournament.createOutcome(match);
                            } else {
                                match.setScore(-score);
                                tournament.createOutcome(match);
                            }
                        }
                    }
                    for (Match match : tournament.getMatches()) {
                        io.updateMatchInDB(match);
                    }
                }

            }
        }
    }
    public void seeTheMatches () {
        System.out.println("Vil du se alle kampene? Y/N: ");
        String ask = sc.nextLine().toLowerCase();
        if (ask.equals("y")) {
            System.out.println("Værsgo, her kan du se alle kampene");
            printAllMatches();
        }
    }

    public void createPlayers() throws IOException {
        io.clearTable("Matches");
        io.clearTable("Teams");
        io.clearTable("Players");

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

    public void printAllMatches() {
        for (Match match : tournament.getMatches()) {
            System.out.println(match);
            System.out.println();
        }
    }

    public static Team[] getTeams() {
        return teams;
    }
}
