package hotel.ui;

import hotel.model.Hotel;
import hotel.model.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class HotelManagementUI extends JFrame {
    private Hotel hotel;

    private DefaultListModel<String> roomListModel = new DefaultListModel<>();
    private JList<String> roomList = new JList<>(roomListModel);

    private JTextField nameField = new JTextField(15);
    private JButton bookButton = new JButton("Book Room");
    private JButton cancelButton = new JButton("Cancel Booking");
    private JButton billButton = new JButton("Show Bill");

    public HotelManagementUI() {
        hotel = new Hotel();
        setTitle("Hotel Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Hotel Room Management", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(roomList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Guest Name:"));
        inputPanel.add(nameField);
        centerPanel.add(inputPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(billButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshRoomList();

        bookButton.addActionListener(e -> bookRoom());
        cancelButton.addActionListener(e -> cancelBooking());
        billButton.addActionListener(e -> showBill());

        roomList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                nameField.setText("");
            }
        });
    }

    private void refreshRoomList() {
        roomListModel.clear();
        for (Room room : hotel.getRooms()) {
            roomListModel.addElement(room.toString());
        }
    }

    private Integer getSelectedRoomNumber() {
        int index = roomList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room.", "No selection", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String selected = roomListModel.get(index);
        // format: "101 - Available" or "101 - Booked by John"
        try {
            return Integer.parseInt(selected.split(" ")[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private void bookRoom() {
        Integer roomNumber = getSelectedRoomNumber();
        if (roomNumber == null) return;

        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter guest name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hotel.bookRoom(roomNumber, name)) {
            JOptionPane.showMessageDialog(this, "Room " + roomNumber + " booked successfully for " + name + ".");
            refreshRoomList();
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Room is already booked.", "Booking Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelBooking() {
        Integer roomNumber = getSelectedRoomNumber();
        if (roomNumber == null) return;

        if (hotel.cancelBooking(roomNumber)) {
            JOptionPane.showMessageDialog(this, "Booking cancelled for room " + roomNumber + ".");
            refreshRoomList();
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Room is not currently booked.", "Cancellation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBill() {
        Integer roomNumber = getSelectedRoomNumber();
        if (roomNumber == null) return;

        Room room = hotel.getRoom(roomNumber);
        if (room != null && room.isBooked()) {
            JOptionPane.showMessageDialog(this, room.getBill(), "Bill", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Room is not booked.", "No Bill", JOptionPane.WARNING_MESSAGE);
        }
    }
}
