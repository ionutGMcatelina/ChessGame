package ChessModel;

/**
 * The queen
 * In this class I put the methods from Rook and Bishop, because the queen can move like both of them
 */
public class Queen extends Chessman {
    Queen(String color, int x, int y){
        super(color, x, y);
        setName( "\u265B");
    }

    private boolean freeX(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1; i < thisX; i++){
                if (!table[i][y].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = thisX + 1; i < x; i++){
                if (!table[i][y].getColor().equals("gol")){
                    return false;
                }
            }
        }
        return true;
    }

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

    private boolean freePd(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1, j = y + 1; i < thisX; i++, j++){
                if (!table[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = x - 1, j = y - 1; i > thisX; i--, j--){
                if (!table[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean freeSd(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1, j = y - 1; i < thisX; i++, j--){
                if (!table[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = x - 1, j = y + 1; i > thisX; i--, j++){
                if (!table[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public boolean move(int x, int y) {
        if ((x == getX() && freeY(x, y)) || (y == getY() && freeX(x, y))) {
            return swap(x, y);
        }
        else if (Math.abs(x - getX()) == Math.abs(y - getY())){
            if (x < getX() && y < getY() || x > getX() && y > getY()){
                if (freePd(x, y)){
                    return swap(x, y);
                }
                else{
                    return false;
                }
            }
            else{
                if (freeSd(x, y)){
                    return swap(x, y);
                }
                else{
                    return false;
                }
            }
        } else{
            return false;
        }
    }
}

