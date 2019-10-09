package ChessModel;

public class Pawn extends Chessman{
    Pawn(String color, int x, int y){
        super(color, x, y);
        setName( "\u265F");
    }

    /**
     * It checks if the square in front is free or
     * it checks if there's an enemy in the right-front or left-front position.
     */

    @Override
    public boolean move(int x, int y) {
        if (getColor().equals("black")) {
            if ((x == getX() + 1 && y == getY() + 1 || x == getX() + 1 && y == getY() - 1)
                     && table[x][y].getColor().equals("white")){
                return swap(x, y);
            }
            else {
                if (Chessman.table[x][y].getColor().equals("empty")) {
                    if (getX() == 1) {
                        if (y == getY() && (x - getX() <= 2 && x - getX() >= 0)) {
                            return swap(x, y);
                        }
                    } else {
                        if (y == getY() && (x - getX() <= 1 && x - getX() >= 0)) {
                            return swap(x, y);
                        }
                    }
                }
            }
        }
        else if (getColor().equals("white")) {
            if ((x == getX() - 1 && y == getY() + 1 || x == getX() - 1 && y == getY() - 1)
                    && table[x][y].getColor().equals("black")){
                return swap(x, y);
            }
            else {
                if (Chessman.table[x][y].getColor().equals("empty")) {
                    if (getX() == 6) {
                        if (y == getY() && (getX() - x <= 2 && getX() - x >= 0)) {
                            return swap(x, y);
                        }
                    } else {
                        if (y == getY() && (getX() - x <= 1 && getX() - x >= 0)) {
                            return swap(x, y);
                        }
                    }
                }
            }
        }
        return false;
    }
}
