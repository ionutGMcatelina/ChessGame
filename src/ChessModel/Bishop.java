package ChessModel;

public class Bishop extends Chessman {
    public Bishop(String color, int x, int y){
        super(color, x, y);
        setNume( "\u265D");
    }

    private boolean freePd(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1, j = y + 1; i < thisX; i++, j++){
                if (!tabla[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = x - 1, j = y - 1; i > thisX; i--, j--){
                if (!tabla[i][j].getColor().equals("gol")){
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
                if (!tabla[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        else{
            for (int i = x - 1, j = y + 1; i > thisX; i--, j++){
                if (!tabla[i][j].getColor().equals("gol")){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean move(int x, int y) {
        if (Math.abs(x - getX()) == Math.abs(y - getY())){
            if (x < getX() && y < getY() || x > getX() && y > getY()){
                if (freePd(x, y)){
                    if (swap(x, y))
                        return true;
                    return false;
                }
                else{
                    System.out.println("Mutare invalida!");
                    return false;
                }
            }
            else{
                if (freeSd(x, y)){
                    if (swap(x, y))
                        return true;
                    return false;
                }
                else{
                    System.out.println("Mutare invalida!");
                    return false;
                }
            }
        }
        else{
            System.out.println("Mutare invalida!");
            return false;
        }
    }
}
