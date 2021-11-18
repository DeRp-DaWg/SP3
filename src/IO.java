import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IO {
    private Connection conn = null;
    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/TournamentDB";

    //  Database credentials
    static final String USER = "root";
    static String PASS;

    public IO() {
        try {
            File passwordFile = new File("password.txt");
            Scanner passwordReader = new Scanner(passwordFile);
            PASS = passwordReader.nextLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Sådan tilføjer man data. Skal ændres senere
    public void saveData() {
        String sql = "INSERT INTO Tournament (id, teamname, playerName, matchID)"
                + "VALUES (?,?,?,?)";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, 1);
            pstmt.setString(2, "Hold A");
            pstmt.setString(3, "Abdi");
            pstmt.setInt(4, 1);

            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tilføj et team
    public void addTeam(String teamName){

        String sql = "INSERT INTO Teams(teamName, teamTournamentScore, teamGoalScore, stillInPlay) VALUES (?, ?, ?, ?)";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, teamName);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, 0);
            pstmt.setBoolean(4, true);

            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tilføj players
    public void addPlayer(String[] playerNames, int foreignKey){
        for (String playerName : playerNames) {
            playerName = playerName.replace(" ", "");
            System.out.println(playerName);

            String sql = "INSERT INTO Players(playerName, teamID) VALUES (?, ?)";

            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, playerName);
                pstmt.setInt(2, foreignKey);

                pstmt.addBatch();
                pstmt.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void insertMatchToDb(Match match){
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String matchName = null;
        int score = 0;
        String teamOneName = null;
        String teamTwoName = null;
        String date = null;
        boolean gotMatch = true;
        try {
            matchName = match.getMatchName();
            score = match.getScore();
            date = match.getDateAsString();
            teamOneName = match.getTeams()[0].getTeamName();
            teamTwoName = match.getTeams()[1].getTeamName();
        }
        catch (NullPointerException e) {
            gotMatch = false;
        }

        int teamOne = 0;
        int teamTwo = 0;
        String sql = null;
        if (gotMatch) {
            sql = "SELECT ID FROM teams WHERE teamName=? OR teamName=?";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, teamOneName);
                pstmt.setString(2, teamTwoName);
                rs = pstmt.executeQuery();
                rs.next();
                teamOne = rs.getInt("ID");
                rs.next();
                teamTwo = rs.getInt("ID");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            sql = "INSERT INTO Matches(matchName, score, time, teamOne, teamTwo) VALUES (?, ?, ?, ?, ?)";
        }
        else {
            sql = "INSERT INTO Matches(matchName, score, time) VALUES (?, ?, ?)";
        }



        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, matchName);
            pstmt.setInt(2, score);
            pstmt.setString(3, date);
            if (gotMatch) {
                pstmt.setInt(4, teamOne);
                pstmt.setInt(5, teamTwo);
            }
            pstmt.addBatch();
            pstmt.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMatchInDB(Match match) {
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String matchName = null;
        int score = 0;
        String teamOneName = null;
        String teamTwoName = null;
        String date = null;
        boolean gotMatch = true;
        try {
            matchName = match.getMatchName();
            score = match.getScore();
            date = match.getDateAsString();
            teamOneName = match.getTeams()[0].getTeamName();
            teamTwoName = match.getTeams()[1].getTeamName();
        }
        catch (NullPointerException e) {
            System.out.print("");
        }
        int teamOne = 0;
        int teamTwo = 0;
        String sql = null;
        sql = "SELECT ID FROM teams WHERE teamName=? OR teamName=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, teamOneName);
            pstmt.setString(2, teamTwoName);
            rs = pstmt.executeQuery();
            rs.next();
            teamOne = rs.getInt("ID");
            rs.next();
            teamTwo = rs.getInt("ID");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "UPDATE matches SET teamOne=?, teamTwo=?, score=?, time=? WHERE matchName=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, teamOne);
            pstmt.setInt(2, teamTwo);
            pstmt.setInt(3,score);
            pstmt.setString(4,date);
            pstmt.setString(5,matchName);
            pstmt.addBatch();
            pstmt.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeamScore(String getMatchName, int teamOne, int teamTwo) throws SQLException {
        String sql1 = "SET SQL_SAFE_UPDATES = 0";
        String sql2 = "UPDATE matches SET teamOne = 1, teamTwo=2 WHERE matchName= \"Quarterfinals1\"";
        String sql4 = "SET SQL_SAFE_UPDATES = 1";

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement pstmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement pstmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement pstmt4 = conn.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS);


        pstmt1.addBatch();
        pstmt2.addBatch();
        pstmt4.addBatch();

        pstmt1.executeBatch();
        pstmt2.executeBatch();
        pstmt4.executeBatch();
    }

    public Tournament readData() {
        Match[] matches;
        Team[] teams;
        String sql;
        ResultSet rs = null;
        int lineCount;

        String[] field_data = new String[40];
        Statement stmt = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            //CREATING TEAMS
            sql = "SELECT * FROM Teams";
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            lineCount = 0;
            while (rs.next()) {
                lineCount++;
            }
            teams = new Team[lineCount];
            rs.beforeFirst();
            int teamCount = 0;
            while(rs.next()) {
                int ID = rs.getInt("ID");
                String teamName = rs.getString("teamName");
                int teamTournamentScore = rs.getInt("teamTournamentScore");
                int teamGoalScore = rs.getInt("teamGoalScore");
                Boolean stillInPlay = rs.getBoolean("stillInPlay");
                Team team = new Team(teamName, teamTournamentScore, teamGoalScore, stillInPlay);
                teams[teamCount] = team;
                teamCount++;
            }

            //CREATING PLAYERS
            sql = "SELECT * FROM Players";
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int ID = rs.getInt("ID");
                String playerName = rs.getString("playerName");
                int teamID = rs.getInt("teamID");
                teams[teamID-1].addPlayer(playerName);
            }

            //CREATING MATCHES
            sql = "SELECT * FROM Matches";
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            lineCount = 0;
            while (rs.next()) {
                lineCount++;
            }
            matches = new Match[lineCount];
            rs.beforeFirst();
            int matchCount = 0;
            while(rs.next()) {
                int ID = rs.getInt("ID");
                String matchName = rs.getString("matchName");
                int teamOne = rs.getInt("teamOne");
                int teamTwo = rs.getInt("teamTwo");
                int score = rs.getInt("score");
                if (!(teamOne <= 0)) {
                    Team[] matchTeams = {teams[teamOne-1], teams[teamTwo-1]};
                    matches[ID-1] = new Match(matchTeams, matchName, score);
                } else {
                    matches[ID-1] = new Match(matchName);
                }

            }
            ArrayList<Match> matchesAL = new ArrayList<>(Arrays.asList(matches));
            Tournament tournament = new KnockoutTournament("Tournament Name!", teams, matchesAL);
            return tournament;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void clearTable(String getTable){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0", Statement.RETURN_GENERATED_KEYS);

            pstmt.addBatch();

            PreparedStatement pstmt1 = conn.prepareStatement("TRUNCATE TABLE " + getTable, Statement.RETURN_GENERATED_KEYS);
            pstmt1.addBatch();

            PreparedStatement pstmt2 = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1", Statement.RETURN_GENERATED_KEYS);
            pstmt2.addBatch();

            pstmt.executeBatch();
            pstmt1.executeBatch();
            pstmt2.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSql(String sql){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
