package hotel;
import hotel.ui.HotelManagementUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelManagementUI ui = new HotelManagementUI();
            ui.setVisible(true);
        });
    }
}
