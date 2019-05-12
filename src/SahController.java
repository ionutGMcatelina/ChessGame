import ChessModel.Chessman;
import ChessModel.King;
import ChessModel.SahModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa care face legatura intre interfata grafica si functionalitate.
 */
class SahController {
    private SahView m_view;
    private SahModel m_model;
    private int [] poz = new int[2];
    private int mutari;
    private boolean gameOver = false;
    private SettingsView settingsView;

    private boolean outA;
    private boolean outB;
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
        mutari = 0;

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
         * Ascultator pentru apasarea unui buton de pe dabla de sah.
         */
        public void actionPerformed(ActionEvent e){
            if (!gameOver) {
                boolean ok = false;
                outA = false;
                outB = false;
                /*verific daca s-a retinut pozitia anterioara
                * daca nu, retin pozitia pe care s-a apasat in acest moment(daca nu este goala)
                * daca da, incerc sa fac swap intre piesa pe care am ales-o anterior si cea selectata acum
                * */
                if (poz[0] == -1) {
                    if (mutari % 2 == 0 && Chessman.tabla[i][j].getColor().equals("white") ||
                            mutari % 2 != 0 && Chessman.tabla[i][j].getColor().equals("black")) {
                        poz[0] = i;
                        poz[1] = j;
                    }
                } else {
                    int sizeA = Chessman.getPieseAlbe().size();
                    int sizeB = Chessman.getPieseNegre().size();
                    if (Chessman.tabla[poz[0]][poz[1]].move(i, j)) {
                        if (Chessman.getPieseAlbe().size() != sizeA){
                            outA = true;
                        }
                        if (Chessman.getPieseNegre().size() != sizeB){
                            outB = true;
                        }

                        ok = true;
                        m_view.setRemoved();
                    }

                    try {
                        int kingX = -1, kingY = -1;
                        int opKingX = -1, opKingY = -1;

                        if (Chessman.tabla[i][j].getColor().equals("white")){
                            for (int m = 0; m < 8; m++){
                                for (int n = 0; n < 8; n++){
                                    if (Chessman.tabla[m][n].getNume().equals("\u265A") && Chessman.tabla[m][n].getColor().equals("black")){
                                        kingX = m;
                                        kingY = n;
                                    } else if (Chessman.tabla[m][n].getNume().equals("\u265A") && Chessman.tabla[m][n].getColor().equals("white")){
                                        opKingX = m;
                                        opKingY = n;
                                    }
                                }
                            }
                        } else {
                            for (int m = 0; m < 8; m++){
                                for (int n = 0; n < 8; n++){
                                    if (Chessman.tabla[m][n].getNume().equals("\u265A") && Chessman.tabla[m][n].getColor().equals("white")){
                                        kingX = m;
                                        kingY = n;
                                    } else if (Chessman.tabla[m][n].getNume().equals("\u265A") && Chessman.tabla[m][n].getColor().equals("black")){
                                        opKingX = m;
                                        opKingY = n;
                                    }
                                }
                            }
                        }

                        /*
                        * daca dupa mutare este sah(regele este amenintat) mut piesa inapoi
                        * daca este sah mat, anunt castigatorul*/
                        if (((King)Chessman.tabla[opKingX][opKingY]).chess(opKingX, opKingY, Chessman.tabla[opKingX][opKingY].getColor(), false)){
                            ok = false;
                            System.out.println(opKingX + " " + opKingY + ", " + i + " " + j);
                            Chessman.tabla[i][j].swapBack(poz[0], poz[1]);

                            Chessman pieceA;
                            Chessman pieceB;
                            if (outA){
                                pieceA = Chessman.getPieseAlbe().get(Chessman.getPieseAlbe().size() - 1);
                                Chessman.getPieseAlbe().remove(Chessman.getPieseAlbe().size() - 1);
                                Chessman.tabla[pieceA.getX()][pieceA.getY()] = pieceA;
                            }
                            if (outB){

                                pieceB = Chessman.getPieseNegre().get(Chessman.getPieseNegre().size() - 1);
                                Chessman.getPieseNegre().remove(Chessman.getPieseNegre().size() - 1);
                                System.out.println(pieceB.getX() + " " + pieceB.getY());
                                Chessman.tabla[pieceB.getX()][pieceB.getY()] = pieceB;
                            }
                        } else {
                            if (((King) Chessman.tabla[kingX][kingY]).checkmate(kingX, kingY, Chessman.tabla[kingX][kingY].getColor())) {
                                if (Chessman.tabla[kingX][kingY].getColor().equals("white")) {
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
                    * tot ce am facut aici a fost pe tabla din spate (matricea de butoane)
                    * acum daca nu a fost sah, inversez piesele si pe interfata
                    * in functie de variabila 'mutari' aleg ce piesa trebuie mutata (alba sau neagra)*/
                    if (ok){
                        // daca se face rocada, mut si tura
                        // rocada se face doar daca nici regele si nici tura nu au mai fost mutate inainte
                        if (Chessman.tabla[i][j].getNume().equals("\u265A") && ((King) Chessman.tabla[i][j]).isCastling()) {
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
                        mutari++;
                        if (mutari % 2 == 0){
                            m_view.setTitle("WHITE TURN");
                        } else{
                            m_view.setTitle("BLACK TURN");
                        }

                        previousMoveX = poz[0];
                        previousMoveY = poz[1];
                        currentMoveX = i;
                        currentMoveY = j;
                        lastPiece = Chessman.tabla[i][j];
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
            mutari = 0;
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
            mutari--;
            if (mutari % 2 == 0){
                m_view.setTitle("WHITE TURN");
            } else{
                m_view.setTitle("BLACK TURN");
            }
        }
    }
}
