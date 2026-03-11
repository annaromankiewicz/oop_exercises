package stackAdvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class StackAdvancedTest {
    StackAdvanced s1;
    StackAdvanced s2;
    StackAdvanced s3;

    @BeforeEach
    void initTest() {
        s1 = new StackAdvanced(); // stack with size 5
        s2 = new StackAdvanced(3);
    }

    @Test
    void testPush() {
        // BEFORE
        s1.push(1);
        s1.push(2);
        s1.push(3);

        assertEquals(3, s1.elements());

        //AFTER

        s2.push(4);
        s2.push(5);

        s1.push(s2);
        assertEquals(5, s1.elements());

        //  Set up a stream to capture console output
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream1));

        s1.print();

        String printedOutput1 = outputStream1.toString().trim();
        assertEquals("[1,2,3,4,5]", printedOutput1);

        // full stack
        s1.push(7);
        s1.push(8);
        s1.push(s2);
        assertEquals(5, s1.elements());

        // empty stack

        s1.clear();
        s1.push(s2);
        assertEquals(2, s1.elements());

        s2.clear();
        s1.push(s2);
        assertEquals(2, s1.elements());

    }

    @Test
    void testClone() {
        s1.push(1);
        s1.push(2);
        s1.push(3);

        s2 = s1.clone();
        s2.pop();

        assertFalse(s1.equals(s2));
        assertTrue(s1.equals(s1.clone()));
    }

    @Test
    void testEquals() {
        s1.push(21);
        s1.push(42);
        s1.push(63);

        s3 = new StackAdvanced(s1);
        assertTrue(s1.equals(s3));

        s1.push(5);
        s1.push(6);
        s2.push(21);
        s2.push(42);
        s2.push(63);

        // first 3 elements are equal
        assertFalse(s1.equals(s3));
        //
        assertFalse(s1.equals(s2));

    }

    @Test
    void testFinalize() {
        s1.push(21);
        s1.push(42);
        s1.push(63);

        assertEquals(3, s1.elements());

        s1.finalize();

        assertTrue(s1.elements() == 0 && s1.size() == 0);
    }
}