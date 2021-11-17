import java.sql.*;

public class IO {

    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/SP3";

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
    public void addTeam(){
        Connection conn = null;
        String sql = "INSERT INTO Teams(teamName, teamTournamentScore, teamGoalScore, stillInPlay) VALUES (?, ?, ?, ?)";

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
}