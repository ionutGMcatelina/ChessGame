import ChessModel.Chessman;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Collections;

/**
 * Here is the graphical user interface.
 * The chess table is a matrix of buttons.
 * Another buttons:
 * - reset: reset the table for a new game
 * - undo: it moves back the last moved piece (dont press it many times in a row)
 * - settings: it open a window with settings for the appearance of the GUI
 */
public class SahView extends JFrame{
    private JButton[][] m_buttons = new JButton[8][8];
    private JButton m_reset = new JButton("  RESET  ");
    private JButton m_undo = new JButton("  UNDO  ");
    private JButton m_settings = new JButton("  SETTINGS  ");
    private JLabel title = new JLabel("CHESS");
    private JLabel winner = new JLabel("PLAYING");
    private JLabel white = new JLabel();
    private JLabel black = new JLabel();
    private SettingsView settingsView;
    private BackgroundPanel bigContent = new BackgroundPanel();

    SahView(SettingsView settingsView){
        JLabel whitePlayer = new JLabel("WHITE PLAYER");
        JLabel blackPlayer = new JLabel("BLACK PLAYER");

        this.settingsView = settingsView;

        winner.setFont(new Font( "Serif", Font.PLAIN, 30));
        winner.setPreferredSize(new Dimension(400, 30));
        title.setFont(new Font( "Serif", Font.PLAIN, 30));
        title.setPreferredSize(new Dimension(100, 30));

        white.setFont(new Font( "Serif", Font.PLAIN, 30));
        white.setPreferredSize(new Dimension(400, 100));
        white.setForeground(Color.WHITE);
        whitePlayer.setFont(new Font( "Serif", Font.PLAIN, 30));

        black.setFont(new Font( "Serif", Font.PLAIN, 30));
        black.setPreferredSize(new Dimension(400, 100));
        black.setForeground(Color.BLACK);
        blackPlayer.setFont(new Font( "Serif", Font.PLAIN, 30));

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                m_buttons[i][j] = new JButton();
                m_buttons[i][j].setFont(new Font( "Serif", Font.PLAIN, 45));
                m_buttons[i][j].setPreferredSize( new Dimension(90, 90));
                m_buttons[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
                if ((i + j) % 2 == 0) {
                    m_buttons[i][j].setBackground(new Color(165,42,42));
                }
                else{
                    m_buttons[i][j].setBackground(new Color(205,133,63));
                }
                if (Chessman.table[i][j].getColor().equals("white")) {
                    m_buttons[i][j].setForeground(Color.WHITE);
                }
                else{
                    m_buttons[i][j].setForeground(Color.BLACK);
                }
                m_buttons[i][j].setText(Chessman.table[i][j].getName());
            }
        }

        m_reset.setFont(new Font("Serif", Font.PLAIN, 30));
        m_reset.setBorder(new BevelBorder(BevelBorder.RAISED));
        m_reset.setBackground(new Color(210,105,30));
        m_undo.setFont(new Font( "Serif", Font.PLAIN, 30));
        m_undo.setBorder(new BevelBorder(BevelBorder.RAISED));
        m_undo.setBackground(new Color(210,105,30));
        m_settings.setFont(new Font( "Serif", Font.PLAIN, 30));
        m_settings.setBorder(new BevelBorder(BevelBorder.RAISED));
        m_settings.setBackground(new Color(210,105,30));

        JPanel content = new JPanel();
        JPanel menuContent = new JPanel();
        JPanel userContent = new JPanel();

        JPanel whiteContent = new JPanel();
        JPanel blackContent = new JPanel();

        setImage(settingsView.getBackgroundBox().getSelectedItem() + "");

        bigContent.add(content);
        bigContent.add(userContent);
        bigContent.setLayout(new FlowLayout());
        content.setLayout(new GridLayout(8, 8));

        whiteContent.add( Box.createRigidArea(new Dimension(0,10)) );
        whiteContent.setBorder(new BevelBorder(BevelBorder.RAISED));
        whiteContent.add(whitePlayer);
        whiteContent.add( Box.createRigidArea(new Dimension(0,20)) );
        whiteContent.add(white);
        whiteContent.setBackground(new Color(165,42,42));
        whiteContent.setLayout(new BoxLayout(whiteContent, BoxLayout.Y_AXIS));

        blackContent.add( Box.createRigidArea(new Dimension(0,10)) );
        blackContent.setBorder(new BevelBorder(BevelBorder.RAISED));
        blackContent.add(blackPlayer);
        blackContent.add( Box.createRigidArea(new Dimension(0,20)) );
        blackContent.add(black);
        blackContent.setBackground(new Color(205,133,63));
        blackContent.setLayout(new BoxLayout(blackContent, BoxLayout.Y_AXIS));

