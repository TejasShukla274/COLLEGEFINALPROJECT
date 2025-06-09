package hotel.model;

import java.io.*;
import java.util.*;

public class Hotel {
    private Map<Integer, Room> rooms = new TreeMap<>();
    private static final String FILE = "rooms.txt";

    public Hotel() {
        loadRooms();
    }

    public Collection<Room> getRooms() {
        return rooms.values();
    }

    public Room getRoom(int number) {
        return rooms.get(number);
    }

    public boolean bookRoom(int number, String guestName) {
        Room room = rooms.get(number);
        if (room != null && !room.isBooked()) {
            room.book(guestName);
            saveRooms();
            return true;
        }
        return false;
    }

    public boolean cancelBooking(int number) {
        Room room = rooms.get(number);
        if (room != null && room.isBooked()) {
            room.cancel();
            saveRooms();
            return true;
        }
        return false;
    }

    private void loadRooms() {
        File file = new File(FILE);
        if (!file.exists()) {
            // Initialize default rooms
            int[] defaultRooms = {101, 102, 201, 202, 203};
            for (int r : defaultRooms) {
                rooms.put(r, new Room(r));
            }
            saveRooms();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            rooms.clear();
            String line;
            while ((line = br.readLine()) != null) {
                Room room = Room.fromFileString(line);
                rooms.put(room.getRoomNumber(), room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRooms() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Room r : rooms.values()) {
                pw.println(r.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
