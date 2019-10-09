import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Settings for the appearance of the table:
 * - background
 * - squares color
 * - pieces color
 */
public class SettingsView extends JFrame {
    private JLabel settings = new JLabel("Settings");
    private JLabel background = new JLabel("Background:");
    private JLabel squaresColor = new JLabel("Squares color:");
    private JLabel chessmanColor = new JLabel("Chessman color:");
    private JButton applyButton = new JButton("APPLY");
    private JComboBox backgroundBox;
    private JComboBox squaresColorBox1;
    private JComboBox squaresColorBox2;
    private JComboBox chessmanColorBox1;
    private JComboBox chessmanColorBox2;

    public SettingsView(){
        String[] backgrounds = {"BLACK", "IMAGE 1", "IMAGE 2", "IMAGE 3", "IMAGE 4", "IMAGE 5", "IMAGE 6"};
        String[] colors1 = {"BEIGE", "BROWN","DARK GREY", "LIGHT GREY", "RED", "YELLOW", "BLUE", "BLACK", "WHITE"};
        String[] colors2 = {"WHITE", "BLACK", "DARK GREY", "LIGHT GREY", "BEIGE", "BROWN", "RED", "YELLOW", "BLUE"};

        backgroundBox = new JComboBox(backgrounds);
        squaresColorBox1 = new JComboBox(colors1);
        squaresColorBox2 = new JComboBox(colors1);
        chessmanColorBox1 = new JComboBox(colors2);
        chessmanColorBox2 = new JComboBox(colors2);

        backgroundBox.setBackground(new Color(0, 0, 255));
        squaresColorBox1.setBackground(new Color(0, 0, 255));
        squaresColorBox2.setBackground(new Color(0, 0, 255));
        chessmanColorBox1.setBackground(new Color(0, 0, 255));
        chessmanColorBox2.setBackground(new Color(0, 0, 255));

        settings.setFont(new Font("Serif", Font.PLAIN, 50));
        background.setFont(new Font("Serif", Font.PLAIN, 20));
        squaresColor.setFont(new Font("Serif", Font.PLAIN, 20));
        chessmanColor.setFont(new Font("Serif", Font.PLAIN, 20));
        applyButton.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel squaresColorsContent = new JPanel();
        JPanel chessmanColorsContent = new JPanel();
        squaresColorsContent.add(squaresColorBox1);
        squaresColorsContent.add(squaresColorBox2);
        squaresColorsContent.setLayout(new FlowLayout());
        squaresColorsContent.setOpaque(false);

        chessmanColorsContent.add(chessmanColorBox1);
        chessmanColorsContent.add(chessmanColorBox2);
        chessmanColorsContent.setLayout(new FlowLayout());
        chessmanColorsContent.setLayout(new FlowLayout());
        chessmanColorsContent.setOpaque(false);

        JPanel settingsContent = new JPanel();
        settingsContent.add(settings);
        settingsContent.add(background);
        settingsContent.add(backgroundBox);
        settingsContent.add(squaresColor);
        settingsContent.add(squaresColorsContent);
        settingsContent.add(chessmanColor);
        settingsContent.add(chessmanColorsContent);
        settingsContent.add(applyButton);
        settingsContent.setBackground(new Color(30, 144, 255));
        settingsContent.setLayout(new FlowLayout());
        settingsContent.setPreferredSize(new Dimension(250, 300));

        this.setContentPane(settingsContent);
        this.pack();
    }

    public JComboBox getBackgroundBox() {
        return backgroundBox;
    }

    public JComboBox getSquaresColorBox1() {
        return squaresColorBox1;
    }

    public JComboBox getChessmanColorBox1() {
        return chessmanColorBox1;
    }

    public JComboBox getSquaresColorBox2() {
        return squaresColorBox2;
    }

    public JComboBox getChessmanColorBox2() {
        return chessmanColorBox2;
    }

    void addBackgroundListener(ActionListener e){
        applyButton.addActionListener(e);
    }
}
