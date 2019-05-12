package ChessModel;

public class King extends Chessman{

    private boolean moved;
    private boolean castling;
    private int threatX;
    private int threatY;
    private boolean checkmate;

    public King(String color, int x, int y) {
        super(color, x, y);
        checkmate = false;
        threatX = -1;
        threatY = -1;
        moved = false;
        castling = true;
        setNume( "\u265A");
    }

    /**
     * Verifica daca o anumita pozitie de pe tabla este amenintata.
     * Se iau toate pozitiile din care se poate ajunce pe acest patrat si se verifica daca este o piesa care sa ajunga
     * aici. De exemplu pentru Queen si pentru Bishop se merge pe diagonalele din care face parte patratul, iar pentru
     * Rook si tot pentru Queen se perge pe linie si pe coloana.
     * @param x Linia pe care se afla patratul.
     * @param y Coloana pe care se afla patratul.
     * @param color Culoarea regelui pentru care se face verificarea.
     * @param gol Boolean folosit pentru a lua cazul in care patratul trebuie sa fie gol sau nu.
     * @return True daca patratul este amenintat de o piesa adversa, false in caz contrar.
     */
    public boolean chess(int x, int y, String color, boolean gol) {
        try {
            if (Chessman.tabla[x][y].getColor().equals("gol") || !gol) {
                // KING
                try{
                    if (!tabla[x][y + 1].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x + 1][y + 1].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x + 1][y].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x + 1][y - 1].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x][y - 1].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x - 1][y - 1].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x - 1][y].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A") ||
                            !tabla[x - 1][y + 1].getColor().equals(color) && tabla[x][y + 1].getNume().equals("\u265A")
                    ){
                        return true;
                    }
                } catch (Exception e){
                    System.out.println("Exception");
                }
                // QUEEN or ROOK
                for (int i = x + 1; i < 8; i++) {
                    if (!tabla[i][y].getColor().equals("gol")) {
                        if (!tabla[i][y].getColor().equals(color) &&
                                (tabla[i][y].getNume().equals("\u265B") || tabla[i][y].getNume().equals("\u265C"))) {
                            threatX = i;
                            threatY = y;
                            return true;
                        }
                        break;
                    }
                }

                for (int i = x - 1; i >= 0; i--) {
                    if (!tabla[i][y].getColor().equals("gol")) {
                        if (!tabla[i][y].getColor().equals(color) &&
                                (tabla[i][y].getNume().equals("\u265B") || tabla[i][y].getNume().equals("\u265C"))) {
                            threatX = i;
                            threatY = y;
                            return true;
                        }
                        break;
                    }
                }

                for (int i = y + 1; i < 8; i++) {
                    if (!tabla[x][i].getColor().equals("gol")) {
                        if (!tabla[x][i].getColor().equals(color) &&
                                (tabla[x][i].getNume().equals("\u265B") || tabla[x][i].getNume().equals("\u265C"))) {
                            threatX = x;
                            threatY = i;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = y - 1; i >= 0; i--) {
                    if (!tabla[x][i].getColor().equals("gol")) {
                        if (!tabla[x][i].getColor().equals(color) &&
                                (tabla[x][i].getNume().equals("\u265B") || tabla[x][i].getNume().equals("\u265C"))) {
                            threatX = x;
                            threatY = i;
                            return true;
                        }
                        break;
                    }
                }

                // QUEEN or BISHOP
                for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
                    if (!tabla[i][j].getColor().equals("gol")) {
                        if (!tabla[i][j].getColor().equals(color) &&
                                (tabla[i][j].getNume().equals("\u265B") || tabla[i][j].getNume().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                    if (!tabla[i][j].getColor().equals("gol")) {
                        if (!tabla[i][j].getColor().equals(color) &&
                                (tabla[i][j].getNume().equals("\u265B") || tabla[i][j].getNume().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
                    if (!tabla[i][j].getColor().equals("gol")) {
                        if (!tabla[i][j].getColor().equals(color) &&
                                (tabla[i][j].getNume().equals("\u265B") || tabla[i][j].getNume().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }
                for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
                    if (!tabla[i][j].getColor().equals("gol")) {
                        if (!tabla[i][j].getColor().equals(color) &&
                                (tabla[i][j].getNume().equals("\u265B") || tabla[i][j].getNume().equals("\u265D"))) {
                            threatX = i;
                            threatY = j;
                            return true;
                        }
                        break;
                    }
                }

                // PAWN
                if (!gol) {
                    if (color.equals("white")) {
                        try {
                            if (Chessman.tabla[x - 1][y - 1].getNume().equals("\u265F") && Chessman.tabla[x - 1][y - 1].getColor().equals("black")) {
                                threatX = x - 1;
                                threatY = y - 1;
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException");
                        }
                        try {
                            if (Chessman.tabla[x - 1][y + 1].getNume().equals("\u265F") && Chessman.tabla[x - 1][y + 1].getColor().equals("black")) {
                                threatX = x - 1;
                                threatY = y + 1;
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException");
                        }
                    }

                    if (color.equals("black")) {
                        try {
                            if (Chessman.tabla[x + 1][y - 1].getNume().equals("\u265F") && Chessman.tabla[x + 1][y - 1].getColor().equals("white")) {
                                threatX = x + 1;
                                threatY = y - 1;
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException");
                        }
                        try {
                            if (Chessman.tabla[x + 1][y + 1].getNume().equals("\u265F") && Chessman.tabla[x + 1][y + 1].getColor().equals("white")) {
                                threatX = x + 1;
                                threatY = y + 1;
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException");
                        }
                    }
                } else {
                    if (color.equals("white")) {
                        try {
                            if (Chessman.tabla[x - 1][y].getNume().equals("\u265F") && Chessman.tabla[x - 1][y].getColor().equals("black")) {
                                threatX = x - 1;
                                threatY = y - 1;
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException");
                        }
                    }

                    if (color.equals("black")) {
                        try {
                            if (Chessman.tabla[x + 1][y].getNume().equals("\u265F") && Chessman.tabla[x + 1][y].getColor().equals("white")) {
                                threatX = x + 1;
                                threatY = y - 1;
                                return true;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException");
                        }
                    }
                }

                // KNIGHT
                try {
                    if (Chessman.tabla[x - 2][y - 1].getNume().equals("\u265E") && !Chessman.tabla[x - 2][y - 1].getColor().equals(getColor())) {
                        threatX = x - 2;
                        threatY = y - 1;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x + 2][y - 1].getNume().equals("\u265E") && !Chessman.tabla[x + 2][y - 1].getColor().equals(getColor())) {
                        threatX = x + 2;
                        threatY = y - 1;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x - 2][y + 1].getNume().equals("\u265E") && !Chessman.tabla[x - 2][y + 1].getColor().equals(getColor())) {
                        threatX = x - 2;
                        threatY = y + 1;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x + 2][y + 1].getNume().equals("\u265E") && !Chessman.tabla[x + 2][y + 1].getColor().equals(getColor())) {
                        threatX = x + 2;
                        threatY = y + 1;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x - 1][y - 2].getNume().equals("\u265E") && !Chessman.tabla[x - 1][y - 2].getColor().equals(getColor())) {
                        threatX = x - 1;
                        threatY = y - 2;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x - 1][y + 2].getNume().equals("\u265E") && !Chessman.tabla[x - 1][y + 2].getColor().equals(getColor())) {
                        threatX = x - 1;
                        threatY = y + 2;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x + 1][y - 2].getNume().equals("\u265E") && !Chessman.tabla[x + 1][y - 2].getColor().equals(getColor())) {
                        threatX = x + 1;
                        threatY = y - 2;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                try {
                    if (Chessman.tabla[x + 1][y + 2].getNume().equals("\u265E") && !Chessman.tabla[x + 1][y + 2].getColor().equals(getColor())) {
                        threatX = x + 1;
                        threatY = y + 2;
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException");
                }

                return false;
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("IndexOutOfBoundsException");
        }
        return true;
    }

    /**
     * Verifica daca este sah mat, adica daca toate casutele unde s-ar putea muta regele sunt in sah si daca nu se
     * poate pune nimic in calea piesei care ameninta (ex. pe diagonala intre regina adversa si rege).
     * @param x Linia pe care se afla regele.
     * @param y Coloana pe care se afla regele.
     * @param color Culoarea regelui.
     * @return True daca este sah mat, false in caz contrar.
     */

    public boolean checkmate(int x, int y, String color){
        if (chess(x, y, color, false)){
            int threatX = this.threatX;
            int threatY = this.threatY;

            if (!chess(x, y - 1, color, true)) {
                return false;
            }
            if (!chess(x - 1, y - 1, color, true)){
                return false;
            }
            if (!chess(x - 1, y, color, true)){
                return false;
            }
            if (!chess(x - 1, y + 1, color, true)){
                return false;
            }
            if (!chess(x, y + 1, color, true)){
                return false;
            }
            if (!chess(x + 1, y + 1, color, true)){
                return false;
            }
            if (!chess(x + 1, y, color, true)){
                return false;
            }
            if (!chess(x + 1, y - 1, color, true)){
                return false;
            }

            if (chess(threatX, threatY, "black", false) && color.equals("white") || chess(threatX, threatY, "white", false) && color.equals("black")){
                return false;
            }

            if (threatX == x){
                if (threatY < y) {
                    for (int i = threatY + 1; i < y; i++){
                        if (chess(threatX, i, "black", true) && color.equals("white") || chess(threatX, i, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                } else {
                    for (int i = threatY - 1; i > y; i--){
                        if (chess(threatX, i, "black", true) && color.equals("white") || chess(threatX, i, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                }
            } else if (threatY == y) {
                if (threatX < x) {
                    for (int i = threatX + 1; i < x; i++){
                        if (chess(i, threatY, "black", true) && color.equals("white") || chess(i, threatY, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                } else {
                    for (int i = threatY - 1; i > y; i--){
                        if (chess(i, threatY, "black", true) && color.equals("white") || chess(i, threatY, "white", true) && color.equals("black")){
                            return false;
                        }
                    }
                }
            }

            if (Math.abs(x - threatX) == Math.abs(y - threatY)){
                if (threatX < x){
                    if (threatY < y){
                        for (int i = threatX + 1, j = threatY + 1; i < x && j < y; i++, j++){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    } else {
                        for (int i = threatX + 1, j = threatY - 1; i < x && j > y; i++, j--){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    }
                } else {
                    if (threatY < y){
                        for (int i = threatX - 1, j = threatY + 1; i > x && j < y; i--, j++){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    } else {
                        for (int i = threatX - 1, j = threatY - 1; i > x && j > y; i--, j--){
                            if (chess(i, j, "black", true) && color.equals("white") || chess(i, j, "white", true) && color.equals("black")){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Muta piesa pe o alta pozitie. Metoda verifica si daca se poate face rocada,
     * adica daca regele si tura au fost mutate si daca a fost aleasa pozitia buna.
     * @param x Linia pe care va fi mutat.
     * @param y Coloana pe care va fi mutat.
     * @return True daca s-a putut face mutarea, false in caz contrar.
     */
    @Override
    public boolean move(int x, int y) {
        if (!chess(x, y, getColor(), false)) {
            if (!moved) {
                castling = false;
                if (!((Rook) Chessman.tabla[getX()][getY() - 4]).isMoved() && (x == getX() && y == getY() - 2) &&
                        (!chess(x, getY() - 1, getColor(), false)) && (!chess(x, getY() - 2, getColor(), false)) && (!chess(x, getY() - 3, getColor(), false))) {
                    if (swap(x, y)) {
                        if ((Chessman.tabla[x][y - 2]).swap(x, y + 1)) {
                            moved = true;
                            castling = true;
                            return true;
                        } else
                            return false;
                    }
                    return false;
                } else if (!((Rook) Chessman.tabla[getX()][getY() + 3]).isMoved() && (x == getX() && y == getY() + 2)
                        && (!chess(x, getY() + 1, getColor(), false)) && (!chess(x, getY() + 2, getColor(), false))) {
                    if (swap(x, y)) {
                        if ((Chessman.tabla[x][y + 1]).swap(x, y - 1)) {
                            moved = true;
                            castling = true;
                            return true;
                        } else
                            return false;
                    }
                    return false;
                }
            }
            if (Math.abs(x - getX()) <= 1 && Math.abs(y - getY()) <= 1) {
                castling = false;
                if (swap(x, y)) {
                    moved = true;
                    return true;
                }
                return false;
            } else {
                System.out.println("Mutare invalida!");
                return false;
            }
        }
        System.out.println("Chess");
        return false;
    }

    public boolean isCastling() {
        return castling;
    }
}

