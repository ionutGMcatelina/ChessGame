package ChessModel;

public class Knight extends Chessman{
    public Knight(String color, int x, int y){
        super(color, x, y);
        setNume( "\u265E");
    }

    public boolean move(int x, int y){
        if ((tabla[x][y] instanceof Empty || !tabla[x][y].getColor().equals(getColor()))) {
            if (x == getX() - 2 || x == getX() + 2) {
                if (y == getY() - 1 || y == getY() + 1) {
                    if (swap(x, y)) {
                        return true;
                    }
                    return false;
                }
            } else if (x == getX() - 1 || x == getX() + 1) {
                if (y == getY() - 2 || y == getY() + 2) {
                    if (swap(x, y))
                        return true;
                    return false;
                }
            }
            System.out.println("Mutare invalida!");
            return false;
        }
        System.out.println("Mutare invalida!");
        return false;
    }
}
