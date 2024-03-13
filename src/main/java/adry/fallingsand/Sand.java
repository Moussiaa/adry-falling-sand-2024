package adry.fallingsand;

import java.util.Random;

public class Sand {
    private final int[][] field;
    private final Random random;

    public Sand(int width, int height) {
        field = new int[height][width];
        this.random = new Random();
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder(); // using += with a string creates a string builder
        // every time it iterates thru the loop. This is more memory efficient way to concatenate strings IN A LOOP.

        for (int y = 0; y < field.length; y++) {   // this is y axis bc it runs downwards
            for (int x = 0; x < field[y].length; x++) {   // and here is x that runs horizontally
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    // put a grain of sand into the field

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * @sets the value in field to be 1 instead of defaulting to 0.
     */
    public void put(int x, int y) {
        field[y][x] = 1;
    }

    public void fall() {
        // moves all sand down one square (which is in field rn).
        // if sand reaches the bottom level / hits another piece of sand it stops.
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1) {
                    // sand falling straight down
                    if (field[y + 1][x] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x] = 1;
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? +1 : -1;
                    int direction2 = rightFirst ? -1 : +1;

                    if (field[y + 1][x + direction1] == 0) {
                        // does the sand fall to the right?
                        field[y][x] = 0;
                        field[y + 1][x + direction1] = 1;
                    } else if (field[y + 1][x + direction2] == 0) {
                        // does the sand fall to the left?
                        field[y][x] = 0;
                        field[y + 1][x + direction2] = 1;
                    }
                }
            }
        }
    }

    public void randomSand(int n) {
        for (int i = 0; i < n; i++) {
            int y = random.nextInt(field.length);
            int x = random.nextInt(field[y].length);
            put(x, y);
        }
    }
}






