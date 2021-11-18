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
        System.out.print("Vil du lave en ny turnering? Y/N:  ");
        String ask = sc.nextLine().toLowerCase();
        if (ask.equals("y")) {
            try {
                createPlayers();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            createTournament();
            tournament.ArrangeMatches();
            for (Match match : tournament.getMatches()) {
                io.insertMatchToDb(match);
            }
        }

        if(ask.equals("n")){
            System.out.println("Vil du tilføje point til et eksisterende team? Y/N:  ");
            ask = sc.nextLine().toLowerCase();
            if(ask.equals("y")){
                System.out.println("Skriv finalenavnet efterfulgt af et komma, af to forskellige teams der er separeret af et komma. Fx quarterfinals1, 1, 3: ");
                ask = sc.nextLine();
                String[] splitAsk = ask.split(",");
                String splitAskFirst = splitAsk[0].replace(" ", "");
                int splitAskSecond = Integer.parseInt(splitAsk[1].replace(" ", ""));
                int splitAskThird = Integer.parseInt(splitAsk[2].replace(" ", ""));
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
        Match match1 = tournament.getMatches().get(0);
        match1.setScore(6);
        tournament.createOutcome(match1);
        io.updateMatchInDB(match1);

        Match match2 = tournament.getMatches().get(1);
        match2.setScore(-8);
        tournament.createOutcome(match2);
        io.updateMatchInDB(match2);

        Match match3 = tournament.getMatches().get(8);
        match3.setScore(4);
        tournament.createOutcome(match3);
        io.updateMatchInDB(match3);

        printAllMatches();

        //Match matchhh = tournament.getMatches().get(8);
        //matchhh.setScore(7);
        //tournament.createOutcome(matchhh);
*/
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
    /*
    public void createOutcome(Match match) {
        Team teamOne = match.getTeams()[0];
        Team teamTwo = match.getTeams()[1];
        String[] matchNames = {"Eight-finals", "Quarterfinals", "Semifinals", "Final"};
        String matchName = match.getMatchName();
        matchName = matchName.replaceAll("\\d", "");
        for (int i = 0; i < matchNames.length; i++) {
            if (matchNames[i].equals(matchName)) {
                if (matchNames[i].equals(matchNames[matchNames.length-1])) {
                    break;
                }
                String nextMatchName = matchNames[i+1];
                for (Match match1 : tournament.getMatches()) {
                    String match1Name = match1.getMatchName();
                    match1Name = match1Name.replaceAll("\\d", "");
                    if (match1Name.equals(nextMatchName) && match1.hasSpace()) {
                        int result = match.findMatchResults();
                        switch (result) {
                            case 0:
                                break;
                            case 1:
                                match1.addTeam(teamOne);
                                break;
                            case 2:
                                match1.addTeam(teamTwo);
                                break;
                        }
                    }
                }
            }
        }
    }*/

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
