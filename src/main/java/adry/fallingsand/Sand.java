package adry.fallingsand;

import java.util.Random;

public class Sand {
    private final Random random;
    private int[][] field;
    private int height;
    private int width;

    public Sand(int width, int height) {
        this(width, height, new Random()); // call the other constructor to reduce code duplication ?
    }

    // constructor to rig random for testing purposes
    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return field[0].length; // length of row (# columns)
    }

    public int getHeight() {
        return field.length;    // # of rows (length columns)
    }

    /**
     * Adds random sand to our field
     *
     * @param n the amount of sand to add.
     */
    // method that adds n pieces of sand into the field in random positions
    public void randomSand(int n) {
        for (int i = 0; i < n; i++) {
            int y = random.nextInt(field.length);
            int x = random.nextInt(field[y].length);
            put(x, y);
        }
    }

    /**
     * sets the value in field to be 1 instead of defaulting to 0.
     */
    public void put(int x, int y) {
        field[y][x] = 1;
    }

    /**
     * Add sand to field
     *
     * @param startX      centerX
     * @param startY      centerY
     * @param width
     * @param height      the radius of the circle
     * @param probability that an empty spot in the circle will be sand.
     */
    public void put(int startX, int startY, int width, int height, double probability) {
        // starting at x, y to x + width and y+ height, set each item in field to be a sand if random.nextDouble() < probability
        for (int y = startY; y < startY + width; y++) {
            for (int x = startX; x < startX + height; x++) {
                if (random.nextDouble() <= probability)
                    put(x, y);
            }
        }
    }

    public void load(String sandString) {
        // takes string representation of sand and modifies internally
        int y = 0;
        int x = 0;

        for (int i = 0; i < sandString.length(); i++) {
            char c = sandString.charAt(i);
            switch (c) {
                case '\n' -> {
                    y++;
                    x = 0;
                }
                case '1' -> {
                    field[y][x] = 1;
                    x++;
                }
                default -> {
                    field[y][x] = 0;
                    x++;
                }

            }
        }
    }
//        String[] rows = sandString.split("\n");
//
//        for (int i = 0; i < rows.length; i++) {
//            String newRow = rows[y];
//            for (int s = 0; s < newRow.length(); s++) {
//                char index = newRow.charAt(x);
//                if (index == 1) {
//                    field[y][x] = 1;
//                } else {
//                    field[y][x] = 0;
//                }
//            }
//        }
//    }

    public String toString() {
        StringBuilder builder = new StringBuilder(); // using += with a string creates a string builder
        // every time it iterates through the loop. This is more memory efficient way to concatenate strings IN A LOOP.

        for (int y = 0; y < field.length; y++) {   // this is y-axis bc it runs downwards
            for (int x = 0; x < field[y].length; x++) {   // and here is x that runs horizontally
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * Moves all sand down one square if there is space
     */
    public void fall() {
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                if (isSand(x, y)) {
                    moveSandDown(x, y);
                }
            }
        }
    }

    /**
     * @return true if there is sand at the coordinates, otherwise false
     */
    public boolean isSand(int x, int y) {
        return field[y][x] == 1;
    }

    /**
     * Moves the sand down one square, or diagonally to the right or left
     */
    private void moveSandDown(int x, int y) {
        // move down
        if (move(x, y, x, y + 1)) {
            return;
        }

        // choose either left or right
        int direction = random.nextBoolean() ? +1 : -1;

        // move diagonally down in one direction
        if (move(x, y, x + direction, y + 1)) {
            return;
        }

        // move diagonally down in the other direction
        move(x, y, x - direction, y + 1);
    }

    /**
     * Attempts to move the sand from x1, y1 to x2, y2
     *
     * @return true if the move was successful, otherwise false
     */
    public boolean move(int x1, int y1, int x2, int y2) {
        if (inBounds(x2, y2) && isSand(x1, y1) && !isSand(x2, y2)) {
            field[y1][x1] = 0;
            field[y2][x2] = 1;
            return true;
        }
        return false;
    }

    /**
     * @return true if the coordinates are in the field, otherwise false
     */
    public boolean inBounds(int x, int y) {
        return 0 <= x && x < field[y].length;
    }

    /**
     * Change the width and height of the field. Keep the contents.
     */
    public void resize(int width, int height) {
        if (height == field.length && width == field[0].length) {
            return;
        }
        // changes size of the field
        int[][] fieldResize = new int[height][width];

        for (int y = 0; y < Math.min(field.length, fieldResize.length); y++) {
            System.arraycopy(field[y], 0, fieldResize[y], 0, Math.min(field[y].length, fieldResize[y].length));
        }
        field = fieldResize;
    }

//    /**
//     * Moves all sand down one square if there is space
//     */
//  /* public void fall() {
//        for (int y = field.length - 2; y >= 0; y--) {
//            for (int x = 0; x < field[y].length; x++) {
//                if (isSand(x, y)) {
//                    moveSandDown(x, y);
//                }
//            }
//        }
//    }
//
//    /**
//     * reports whether a given position is filled
//     */
//    public boolean position(int x, int y) {
//        return isSand(y, x);
//    }
//
//
//    public void move(int x1, int y1, int x2, int y2) {
//        if (isSand(x1, y1) && inBounds(x2, y2) && !isSand(x2, y2)) {
//            field[y1][x1] = 0;
//            field[y2][x2] = 1;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean inBounds(int x, int y) {
//
//    }
//
//    public void fall() {
//        // moves all sand down one square (which is in field rn).
//        // if sand reaches the bottom level / hits another piece of sand it stops.
//        for (int y = field.length - 2; y >= 0; y--) {
//            for (int x = 0; x < field[y].length; x++) {
//                if (isSand(x, y)) {
//                    // sand falling straight down
//                    if (!isSand(x, y + 1)) {
//                        move(x, y, x, y + 1);
//                        continue;
//                    }
//
//                    // ALL GOES IN THE MOVE METHOD
//                    boolean rightFirst = random.nextBoolean();
//                    int direction1 = rightFirst ? +1 : -1; // ternary operator => 'condition' ? true : false
//                    int direction2 = rightFirst ? -1 : +1; // if true move left
//
//                    // check that sand remains in bounds for x and y dimensions, as well as below
//                    if (x + direction1 >= 0 && x + direction1 <= field[y].length
//                            && field[y + 1][x + direction1] == 0) {
//                        // does the sand fall to the right?
//                        field[y][x] = 0;
//                        field[y + 1][x + direction1] = 1;
//                    } else if (x + direction2 >= 0 && x + direction2 <= field[y].length
//                            && field[y + 1][x + direction2] == 0) {
//                        // does the sand fall to the left?
//                        move(x, y, x + direction1, y);
//                        field[y][x] = 0;
//                        field[y + 1][x + direction2] = 1;
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean isSand(int y, int x) {
//        return field[y][x] == 1;
//    }

}


// homework: complete resize, load, and put with tests for all of them, add animation and mouse input

