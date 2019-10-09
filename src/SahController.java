import ChessModel.Chessman;
import ChessModel.King;
import ChessModel.SahModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class makes connection between GUI and internal functionality
 * IMPORTANT:
 * there are two tables: - the backward table made of Chessman objects
 *                       - the forward table made of buttons
 */
class SahController {
    private SahView m_view;
    private SahModel m_model;
    private int [] poz = new int[2];
    private int moves;
    private boolean gameOver = false;
    private SettingsView settingsView;

    private int currentMoveX;
    private int currentMoveY;
    private int previousMoveX;
    private int previousMoveY;
    private Chessman lastPiece;

    SahController(SahModel m_model, SahView m_view, SettingsView settingsView){
        this.m_model = m_model;
        this.m_view = m_view;
        this.settingsView = settingsView;
        poz[0] = -1;
        poz[1] = -1;
        moves = 0;

        settingsView.addBackgroundListener(new BackgroundListener());

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                m_view.addButtonListener(new ButtonListener(i, j), i, j);
            }
        }
        m_view.addResetButtonListener(new ResetButtonListener());
        m_view.addSettingsButtonListener(new SettingsButtonListener());
        m_view.addUndoButtonListener(new UndoButtonListener());
    }

    public class ButtonListener implements ActionListener{
        private int i;
        private int j;

        ButtonListener(int i, int j){
            this.i = i;
            this.j = j;
        }

        /**
         * Listener for the buttons on the chess table
         */
        public void actionPerformed(ActionEvent e){
            if (!gameOver) {
                boolean outA;
                boolean outB;
                boolean ok = false;
                outA = false;
                outB = false;
                /*
                * If the previous move wasn't stored it will store the position of the pressed button (if isn't empty)
                * Otherwise, it swaps the button pressed now with the one pressed before (it will swap the text) if
                * this is possible.
                * */
                if (poz[0] == -1) {
                    if (moves % 2 == 0 && Chessman.table[i][j].getColor().equals("white") ||
                            moves % 2 != 0 && Chessman.table[i][j].getColor().equals("black")) {
                        poz[0] = i;
                        poz[1] = j;
                    }
                } else {
                    int sizeA = Chessman.getWhitePieces().size();
                    int sizeB = Chessman.getBlackPieces().size();
                    if (Chessman.table[poz[0]][poz[1]].move(i, j)) {
                        if (Chessman.getWhitePieces().size() != sizeA){
                            outA = true;
                        }
                        if (Chessman.getBlackPieces().size() != sizeB){
                            outB = true;
                        }

                        ok = true;
                        m_view.setRemoved();
                    }

                    try {
                        int kingX = -1, kingY = -1;
                        int opKingX = -1, opKingY = -1;

                        if (Chessman.table[i][j].getColor().equals("white")){
                            for (int m = 0; m < 8; m++){
                                for (int n = 0; n < 8; n++){
                                    if (Chessman.table[m][n].getName().equals("\u265A") && Chessman.table[m][n].getColor().equals("black")){
                                        kingX = m;
                                        kingY = n;
                                    } else if (Chessman.table[m][n].getName().equals("\u265A") && Chessman.table[m][n].getColor().equals("white")){
                                        opKingX = m;
                                        opKingY = n;
                                    }
                                }
                            }
                        } else {
                            for (int m = 0; m < 8; m++){
                                for (int n = 0; n < 8; n++){
                                    if (Chessman.table[m][n].getName().equals("\u265A") && Chessman.table[m][n].getColor().equals("white")){
                                        kingX = m;
                                        kingY = n;
                                    } else if (Chessman.table[m][n].getName().equals("\u265A") && Chessman.table[m][n].getColor().equals("black")){
                                        opKingX = m;
                                        opKingY = n;
                                    }
                                }
                            }
                        }

                        /*
                        * If it is chess after a move, that piece will be moved back.
                        * In case of checkmate, the game is over and the winner will be displayed.*/
                        if (((King)Chessman.table[opKingX][opKingY]).chess(opKingX, opKingY, Chessman.table[opKingX][opKingY].getColor(), false)){
                            ok = false;
                            System.out.println(opKingX + " " + opKingY + ", " + i + " " + j);
                            Chessman.table[i][j].swapBack(poz[0], poz[1]);

                            Chessman pieceA;
                            Chessman pieceB;
                            if (outA){
                                pieceA = Chessman.getWhitePieces().get(Chessman.getWhitePieces().size() - 1);
                                Chessman.getWhitePieces().remove(pieceA);
                                Chessman.table[pieceA.getX()][pieceA.getY()] = pieceA;
                            }
                            if (outB){
                                pieceB = Chessman.getBlackPieces().get(Chessman.getBlackPieces().size() - 1);
                                Chessman.getBlackPieces().remove(pieceB);
                                System.out.println(pieceB.getX() + " " + pieceB.getY());
                                Chessman.table[pieceB.getX()][pieceB.getY()] = pieceB;
                            }
                        } else {
                            if (((King) Chessman.table[kingX][kingY]).checkmate(kingX, kingY, Chessman.table[kingX][kingY].getColor())) {
                                if (Chessman.table[kingX][kingY].getColor().equals("white")) {
                                    m_view.setWinner("CHECKMATE, black won");
                                    gameOver = true;
                                } else {
                                    m_view.setWinner("CHECKMATE, white won");
                                    gameOver = true;
                                }
                            }
                        }
                    } catch (Exception ex){
                        System.out.println("Exception");
                    }
                    /*
                    * If the move is ok, the piece will be moved on the forward table
                    * The 'moves' variable is used to know the turns (white or black)*/
                    if (ok){
                        // In case of castling, it will move the Rook
                        // The castling is possible just if the Rook and the Queen weren't moved before
                        if (Chessman.table[i][j].getName().equals("\u265A") && ((King) Chessman.table[i][j]).isCastling()) {
                            if (i == 0 || i == 7) {
                                if (j == 2) {
                                    m_view.swapButtons(poz[0], poz[1] - 4, i, j + 1);
                                }
                                else{
                                    m_view.swapButtons(poz[0], poz[1] + 3, i, j - 1);
                                }
                            }
                        }

                        m_view.swapButtons(poz[0], poz[1], i, j);
                        moves++;
                        if (moves % 2 == 0){
                            m_view.setTitle("WHITE TURN");
                        } else{
                            m_view.setTitle("BLACK TURN");
                        }

                        previousMoveX = poz[0];
                        previousMoveY = poz[1];
                        currentMoveX = i;
                        currentMoveY = j;
                        lastPiece = Chessman.table[i][j];
                    }
                    poz[0] = -1;
                    poz[1] = -1;
                }
            }
        }
    }

    public class ResetButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            m_view.reset();
            m_model.reset();
            moves = 0;
            gameOver = false;
        }
    }

    public class SettingsButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            m_view.showSettings();
        }
    }

    public class BackgroundListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            m_view.setImage(settingsView.getBackgroundBox().getSelectedItem() + "");
            m_view.setSquaresColor(settingsView.getSquaresColorBox1().getSelectedItem() + "", settingsView.getSquaresColorBox2().getSelectedItem() + "");
            m_view.setChessmanColor(settingsView.getChessmanColorBox1().getSelectedItem() + "", settingsView.getChessmanColorBox2().getSelectedItem() + "");
        }
    }

    public class UndoButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            lastPiece.swapBack(previousMoveX, previousMoveY);
            m_view.swapButtons(currentMoveX, currentMoveY, previousMoveX, previousMoveY);
            moves--;
            if (moves % 2 == 0){
                m_view.setTitle("WHITE TURN");
            } else{
                m_view.setTitle("BLACK TURN");
            }
        }
    }
}
