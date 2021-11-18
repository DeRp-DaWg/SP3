import java.io.IOException;
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

    public void start() {
        System.out.print("Vil du lave en ny turnering? Y/N  ");
        String ask = sc.nextLine().toLowerCase();
        if (ask.equals("y")) {
            try {
                createPlayers();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            createTournament();
        }

        if(ask.equals("n")){
            System.out.println("Vil du tilføje point til et eksisterende team? Y/N");
            ask = sc.nextLine().toLowerCase();
            if(ask.equals("y")){
                System.out.println("Vælg et team, skriv derefter et komma efterfulgt af antallet af point, og matchets navn. Fx 1, 3, semifinale: ");
                ask = sc.nextLine().toLowerCase();
                String[] splitAsk = ask.split(",");
                int splitAskFirst = Integer.parseInt(splitAsk[0].replace(" ", ""));
                int splitAskSecond = Integer.parseInt(splitAsk[1].replace(" ", ""));
                String splitAskThird = splitAsk[2].replace(" ", "");
                io.updateTeamScore(splitAskFirst, splitAskSecond, splitAskThird);
                System.out.println("Dine data er nu gemt!");
            }else{
                createTournamentFromDB();
                teams = tournament.getTeams();

                tournament.ArrangeMatches();
                io.clearTable("Matches");
                for (Match match : tournament.getMatches()) {
                    io.insertMatchToDb(match);
                }
                printAllMatches();
            }
        }

        /*
        tournament.ArrangeMatches();
        io.clearTable("Matches");
        for (Match match : tournament.getMatches()) {
            io.insertMatchToDb(match);
        }
        printAllMatches();
        //Date date = new Date(2021,4,2);
        Date date = new Date();
        Match match = tournament.getMatches().get(0);
        try {
            match.setTime(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        io.updateMatchInDB(match);
        for (Match m : tournament.getMatches()) {
            io.updateMatchInDB(m);
        }
        */
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
