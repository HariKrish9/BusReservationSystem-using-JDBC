package BusReservation;

import java.util.Scanner;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BusDemo {

    public static void main(String[] args) {
        BusDAO busdao = new BusDAO();

        try {
            busdao.displayBusInfo();

            int userOpt = 1;
            Scanner scanner = new Scanner(System.in);

            while (userOpt != 0) {
                System.out.println("Enter 1 to Book, 2 to View, 3 to Update, 4 to Delete, 0 to exit");
                userOpt = scanner.nextInt();

                BookingDAO bookingdao = new BookingDAO();
                Booking booking;

                switch (userOpt) {
                    case 1:
                        booking = new Booking();
                        try {
                            if (booking.isAvailable()) {
                                bookingdao.addBooking(booking);
                                System.out.println("Your booking is confirmed");
                            } else {
                                System.out.println("Sorry. Bus is full. Try another bus or date.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error checking availability: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter booking ID to view:");
                        int id = scanner.nextInt();
                        try {
                            booking = bookingdao.getBooking(id);
                            if (booking != null) {
                                System.out.println("Booking Details: " + booking.passengerName + " " + booking.busNo + " " + booking.date);
                            } else {
                                System.out.println("Booking not found.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error retrieving booking: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Enter booking ID to update:");
                        id = scanner.nextInt();
                        try {
                            booking = bookingdao.getBooking(id);
                            if (booking != null) {
                                System.out.println("Enter new passenger name:");
                                scanner.nextLine(); // consume newline
                                String newName = scanner.nextLine();
                                System.out.println("Enter new bus number:");
                                int newBusNo = scanner.nextInt();
                                System.out.println("Enter new date (dd-MM-yyyy):");
                                String dateInput = scanner.next();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                Date newDate = new Date(dateFormat.parse(dateInput).getTime());

                                booking.setId(id);
                                booking.passengerName = newName;
                                booking.busNo = newBusNo;
                                booking.date = newDate;
                                bookingdao.updateBooking(booking);
                                System.out.println("Booking updated.");
                            } else {
                                System.out.println("Booking not found.");
                            }
                        } catch (SQLException | ParseException e) {
                            System.out.println("Error updating booking: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Enter booking ID to delete:");
                        id = scanner.nextInt();
                        try {
                            bookingdao.deleteBooking(id);
                            System.out.println("Booking deleted.");
                        } catch (SQLException e) {
                            System.out.println("Error deleting booking: " + e.getMessage());
                        }
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
