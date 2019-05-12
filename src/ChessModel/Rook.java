package ChessModel;

public class Rook extends Chessman {

    private boolean moved;

    Rook(String color, int x, int y){
        super(color, x, y);
        setNume( "\u265C");
    }

    /**
     * Verifica daca pozitia pe care urmeaza sa fie mutata piesa se afla pe aceeasi linie
     * din care face parte pozitia curenta.
     * @param x Linia pozitiei de verificat.
     * @param y Coloana pozitiei de verificat.
     * @return True daca conditia este inceplinita, false in caz contrar.
     */
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

    /**
     * Verifica daca pozitia pe care urmeaza sa fie mutata piesa se afla pe aceeasi coloana
     * din care face parte pozitia curenta.
     * @param x Linia pozitiei de verificat.
     * @param y Coloana pozitiei de verificat.
     * @return True daca conditia este inceplinita, false in caz contrar.
     */
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

    /**
     * Muta piesa pe o alta pozitie.
     * @param x Linia pe care va fi mutat.
     * @param y Coloana pe care va fi mutat.
     * @return True daca s-a putut face mutarea, false in caz contrar.
     */
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
