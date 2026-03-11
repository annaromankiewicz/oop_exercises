package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack s1, s2;

    @BeforeEach
    void setUp() {
        s1 = new Stack();
        s2 = new Stack();
        s1.initStack(5);
        s2.initStack(7);
    }

    @Test
    void initStack() {
        assertEquals(5, s1.size());
        assertEquals(7, s2.size());
    }

    @Test
    void clear() {
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);

        assertEquals(4, s1.elements());

        s1.clear();

        assertEquals(0, s1.elements());

        s2.push(3);
        s2.push(4);
        assertEquals(2, s2.elements());
        s2.clear();
        assertEquals(0, s2.elements());


        // clear an empty stock
        s1.clear();
        assertEquals(0, s1.elements());

    }

    @Test
    void push() {

        // without overflow
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);

        assertEquals(4, s1.elements());


        // with overflow

        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);

        assertEquals(5,s1.elements());
    }


    @Test
    void pop() {
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);

        // pop without underflow

        assertEquals(4, s1.pop());
        assertEquals(3, s1.pop());
        assertEquals(2, s1.pop());
        assertEquals(1, s1.pop());

        // pop with underflow

        assertEquals(0, s1.elements());
        assertEquals(Integer.MIN_VALUE, s1.pop());

    }

    @Test
    void peek() {
        assertEquals(Integer.MIN_VALUE, s2.peek());
        s2.push(42);
        assertEquals(42, s2.peek());
        assertEquals(42, s2.peek());
        s2.push(13);
        assertEquals(13, s2.peek());
    }

    @Test
    void elements() {
        s1.push(10);
        assertEquals(1, s1.elements());

        s1.push(20);
        assertEquals(2, s1.elements());

        s1.push(30);
        assertEquals(3, s1.elements());

        s1.push(40);
        assertEquals(4, s1.elements());

        s1.push(50);
        assertEquals(5, s1.elements());

        // overflow

        s1.push(100);
        assertEquals(5, s1.elements());


    }

    @Test
    void size() {
        assertEquals(5, s1.size());
        assertEquals(7, s2.size());

        s1.push(12);
        s1.push(50);
        assertEquals(5, s1.size());

        s2.push(2);
        s2.push(5);
        s2.push(7);
        s2.pop();

        assertEquals(7, s2.size());

    }

    @Test
    void print() {
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);

        //  Set up a stream to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        s1.print();

        String printedOutput = outputStream.toString().trim();
        assertEquals("[1,2,3,4]", printedOutput);

    }
}