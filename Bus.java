package BusReservation;

public class Bus {
    private int busNo;
    private boolean ac;
    private int capacity;
    private String startDestination;
    private String endDestination;

    public Bus(int no, boolean ac, int cap, String startDest, String endDest) {
        this.busNo = no;
        this.ac = ac;
        this.capacity = cap;
        this.startDestination = startDest;
        this.endDestination = endDest;
    }

    public int getBusNo() {
        return busNo;
    }

    public boolean isAc() {
        return ac;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setAc(boolean val) {
        ac = val;
    }

    public void setCapacity(int cap) {
        capacity = cap;
    }

    public void setStartDestination(String startDest) {
        this.startDestination = startDest;
    }

    public void setEndDestination(String endDest) {
        this.endDestination = endDest;
    }
}
