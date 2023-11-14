import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnesCounterTest {

    @Test
    void countTest1() {

        int[] ints = new int[] {1};

        assertEquals(0, OnesCounter.count(ints));
    }

    @Test
    void countTest2() {

        int[] ints = new int[] {0};

        assertEquals(0, OnesCounter.count(ints));
    }

    @Test
    void countTest3() {

        int[] ints = new int[] {1, 1};

        assertEquals(1, OnesCounter.count(ints));
    }

    @Test
    void countTest4() {

        int[] ints = new int[] {1, 1, 0, 1};

        assertEquals(3, OnesCounter.count(ints));
    }

    @Test
    void countTest5() {

        int[] ints = new int[] {1, 1, 1, 0, 0, 1, 1, 0};

        assertEquals(3, OnesCounter.count(ints));
    }

    @Test
    void countTest6() {

        int[] ints = new int[] {1, 1, 1, 0, 1, 1, 0, 1, 1};

        assertEquals(5, OnesCounter.count(ints));
    }

    @Test
    void countTest7() {

        int[] ints = new int[] {1, 1, 1, 0, 0, 1, 1, 0, 1, 1};

        assertEquals(4, OnesCounter.count(ints));
    }

    @Test
    void countTest8() {

        int[] ints = new int[] {1, 0, 1, 0, 1, 1, 0, 1, 1};

        assertEquals(4, OnesCounter.count(ints));
    }
}