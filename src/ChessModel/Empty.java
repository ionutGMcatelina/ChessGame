package ChessModel;

/**
 * Reprezinta o piesa "goala", pentru a inlocui pozitiile libere.
 */
public class Empty extends Chessman{
    public Empty(int x, int y){
        super("gol", x, y);
        setNume("");
    }

    /**
     * Muta piesa pe o alta pozitie. Aceasta piesa poate fi mutata oriunde.
     * @param x Linia pe care va fi mutat.
     * @param y Coloana pe care va fi mutat.
     * @return True daca s-a putut face mutarea, false in caz contrar.
     */
    public boolean move(int x, int y){
        setX(x);
        setY(y);
        return true;
    }
}