        menuContent.setBackground(new Color(205,133,63));
        menuContent.setLayout(new BoxLayout(menuContent, BoxLayout.Y_AXIS));
        menuContent.add(title);
        menuContent.add( Box.createRigidArea(new Dimension(0,20)) );
        menuContent.add(winner);
        menuContent.add( Box.createRigidArea(new Dimension(0,20)) );
        menuContent.add(m_undo);
        menuContent.add( Box.createRigidArea(new Dimension(0,20)) );
        menuContent.add(m_reset);
        menuContent.add( Box.createRigidArea(new Dimension(0,20)) );
        menuContent.add(m_settings);
        menuContent.setBorder(new BevelBorder(BevelBorder.RAISED));

        userContent.setOpaque(false);
        userContent.setPreferredSize(new Dimension(500, 700));
        userContent.add(menuContent);
        userContent.add(whiteContent);
        userContent.add(blackContent);
        userContent.setLayout(new FlowLayout());

        content.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(205,133,63), new Color(205,133,63)));

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                content.add(m_buttons[i][j]);
            }
        }

        this.setContentPane(bigContent);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void setImage(String img){
        try {
            ImageIcon image = null;
            switch (img) {
                case "BLACK":
                    image = new ImageIcon("Future_earth.jpg");
                    break;
                case "IMAGE 1":
                    image = new ImageIcon("black-background_00313351.jpg");
                    break;
                case "IMAGE 2":
                    image = new ImageIcon("164254282-elegant-wallpapers.jpg");
                    break;
                case "IMAGE 3":
                    image = new ImageIcon("wallpaper2you_574146.jpg");
                    break;
                case "IMAGE 4":
                    image = new ImageIcon("wp2570965.jpg");
                    break;
                case "IMAGE 5":
                    image = new ImageIcon("Widescreen-Full-HD-Background.jpg");
                    break;
                case "IMAGE 6":
                    image = new ImageIcon("121003-red-background-hd-1920x1200-for-full-hd.jpg");
                    break;
            }
            BufferedImage buffImg = new BufferedImage(1400, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffImg.createGraphics();
            assert image != null;
            g2d.drawImage(image.getImage(), 0, 0, 1400, 800, null);
            bigContent.setImg(buffImg);
            bigContent.paintImmediately(0, 0, 1400, 800);
            bigContent.revalidate();
            //this.revalidate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Color makeColor(String sColor){
        Color color = new Color(205,133,63);
        switch (sColor){
            case "DARK GREY": color = Color.DARK_GRAY;
                break;
            case "LIGHT GREY": color = Color.LIGHT_GRAY;
                break;
            case "BEIGE": color = new Color(205,133,63);
                break;
            case  "BROWN": color = new Color(165,42,42);
                break;
            case "RED":  color = Color.RED;
                break;
            case "YELLOW": color = Color.YELLOW;
                break;
            case "BLUE": color = Color.BLUE;
                break;
            case "BLACK": color = Color.BLACK;
                break;
            case "WHITE": color = Color.WHITE;
                break;
        }

        return color;
    }

    void setSquaresColor(String sColor1, String sColor2){
        Color color1 = makeColor(sColor1);

        Color color2 = makeColor(sColor2);

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((i + j) % 2 == 0){
                    m_buttons[i][j].setBackground(color1);
                } else{
                    m_buttons[i][j].setBackground(color2);
                }
            }
        }

    }

    void setChessmanColor(String sColor1, String sColor2){
        Color color1 = makeColor(sColor1);
        Color color2 = makeColor(sColor2);

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (Chessman.table[i][j].getColor().equals("white")){
                    m_buttons[i][j].setForeground(color1);
                } else if (Chessman.table[i][j].getColor().equals("black")){
                    m_buttons[i][j].setForeground(color2);
                }
            }
        }
    }

    void setRemoved(){
        Collections.sort(Chessman.getWhitePieces());
        Collections.sort(Chessman.getBlackPieces());
        white.setText(Chessman.getWhitePieces().toString());
        black.setText(Chessman.getBlackPieces().toString());
    }

    void showSettings(){
        settingsView.setVisible(true);
    }

    void reset(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (Chessman.table[i][j].getColor().equals("white")) {
                    m_buttons[i][j].setForeground(Color.WHITE);
                }
                else{
                    m_buttons[i][j].setForeground(Color.BLACK);
                }
                m_buttons[i][j].setText(Chessman.table[i][j].getName());
            }
        }
        title.setText("WHITE TURN");
        winner.setText("PLAYING");
        this.revalidate();
    }

    void swapButtons(int i, int j, int newI, int newJ){
        m_buttons[newI][newJ].setText(m_buttons[i][j].getText());
        m_buttons[newI][newJ].setForeground(m_buttons[i][j].getForeground());
        m_buttons[i][j].setText("");
    }

    void setWinner(String winner){
        this.winner.setText(winner);
    }

    void addButtonListener(ActionListener e, int i, int j){
        m_buttons[i][j].addActionListener(e);
    }

    void addResetButtonListener(ActionListener e){
        m_reset.addActionListener(e);
    }

    void addSettingsButtonListener(ActionListener e){
        m_settings.addActionListener(e);
    }

    void addUndoButtonListener(ActionListener e){
        m_undo.addActionListener(e);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public static class BackgroundPanel extends JPanel{
        Image img;

        void setImg(Image img){
            this.img = img;
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
}
