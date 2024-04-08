package adry.fallingsand;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SandTest {

    @Test
    public void string() {
        // given (parameters)
        Sand sand = new Sand(3, 3);

        // when (one thing getting tested gets executed)
        String actual = sand.toString();

        // then (result)
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void put() {
        // given
        Sand sand = new Sand(3, 3);

        // when
        sand.put(1, 0);

        // then
        assertEquals("010\n000\n000\n", sand.toString());
    }

    @Test
    public void fall() {
        // given
        Sand sand = new Sand(3, 3);
        sand.load("010\n000\n000\n");

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void fallOnGround() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n010\n", sand.toString());
    }

    // make this into fall randomly right and left directions
    @Test
    public void fallRandomDirectionRight() {
        // given
        Random random = mock();

        doReturn(true).when(random).nextBoolean();
        Sand sand = new Sand(3, 3, random);
        sand.put(1, 1);
        sand.put(1, 2);
        sand.put(0, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n011", sand.toString());
    }

    @Test
    public void fallRandomDirectionLeft() {
        // given
        Random random = mock();

        doReturn(false).when(random).nextBoolean();
        Sand sand = new Sand(3, 3, random);
        sand.put(1, 1);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n110", sand.toString());
    }

    @Test
    // both grains of sand falling together
    public void fallSimultaneously() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);
        sand.put(1, 1);

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n010\n", sand.toString());
    }

    @Test
    public void fallLeftEdge() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(0, 1);
        sand.put(0, 2);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n100\n110\n", sand.toString());
    }

    @Test
    public void fallRightEdge() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(2, 1);
        sand.put(1, 2);
        sand.put(2, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n001\n011\n", sand.toString());
    }

    @Test
    public void resize() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 1);

        // when
        sand.resize(2, 2);

        // then
        assertEquals("00\n01\n", sand.toString());
    }

    @Test
    public void resizeLarger() {
        // given
        Sand sand = new Sand(2, 2);
        sand.put(1, 1);

        // when
        sand.resize(3, 3);

        // then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void load() {
        // given
        Sand sand = new Sand(2, 2);

        // when
        sand.load("000\n010\n000\n");

        // then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void putMultiple() {
        // given
        Sand sand = new Sand(5, 5);

        // when
        sand.put(1, 1, 3, 3, 1.00);

        // then
        assertEquals("00000\n01110\n01110\n01110\n00000\n", sand.toString());
    }

}