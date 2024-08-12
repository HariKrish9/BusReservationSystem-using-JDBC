package BusReservation;

import java.sql.*;

public class BookingDAO {

    public int getBookedCount(int busNo, Date date) throws SQLException {
        String query = "SELECT count(passenger_name) FROM booking WHERE bus_no=? AND travel_date=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, busNo);
        pst.setDate(2, date);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO booking (passenger_name, bus_no, travel_date) VALUES (?, ?, ?)";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, booking.passengerName);
        pst.setInt(2, booking.busNo);
        pst.setDate(3, booking.date);
        pst.executeUpdate();
    }

    public Booking getBooking(int id) throws SQLException {
        String query = "SELECT * FROM booking WHERE id=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String passengerName = rs.getString("passenger_name");
            int busNo = rs.getInt("bus_no");
            Date date = rs.getDate("travel_date");
            return new Booking(id, passengerName, busNo, date);
        }
        return null;
    }

    public void updateBooking(Booking booking) throws SQLException {
        String query = "UPDATE booking SET passenger_name=?, bus_no=?, travel_date=? WHERE id=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, booking.passengerName);
        pst.setInt(2, booking.busNo);
        pst.setDate(3, booking.date);
        pst.setInt(4, booking.getId());
        pst.executeUpdate();
    }

    public void deleteBooking(int id) throws SQLException {
        String query = "DELETE FROM booking WHERE id=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.executeUpdate();
    }
}
