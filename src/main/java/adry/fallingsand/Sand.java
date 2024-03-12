package adry.fallingsand;

public class Sand {
    private int[][] field = new int[3][3];

    public String toString() {
        StringBuilder builder = new StringBuilder(); // using += with a string creates a string builder
        // every time it iterates thru the loop. This is more memory efficient way to concatenate strings IN A LOOP.

        for (int y = 0; y < field.length; y++) {   // this is y axis bc it goes down
            for (int x = 0; x < 3; x++) {   // and here is x that goes across
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

    // HOMEWORK: create a method
    public void fall() {
        // moves all sand down one square (which is in field rn).
        // if sand reaches the bottom level / hits another piece of sand it stops.
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                // sand falling straight down
                if (field[y][x] == 1 && field[y + 1][x] == 0) {
                    field[y][x] = 0;
                    field[y + 1][x] = 1;
                }
                // does the sand fall to the right?
                if (field[y][x] == 1 && field[y + 1][x + 1] == 0) {
                    field[y][x] = 0;
                    field[y + 1][x + 1] = 1;
                }

                // does the sand fall to the left?

                if (field[y][x] == 1 && field[y - 1][x - 1] == 0) {
                    field[y][x] = 0;
                    field[y + 1][x - 1] = 1;
                }
            }
        }
    }
}




