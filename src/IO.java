import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class IO {
    //hejju
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
        Connection conn = null;
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
        Connection conn = null;
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

            Connection conn = null;
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

    public void readData() {
        Match[] matches;
        Team[] teams;
        String sql;
        ResultSet rs = null;
        int lineCount;

        String[] field_data = new String[40];
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
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
                System.out.println(ID+", "+teamName+", "+teamTournamentScore+", "+teamGoalScore+", "+stillInPlay);
                Team team = new Team(teamName, teamTournamentScore, teamGoalScore, stillInPlay);
                teams[teamCount] = team;
                teamCount++;
            }
            System.out.println();

            //CREATING PLAYERS
            sql = "SELECT * FROM Players";
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int ID = rs.getInt("ID");
                String playerName = rs.getString("playerName");
                int teamID = rs.getInt("teamID");
                System.out.println(ID+", "+playerName+", "+teamID);
                teams[teamID-1].addPlayer(playerName);
            }
            System.out.println();

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
                System.out.println(ID+", "+matchName+", "+teamOne+", "+teamTwo+", "+score);
                Team[] matchTeams = {teams[teamOne-1], teams[teamTwo-1]};
                matches[ID-1] = new Match(matchTeams, matchName, score);
            }
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
    }
}