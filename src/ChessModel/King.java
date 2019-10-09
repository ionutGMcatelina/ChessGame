package ChessModel;

/**
 * The king
 */

public class King extends Chessman{
    private boolean moved;
    private boolean castling;
    private int threatX;
    private int threatY;

    King(String color, int x, int y) {
        super(color, x, y);
        threatX = -1;
        threatY = -1;
        moved = false;
        castling = true;
        setName( "\u265A");
    }

    private boolean onTable(int x, int y){
        return (x >= 0 && x < 8) && (y >= 0 && y <8);
    }

    /**
     * It checks if the position (x, y) is threatened.
     * For example, for the queen and the Rook, it checks the line, column and the diagonals
     * @param color the color of the king that should be moved here
     * @param empty Boolean used to know if that position must be empty or not
     */
    public boolean chess(int x, int y, String color, boolean empty) {
            if (onTable(x, y) && !Chessman.table[x][y].getColor().equals(color) || !empty) {
                // KING
                try{
                    if (!table[x][y + 1].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x + 1][y + 1].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x + 1][y].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x + 1][y - 1].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x][y - 1].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x - 1][y - 1].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x - 1][y].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A") ||
                            !table[x - 1][y + 1].getColor().equals(color) && table[x][y + 1].getName().equals("\u265A")
                    ){
                        return true;
                    }
                } catch (Exception e){
                    System.out.println("Exceptionnn");
                }
                // QUEEN or ROOK
                for (int i = x + 1; i < 8; i++) {
                    if (!table[i][y].getColor().equals("gol")) {
                        if (!table[i][y].getColor().equals(color) &&
                                (table[i][y].getName().equals("\u265B") || table[i][y].getName().equals("\u265C"))) {
                            threatX = i;
                            threatY = y;
                            return true;
                        }
                        break;
                    }
                }

                for (int i = x - 1; i >= 0; i--) {
                    if (!table[i][y].getColor().equals("gol")) {
                        if (!table[i][y].getColor().equals(color) &&
                                (table[i][y].getName().equals("\u265B") || table[i][y].getName().equals("\u265C"))) {
                            threatX = i;
                            threatY = y;
                            return true;
                        }
                        break;
                    }
                }

                for (int i = y + 1; i < 8; i++) {
                    if (!table[x][i].getColor().equals("gol")) {
                        if (!table[x][i].getColor().equals(color) &&
                                (table[x][i].getName().equals("\u265B") || table[x][i].getName().equals("\u265C"))) {
                            threatX = x;
                            threatY = i;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = y - 1; i >= 0; i--) {
                    if (!table[x][i].getColor().equals("gol")) {
                        if (!table[x][i].getColor().equals(color) &&
                                (table[x][i].getName().equals("\u265B") || table[x][i].getName().equals("\u265C"))) {
                            threatX = x;
                            threatY = i;
                            return true;
                        }
                        break;
                    }
                }

                // QUEEN or BISHOP
                for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
                    if (!table[i][j].getColor().equals("gol")) {
                        if (!table[i][j].getColor().equals(color) &&
                                (table[i][j].getName().equals("\u265B") || table[i][j].getName().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                    if (!table[i][j].getColor().equals("gol")) {
                        if (!table[i][j].getColor().equals(color) &&
                                (table[i][j].getName().equals("\u265B") || table[i][j].getName().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
                    if (!table[i][j].getColor().equals("gol")) {
                        if (!table[i][j].getColor().equals(color) &&
                                (table[i][j].getName().equals("\u265B") || table[i][j].getName().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
                    if (!table[i][j].getColor().equals("gol")) {
                        if (!table[i][j].getColor().equals(color) &&
                                (table[i][j].getName().equals("\u265B") || table[i][j].getName().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }

                // PAWN
                if (!empty) {
                    if (color.equals("white")) {
                        if (onTable(x - 1, y - 1) && Chessman.table[x - 1][y - 1].getName().equals("\u265F") && Chessman.table[x - 1][y - 1].getColor().equals("black")) {
                            threatX = x - 1;
                            threatY = y - 1;
                            return true;
                        }
                        if (onTable(x - 1, y + 1) && Chessman.table[x - 1][y + 1].getName().equals("\u265F") && Chessman.table[x - 1][y + 1].getColor().equals("black")) {
                            threatX = x - 1;
                            threatY = y + 1;
                            return true;
                        }
                    } else if (color.equals("black")) {
                        if (onTable(x + 1, y - 1) && Chessman.table[x + 1][y - 1].getName().equals("\u265F") && Chessman.table[x + 1][y - 1].getColor().equals("white")) {
                            threatX = x + 1;
                            threatY = y - 1;
                            return true;
                        }
                        if (onTable(x + 1, y + 1) && Chessman.table[x + 1][y + 1].getName().equals("\u265F") && Chessman.table[x + 1][y + 1].getColor().equals("white")) {
                            threatX = x + 1;
                            threatY = y + 1;
                            return true;
                        }
                    }
                } else {
                    if (color.equals("white")) {
                        if (onTable(x - 1, y) && Chessman.table[x - 1][y].getName().equals("\u265F") && Chessman.table[x - 1][y].getColor().equals("black")) {
                            threatX = x - 1;
                            threatY = y;
                            return true;
                        }
                    }

                    if (color.equals("black")) {
                        if (onTable(x + 1, y) && Chessman.table[x + 1][y].getName().equals("\u265F") && Chessman.table[x + 1][y].getColor().equals("white")) {
                            threatX = x + 1;
                            threatY = y;
                            return true;
                        }
                    }
                }

                // KNIGHT
                if (onTable(x - 2, y - 1) && Chessman.table[x - 2][y - 1].getName().equals("\u265E") && !Chessman.table[x - 2][y - 1].getColor().equals(getColor())) {
                    threatX = x - 2;
                    threatY = y - 1;
                    return true;
                }

                if (onTable(x + 2, y - 1) && Chessman.table[x + 2][y - 1].getName().equals("\u265E") && !Chessman.table[x + 2][y - 1].getColor().equals(getColor())) {
                    threatX = x + 2;
                    threatY = y - 1;
                    return true;
                }

                if (onTable(x - 2, y + 1) && Chessman.table[x - 2][y + 1].getName().equals("\u265E") && !Chessman.table[x - 2][y + 1].getColor().equals(getColor())) {
                    threatX = x - 2;
                    threatY = y + 1;
                    return true;
                }

                if (onTable(x + 2, y + 1) && Chessman.table[x + 2][y + 1].getName().equals("\u265E") && !Chessman.table[x + 2][y + 1].getColor().equals(getColor())) {
                    threatX = x + 2;
                    threatY = y + 1;
                    return true;
                }

                if (onTable(x - 1, y - 2) && Chessman.table[x - 1][y - 2].getName().equals("\u265E") && !Chessman.table[x - 1][y - 2].getColor().equals(getColor())) {
                    threatX = x - 1;
                    threatY = y - 2;
                    return true;
                }

                if (onTable(x - 1, y + 2) && Chessman.table[x - 1][y + 2].getName().equals("\u265E") && !Chessman.table[x - 1][y + 2].getColor().equals(getColor())) {
                    threatX = x - 1;
                    threatY = y + 2;
                    return true;
                }

                if (onTable(x + 1, y - 2) && Chessman.table[x + 1][y - 2].getName().equals("\u265E") && !Chessman.table[x + 1][y - 2].getColor().equals(getColor())) {
                    threatX = x + 1;
                    threatY = y - 2;
                    return true;
                }

                if (onTable(x + 1, y + 2) && Chessman.table[x + 1][y + 2].getName().equals("\u265E") && !Chessman.table[x + 1][y + 2].getColor().equals(getColor())) {
                    threatX = x + 1;
                    threatY = y + 2;
                    return true;
                }

                return false;
            }
        System.out.println(" HERE");
        return true;
    }

    /**
     * It checks if its checkmate.
     * The method use the chess method to verify if the behind squares are threatened or not
     * @param color The color of the king
     */

    public boolean checkmate(int x, int y, String color){
        if (chess(x, y, color, false)){
            int threatX = this.threatX;
            int threatY = this.threatY;

            if (!chess(x, y - 1, color, true)) {
                return false;
            }
            if (!chess(x - 1, y - 1, color, true)){
                return false;
            }
            if (!chess(x - 1, y, color, true)){
                return false;
            }
            if (!chess(x - 1, y + 1, color, true)){
                return false;
            }
            if (!chess(x, y + 1, color, true)){
                return false;
            }
            if (!chess(x + 1, y + 1, color, true)){
                return false;
            }
            if (!chess(x + 1, y, color, true)){
                return false;
            }
            if (!chess(x + 1, y - 1, color, true)){
                return false;
            }

            if (chess(threatX, threatY, "black", false) && color.equals("white") || chess(threatX, threatY, "white", false) && color.equals("black")){
                return false;
            }

            if (threatX == x){              // It checks if there are squares between the king and the threat that can be occupied
                if (threatY < y) {
                    for (int i = threatY + 1; i < y; i++){
                        if (chess(threatX, i, "black", true) && color.equals("white") || chess(threatX, i, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                } else {
                    for (int i = threatY - 1; i > y; i--){
                        if (chess(threatX, i, "black", true) && color.equals("white") || chess(threatX, i, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                }
            } else if (threatY == y) {
                if (threatX < x) {
                    for (int i = threatX + 1; i < x; i++){
                        if (chess(i, threatY, "black", true) && color.equals("white") || chess(i, threatY, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                } else {
                    for (int i = threatY - 1; i > y; i--){
                        if (chess(i, threatY, "black", true) && color.equals("white") || chess(i, threatY, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                }
            }

            if (Math.abs(x - threatX) == Math.abs(y - threatY)){
                if (threatX < x){
                    if (threatY < y){
                        for (int i = threatX + 1, j = threatY + 1; i < x && j < y; i++, j++){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    } else {
                        for (int i = threatX + 1, j = threatY - 1; i < x && j > y; i++, j--){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    }
                } else {
                    if (threatY < y){
                        for (int i = threatX - 1, j = threatY + 1; i > x && j < y; i--, j++){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    } else {
                        for (int i = threatX - 1, j = threatY - 1; i > x && j > y; i--, j--){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean move(int x, int y) {
        if (!chess(x, y, getColor(), false)) {
            if (!moved) {
                castling = false;
                if (!((Rook) Chessman.table[getX()][getY() - 4]).isMoved() && (x == getX() && y == getY() - 2) &&
                        (!chess(x, getY() - 1, getColor(), false)) && (!chess(x, getY() - 2, getColor(), false)) && (!chess(x, getY() - 3, getColor(), false))) {
                    if (swap(x, y)) {
                        if ((Chessman.table[x][y - 2]).swap(x, y + 1)) {
                            moved = true;
                            castling = true;
                            return true;
                        } else
                            return false;
                    }
                    return false;
                } else if (!((Rook) Chessman.table[getX()][getY() + 3]).isMoved() && (x == getX() && y == getY() + 2)
                        && (!chess(x, getY() + 1, getColor(), false)) && (!chess(x, getY() + 2, getColor(), false))) {
                    if (swap(x, y)) {
                        if ((Chessman.table[x][y + 1]).swap(x, y - 1)) {
                            moved = true;
                            castling = true;
                            return true;
                        } else
                            return false;
                    }
                    return false;
                }
            }
            if (Math.abs(x - getX()) <= 1 && Math.abs(y - getY()) <= 1) {
                castling = false;
                if (swap(x, y)) {
                    moved = true;
                    return true;
                }
                return false;
            } else {
                System.out.println("Wrong move!");
                return false;
            }
        }
        System.out.println("Chess");
        return false;
    }

    public boolean isCastling() {
        return castling;
    }
}

