package ChessModel;

import java.util.ArrayList;

public abstract class Chessman implements Comparable<Chessman> {
    private String color;
    private String name;
    private int x;
    private int y;
    private static ArrayList<Chessman> whitePieces;
    private static ArrayList<Chessman> blackPieces;
    public static Chessman[][] table;

    static{
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        table = new Chessman[8][8];
    }

    public Chessman(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    /**
     * The method put the piece on the (x, y) position
     * This method will be called from each class if the move respects the rules from that piece
     */
    boolean swap(int x, int y){
        if (!table[x][y].getColor().equals(color)) {
            if (x <= 7 && y <= 7) { // This is because we cannot move where is a piece with the same color
                if (!table[x][y].getColor().equals("gol")){
                    if (table[x][y].getColor().equals("black")){
                        whitePieces.add(table[x][y]);
                    }
                    else{
                        blackPieces.add(table[x][y]);
                    }
                    table[x][y] = new Empty(x, y);
                }
                Chessman aux;
                aux = table[x][y];
                aux.setX(getX());
                aux.setY(getY());
                table[x][y] = table[getX()][getY()];
                table[getX()][getY ()] = aux;
                setX(x);
                setY(y);
                return true;
            } else {        // This can be useless because in the GUI it is impossible to go out of the table
                System.out.println("Wrong move!");
                return false;
            }
        }
        else{
            System.out.println("Wrong move!");
            return false;
        }
    }

    /**
     * It moves the piece in the previous position.
     */
    public void swapBack(int x, int y){
        Chessman aux;
        aux = table[x][y];
        aux.setX(getX());
        aux.setY(getY());
        table[x][y] = table[getX()][getY()];
        table[getX()][getY ()] = aux;
        setX(x);
        setY(y);
    }

    /**
     * This abstract method moves the piece
     * It is separate implemented in each class that inherits this class
     * @param x The line of the next position
     * @param y The column of the next position
     * @return True if the move can be made,, false otherwise
     */
    public abstract boolean move(int x, int y);

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    static void reset(){
        whitePieces.clear();
        blackPieces.clear();
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    public static ArrayList<Chessman> getWhitePieces() {
        return whitePieces;
    }

    public static ArrayList<Chessman> getBlackPieces() {
        return blackPieces;
    }

    @Override
    public String toString(){
        return " " + name;
    }

    @Override
    public int compareTo(Chessman o) {
        return this.name.compareTo(o.name);
    }
}
