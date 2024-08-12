package BusReservation;

import java.sql.*;

public class BusDAO {
    public void displayBusInfo() throws SQLException {
        String query = "SELECT * FROM bus";
        Connection con = DbConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("Bus No: " + rs.getInt("id"));
            System.out.println("AC: " + (rs.getBoolean("ac") ? "yes" : "no"));
            System.out.println("Capacity: " + rs.getInt("capacity"));
            System.out.println("Start Destination: " + rs.getString("start_destination"));
            System.out.println("End Destination: " + rs.getString("end_destination"));
            System.out.println("------------------------------------------");
        }
    }

    public int getCapacity(int id) throws SQLException {
        String query = "SELECT capacity FROM bus WHERE id=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
