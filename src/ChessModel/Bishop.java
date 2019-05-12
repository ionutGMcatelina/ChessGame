package ChessModel;

public class Bishop extends Chessman {
    public Bishop(String color, int x, int y){
        super(color, x, y);
        setNume( "\u265D");
    }

    /**
     * Verifica daca pozitia pe care urmeaza sa fie mutata piesa se afla pe diagonala principala
     * din care face parte pozitia curenta.
     * @param x Linia pozitiei de verificat.
     * @param y Coloana pozitiei de verificat.
     * @return True daca conditia este inceplinita, false in caz contrar.
     */
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

    /**
     * Verifica daca pozitia pe care urmeaza sa fie mutata piesa se afla pe diagonala secundara
     * din care face parte pozitia curenta.
     * @param x Linia pozitiei de verificat.
     * @param y Coloana pozitiei de verificat.
     * @return True daca conditia este inceplinita, false in caz contrar.
     */
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

    /**
     * Muta piesa pe o alta pozitie.
     * @param x Linia pe care va fi mutat.
     * @param y Coloana pe care va fi mutat.
     * @return True daca s-a putut face mutarea, false in caz contrar.
     */
    @Override
    public boolean move(int x, int y) {
        if (Math.abs(x - getX()) == Math.abs(y - getY())){
            if (x < getX() && y < getY() || x > getX() && y > getY()){
                if (freePd(x, y)){
                    return swap(x, y);
                }
                else{
                    System.out.println("Mutare invalida!");
                    return false;
                }
            }
            else{
                if (freeSd(x, y)){
                    return swap(x, y);
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
