package ChessModel;
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
