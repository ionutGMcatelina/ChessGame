package ChessModel;

public class Rook extends Chessman {

    private boolean moved;

    public Rook(String color, int x, int y){
        super(color, x, y);
        setNume( "\u265C");
    }

    private boolean freeX(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1; i < thisX; i++){
                if (!tabla[i][y].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = thisX + 1; i < x; i++){
                if (!tabla[i][y].getColor().equals("gol")){
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
                if (!tabla[x][i].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = thisY + 1; i < y; i++){
                if (!tabla[x][i].getColor().equals("gol")){
                    return false;
                }
            }
        }
        return true;
    }

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
            System.out.println("Mutare invalida!");
            return false;
        }
    }

    public boolean isMoved() {
        return moved;
    }
}
