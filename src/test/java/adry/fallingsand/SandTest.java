package adry.fallingsand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandTest {

    @Test
    public void string() {
        // given (parameters)
        Sand sand = new Sand();

        // when (one thing getting tested gets executed)
        String actual = sand.toString();

        // then (result)
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void put() {
        // given
        Sand sand = new Sand();

        // when
        sand.put(1,0);

        // then
        assertEquals("010\n000\n000\n", sand.toString());
    }

    @Test
    public void fall() {
        // given
        Sand sand = new Sand();

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void fallOnGround() {
        // given
        Sand sand = new Sand();
        sand.put(1,2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n010\n", sand.toString());
    }

    @Test
    public void fallOtherSand() {
        // given
        Sand sand = new Sand();
        sand.put(1,1);
        sand.put(1,2);

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n010\n", sand.toString());
    }

}