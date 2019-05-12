package ChessModel;

public class Pawn extends Chessman{

    protected Pawn(String color, int x, int y){
        super(color, x, y);
        setNume( "\u265F");
    }

    /**
     * Muta piesa pe o alta pozitie.
     * @param x Linia pe care va fi mutat.
     * @param y Coloana pe care va fi mutat.
     * @return True daca s-a putut face mutarea, false in caz contrar.
     */
    @Override
    public boolean move(int x, int y) {
        if (getColor().equals("black")) {
            if ((x == getX() + 1 && y == getY() + 1 || x == getX() + 1 && y == getY() - 1)
                     && tabla[x][y].getColor().equals("white")){
                if (swap(x, y))
                    return true;
                return false;
            }
            else {
                if (Chessman.tabla[x][y].getColor().equals("gol")) {
                    if (getX() == 1) {
                        if (y == getY() && (x - getX() <= 2 && x - getX() >= 0)) {
                            if (swap(x, y))
                                return true;
                            return false;
                        }
                    } else {
                        if (y == getY() && (x - getX() <= 1 && x - getX() >= 0)) {
                            if (swap(x, y))
                                return true;
                            return false;
                        }
                    }
                }
            }
        }
        else if (getColor().equals("white")) {
            if ((x == getX() - 1 && y == getY() + 1 || x == getX() - 1 && y == getY() - 1)
                    && tabla[x][y].getColor().equals("black")){
                if (swap(x, y))
                    return true;
                return false;
            }
            else {
                if (Chessman.tabla[x][y].getColor().equals("gol")) {
                    if (getX() == 6) {
                        if (y == getY() && (getX() - x <= 2 && getX() - x >= 0)) {
                            if (swap(x, y))
                                return true;
                            return false;
                        }
                    } else {
                        if (y == getY() && (getX() - x <= 1 && getX() - x >= 0)) {
                            if (swap(x, y))
                                return true;
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
