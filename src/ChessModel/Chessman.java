package ChessModel;

import java.util.ArrayList;

public abstract class Chessman {
    private String color;
    private String nume;
    private int x;
    private int y;
    private static ArrayList<Chessman> pieseAlbe;
    private static ArrayList<Chessman> pieseNegre;
    public static Chessman[][] tabla;

    static{
        pieseAlbe = new ArrayList<>();
        pieseNegre = new ArrayList<>();
        tabla = new Chessman[8][8];
    }

    public Chessman(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    protected boolean swap(int x, int y){
        if (!tabla[x][y].getColor().equals(color)) {
            if (x <= 7 && y <= 7) {
                if (!tabla[x][y].getColor().equals("gol")){
                    if (tabla[x][y].getColor().equals("black")){
                        pieseNegre.add(tabla[x][y]);
                    }
                    else{
                        pieseAlbe.add(tabla[x][y]);
                    }
                    tabla[x][y] = new Empty(x, y);
                }
                Chessman aux;
                aux = tabla[x][y];
                aux.setX(getX());
                aux.setY(getY());
                tabla[x][y] = tabla[getX()][getY()];
                tabla[getX()][getY ()] = aux;
                setX(x);
                setY(y);
                return true;
            } else {
                System.out.println("Mutare invalida!");
                return false;
            }
        }
        else{
            System.out.println("Mutare invalida!");
            return false;
        }
    }

    public void swapBack(int x, int y){
        Chessman aux;
        aux = tabla[x][y];
        aux.setX(getX());
        aux.setY(getY());
        tabla[x][y] = tabla[getX()][getY()];
        tabla[getX()][getY ()] = aux;
        setX(x);
        setY(y);
    }

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

    public static void reset(){
        pieseAlbe.clear();
        pieseNegre.clear();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void printTabla(){
        System.out.print("   ");
        for (int i = 0; i < 8; i++){
            System.out.print("     " + i + "     ");
        }
        System.out.println();
        int k = 0;
        for (Chessman[] p : tabla){
            System.out.println("   -----------------------------------------------------------------------------------------");
            System.out.print(k + "  ");
            for (Chessman ps : p){
                System.out.print("| " + ps + "  ");
            }
            System.out.println("|");
            k++;
        }
        System.out.println("   -----------------------------------------------------------------------------------------");
        System.out.println("Piese albe scoase:");
        System.out.println(pieseAlbe);
        System.out.println("Piese negre scoase: ");
        System.out.println(pieseNegre);
    }

    public static ArrayList<Chessman> getPieseAlbe() {
        return pieseAlbe;
    }

    public static ArrayList<Chessman> getPieseNegre() {
        return pieseNegre;
    }

    @Override
    public String toString(){
        return " " + nume;
    }
}
