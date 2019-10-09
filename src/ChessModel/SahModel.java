package ChessModel;

public class SahModel {
    public SahModel(){
        Chessman.table[0][0] = new Rook("black", 0, 0);
        Chessman.table[0][1] = new Knight("black", 0, 1);
        Chessman.table[0][2] = new Bishop("black", 0, 2);
        Chessman.table[0][3] = new Queen("black", 0, 3);
        Chessman.table[0][4] = new King("black", 0, 4);
        Chessman.table[0][5] = new Bishop("black", 0, 5);
        Chessman.table[0][6] = new Knight("black", 0, 6);
        Chessman.table[0][7] = new Rook("black", 0, 7);

        Chessman.table[1][0] = new Pawn("black", 1, 0);
        Chessman.table[1][1] = new Pawn("black", 1, 1);
        Chessman.table[1][2] = new Pawn("black", 1, 2);
        Chessman.table[1][3] = new Pawn("black", 1, 3);
        Chessman.table[1][4] = new Pawn("black", 1, 4);
        Chessman.table[1][5] = new Pawn("black", 1, 5);
        Chessman.table[1][6] = new Pawn("black", 1, 6);
        Chessman.table[1][7] = new Pawn("black", 1, 7);

        Chessman.table[7][0] = new Rook("white", 7, 0);
        Chessman.table[7][1] = new Knight("white", 7, 1);
        Chessman.table[7][2] = new Bishop("white", 7, 2);
        Chessman.table[7][3] = new Queen("white", 7, 3);
        Chessman.table[7][4] = new King("white", 7, 4);
        Chessman.table[7][5] = new Bishop("white", 7, 5);
        Chessman.table[7][6] = new Knight("white", 7, 6);
        Chessman.table[7][7] = new Rook("white", 7, 7);

        Chessman.table[6][0] = new Pawn("white", 6, 0);
        Chessman.table[6][1] = new Pawn("white", 6, 1);
        Chessman.table[6][2] = new Pawn("white", 6, 2);
        Chessman.table[6][3] = new Pawn("white", 6, 3);
        Chessman.table[6][4] = new Pawn("white", 6, 4);
        Chessman.table[6][5] = new Pawn("white", 6, 5);
        Chessman.table[6][6] = new Pawn("white", 6, 6);
        Chessman.table[6][7] = new Pawn("white", 6, 7);

        for (int i = 2; i < 6; i++){
            for (int j = 0; j < 8; j++){
                Chessman.table[i][j] = new Empty(i, j);
            }
        }

    }

    public void reset(){
        Chessman.table[0][0] = new Rook("black", 0, 0);
        Chessman.table[0][1] = new Knight("black", 0, 1);
        Chessman.table[0][2] = new Bishop("black", 0, 2);
        Chessman.table[0][3] = new Queen("black", 0, 3);
        Chessman.table[0][4] = new King("black", 0, 4);
        Chessman.table[0][5] = new Bishop("black", 0, 5);
        Chessman.table[0][6] = new Knight("black", 0, 6);
        Chessman.table[0][7] = new Rook("black", 0, 7);

        Chessman.table[1][0] = new Pawn("black", 1, 0);
        Chessman.table[1][1] = new Pawn("black", 1, 1);
        Chessman.table[1][2] = new Pawn("black", 1, 2);
        Chessman.table[1][3] = new Pawn("black", 1, 3);
        Chessman.table[1][4] = new Pawn("black", 1, 4);
        Chessman.table[1][5] = new Pawn("black", 1, 5);
        Chessman.table[1][6] = new Pawn("black", 1, 6);
        Chessman.table[1][7] = new Pawn("black", 1, 7);

        Chessman.table[7][0] = new Rook("white", 7, 0);
        Chessman.table[7][1] = new Knight("white", 7, 1);
        Chessman.table[7][2] = new Bishop("white", 7, 2);
        Chessman.table[7][3] = new Queen("white", 7, 3);
        Chessman.table[7][4] = new King("white", 7, 4);
        Chessman.table[7][5] = new Bishop("white", 7, 5);
        Chessman.table[7][6] = new Knight("white", 7, 6);
        Chessman.table[7][7] = new Rook("white", 7, 7);

        Chessman.table[6][0] = new Pawn("white", 6, 0);
        Chessman.table[6][1] = new Pawn("white", 6, 1);
        Chessman.table[6][2] = new Pawn("white", 6, 2);
        Chessman.table[6][3] = new Pawn("white", 6, 3);
        Chessman.table[6][4] = new Pawn("white", 6, 4);
        Chessman.table[6][5] = new Pawn("white", 6, 5);
        Chessman.table[6][6] = new Pawn("white", 6, 6);
        Chessman.table[6][7] = new Pawn("white", 6, 7);

        for (int i = 2; i < 6; i++){
            for (int j = 0; j < 8; j++){
                Chessman.table[i][j] = new Empty(i, j);
            }
        }

        Chessman.reset();
    }


}
