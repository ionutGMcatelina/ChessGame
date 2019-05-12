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

    void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Muta piesa pe o pozitie noua. Practic mutarea se face printr-un swap
     * intre piesa curenta si una goala, iar daca pe pozitia viitoare este o piesa
     * adversa, aceasta va fi stearsa de pe tabla.
     * @param x Linia pe care va fi mutata.
     * @param y Coloana pe care va fi mutata.
     * @return True daca s-a putut face mutarea, false in caz contrar.
     */
    boolean swap(int x, int y){
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

    /**
     * Muta piesa pe o pozitie anterioara.
     * @param x Va fi linia pe care a fost piesa.
     * @param y Va fi coloana pe care a fost piesa.
     */
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
