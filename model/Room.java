package hotel.model;

public class Room {
    private int roomNumber;
    private boolean booked;
    private String bookedBy;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.booked = false;
        this.bookedBy = "";
    }

    public Room(int roomNumber, boolean booked, String bookedBy) {
        this.roomNumber = roomNumber;
        this.booked = booked;
        this.bookedBy = bookedBy;
    }

    public int getRoomNumber() { return roomNumber; }
    public boolean isBooked() { return booked; }
    public String getBookedBy() { return bookedBy; }

    public void book(String guestName) {
        this.booked = true;
        this.bookedBy = guestName;
    }

    public void cancel() {
        this.booked = false;
        this.bookedBy = "";
    }

    public String getBill() {
        return booked ? "Room " + roomNumber + " booked by " + bookedBy + ". Total: $2000" : "Room not booked.";
    }

    public String toFileString() {
        return roomNumber + "," + booked + "," + bookedBy;
    }

    public static Room fromFileString(String line) {
        String[] parts = line.split(",", 3);
        int num = Integer.parseInt(parts[0]);
        boolean b = Boolean.parseBoolean(parts[1]);
        String name = parts.length > 2 ? parts[2] : "";
        return new Room(num, b, name);
    }

    @Override
    public String toString() {
        return roomNumber + " - " + (booked ? "Booked by " + bookedBy : "Available");
    }
}
