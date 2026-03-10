import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        try {
            FlatDarkLaf.setup();
        } catch (Throwable e) {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {}
        }

        SwingUtilities.invokeLater(() -> {
            new ui.SearchUI().setVisible(true);
        });
    }
}