package ChessModel;

public class Rook extends Chessman {

    private boolean moved = false;

    Rook(String color, int x, int y){
        super(color, x, y);
        setName( "\u265C");
    }

    /**
     * It checks if all of the squares between current position and the next one are empty
     * on that line.
     */
    private boolean freeX(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1; i < thisX; i++){
                if (!table[i][y].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else {
            for (int i = thisX + 1; i < x; i++) {
                if (!table[i][y].getColor().equals("gol")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * It checks if all of the squares between current position and the next one are empty
     * on that column.
     */
    private boolean freeY(int x, int y){
        int thisY = getY();
        if (y < thisY){
            for (int i = y + 1; i < thisY; i++){
                if (!table[x][i].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = thisY + 1; i < y; i++){
                if (!table[x][i].getColor().equals("gol")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method checks if the next position is on the same line or the same column
     * and if the methods freeX or freeY return true.
     */
    @Override
    public boolean move(int x, int y) {
        if ((x == getX() && freeY(x, y)) || (y == getY() && freeX(x, y))){
            if (swap(x, y)) {
                moved = true;
                return true;
            }
            return false;
        }
        else{
            System.out.println("Wrong move!");
            return false;
        }
    }

    /**
     * In some cases we can move the Rook just if id was not moved before (castling).
     * This method tell us if this is the first move or not.
     */
    boolean isMoved() {
        return moved;
    }
}
