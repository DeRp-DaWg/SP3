import java.sql.*;

public class IO {
    //hejju
    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/TournamentDB";


    //  Database credentials
    static final String USER = "root";
    static final String PASS = "test";

    //Sådan tilføjer man data. Skal ændres senere
    public void saveData() {
        Connection conn = null;
        String sql = "INSERT INTO Tournament (id, teamname, playerName, matchID) "
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
        System.out.println(playerNames);
        for (String playerName : playerNames) {
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
        String[] matches;
        String[] players;
        String[] teams;
        String sql;
        ResultSet rs = null;

        String[] field_data = new String[40];
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //CREATING TEAMS
            sql = "SELECT * FROM Teams";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int ID = rs.getInt("ID");
                String teamName = rs.getString("teamName");
                String teamTournamentScore = rs.getString("teamTournamentScore");
                String teamGoalScore = rs.getString("teamGoalScore");
                Boolean stillInPlay = rs.getBoolean("stillInPlay");
                System.out.println(ID+", "+teamName+", "+teamTournamentScore+", "+teamGoalScore+", "+stillInPlay);
            }
            System.out.println();
            //CREATING PLAYERS
            sql = "SELECT * FROM Players";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int ID = rs.getInt("ID");
                String playerName = rs.getString("playerName");
                int teamID = rs.getInt("teamID");
                System.out.println(ID+", "+playerName+", "+teamID);
            }
            System.out.println();
            //CREATING MATCHES
            sql = "SELECT * FROM Matches";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int ID = rs.getInt("ID");
                String matchName = rs.getString("matchName");
                int teamOne = rs.getInt("teamOne");
                int teamTwo = rs.getInt("teamTwo");
                int score = rs.getInt("score");
                System.out.println(ID+", "+matchName+", "+teamOne+", "+teamTwo+", "+score);
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