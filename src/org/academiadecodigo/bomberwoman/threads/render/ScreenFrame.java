package org.academiadecodigo.bomberwoman.threads.render;


import org.academiadecodigo.bomberwoman.Constants;
import org.academiadecodigo.bomberwoman.Game;
import org.academiadecodigo.bomberwoman.Utils;

/**
 * Created by miro on 04-11-2017.
 */
class ScreenFrame {

    private StringBuilder content = new StringBuilder("");

    private String[][] cells;

    ScreenFrame(int width, int height) {

        cells = new String[width][height];

        clearBoard();
    }

    private void clearBoard() {

        for(int x = 0; x < width(); x++) {

            for(int y = 0; y < height(); y++) {

                String cell = " ";
                if(isCorner(x, y)) {

                    cell = Constants.CORNER_CHAR;
                }
                else if(isSide(x)) {

                    cell = Constants.VERTICAL_CHAR;
                }
                else if(isVert(y)) {

                    cell = Constants.HORIZONTAL_CHAR;
                }

                cells[x][y] = cell;
            }
        }
    }

    int x = 0;
    void update() {

        updateContent();

        clearBoard();

        putStringAt("X", x++, 3);
    }

    private void updateContent() {

        content.setLength(0);        //This clears the content of the StringBuilder
        content.append("\r");
        for(int y = 0; y < height(); y++) {

            for(int x = 0; x < width(); x++) {

                content.append(cells[x][y]);

                if(x == width() - 1) {

                    content.append("\r");
                    content.append(System.lineSeparator());
                }
            }
        }
    }

    void putStringAt(String objectString, int x, int y) {

        x = Utils.clamp(x, 0, Game.WIDTH);
        y = Utils.clamp(y, 0, Game.HEIGHT);

        cells[x][y] = objectString;
    }

    String getContent() {

        return content.toString();
    }

    private boolean isCorner(int x, int y) {

        return x == 0 && y == 0 || x == 0 && y == height() - 1 || x == width() - 1 && y == 0 || x == width() - 1 && y == height() - 1;
    }

    private boolean isSide(int x) {

        return x == 0 || x == width() - 1;
    }

    private boolean isVert(int y) {

        return y == 0 || y == height() - 1;
    }

    private int width() {

        if(cells == null) {

            return 0;
        }
        return cells.length;
    }

    private int height() {

        if(cells == null) {

            return 0;
        }
        return cells[0].length;
    }
}