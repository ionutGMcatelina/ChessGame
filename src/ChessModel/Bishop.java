package ChessModel;

public class Bishop extends Chessman {
    Bishop(String color, int x, int y){
        super(color, x, y);
        setName( "\u265D");
    }

    /**
     * It checks if the main diagonal is free
     */
    private boolean freePd(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1, j = y + 1; i < thisX; i++, j++){
                if (!table[i][j].getColor().equals("empty")){
                    return false;
                }
            }
        }
        else{
            for (int i = x - 1, j = y - 1; i > thisX; i--, j--){
                if (!table[i][j].getColor().equals("empty")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * It checks if the secondary diagonal is free
     */
    private boolean freeSd(int x, int y){
        int thisX = getX();
        if (x < thisX){
            for (int i = x + 1, j = y - 1; i < thisX; i++, j--){
                if (!table[i][j].getColor().equals("empty")){
                    return false;
                }
            }
        }
        else{
            for (int i = x - 1, j = y + 1; i > thisX; i--, j++){
                if (!table[i][j].getColor().equals("empty")){
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
                    return swap(x, y);
                }
                else{
                    System.out.println("Wrong move!");
                    return false;
                }
            }
            else{
                if (freeSd(x, y)){
                    return swap(x, y);
                }
                else{
                    System.out.println("Wrong move!");
                    return false;
                }
            }
        }
        else{
            System.out.println("Wrong move!");
            return false;
        }
    }
}
