package BusReservation;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;

public class Booking {
    int id;
    String passengerName;
    int busNo;
    Date date;

    public Booking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of passenger: ");
        passengerName = scanner.nextLine();
        System.out.println("Enter bus no: ");
        busNo = scanner.nextInt();
        System.out.println("Enter date (dd-MM-yyyy): ");
        String dateInput = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            java.util.Date utilDate = dateFormat.parse(dateInput);
            date = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Booking(String passengerName, int busNo, Date date) {
        this.passengerName = passengerName;
        this.busNo = busNo;
        this.date = date;
    }

    public Booking(int id, String passengerName, int busNo, Date date) {
        this.id = id;
        this.passengerName = passengerName;
        this.busNo = busNo;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() throws SQLException {
        BusDAO busdao = new BusDAO();
        BookingDAO bookingdao = new BookingDAO();
        int capacity = busdao.getCapacity(busNo);
        int booked = bookingdao.getBookedCount(busNo, date);
        return booked < capacity;
    }
}
