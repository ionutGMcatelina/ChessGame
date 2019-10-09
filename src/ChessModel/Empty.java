package ChessModel;

/**
 * It represent an empty square (an invisible piece that does nothing).
 */
public class Empty extends Chessman{
    public Empty(int x, int y){
        super("gol", x, y);
        setNume("");
    }

    public boolean move(int x, int y){
        setX(x);
        setY(y);
        return true;
    }
}
