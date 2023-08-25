package linkedlist.doble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    private LinkedList<Integer> lista;
    @BeforeEach
    void setUp() {
        lista = new LinkedList<>();
    }

    @Test
    void testListaVacia() {
        assertEquals(0, lista.length());
        assertThrowsExactly(NoSuchElementException.class,
                () -> {
                    lista.getValue();
                });
        assertThrowsExactly(NoSuchElementException.class,
                () -> {
                    lista.remove();
                });
    }

    @Test
    public void testPrimerNodo() {
        lista.append(5);

        // que debo probar ahora??
        assertEquals(1, lista.length());
        lista.moveToStart();
        assertEquals(5, lista.getValue());
        lista.moveToEnd();
        assertEquals(5, lista.getValue());
    }

    @Test
    void testAppend() {
        lista.append(5);
        lista.append(15);
        lista.append(25);
        lista.append(35);
        lista.append(100);

        assertEquals(5, lista.length());
        lista.moveToStart();
        assertEquals(5, lista.getValue());

        lista.moveToPos(3);
        assertEquals(35, lista.getValue());

        lista.prev();
        assertEquals(25, lista.getValue());

        lista.moveToEnd();
        assertEquals(100, lista.getValue());
    }

    @Test
    void testInsertVarios() {
        lista.append(10);  // < |10 >
        lista.moveToStart();
        lista.insert(20);  // < 20 |10 >
        lista.moveToEnd();
        lista.insert(30);  // < 20 30 |10 >

       // assertEquals(3, lista.length());

        lista.moveToEnd();
        assertEquals(10, lista.getValue());

        lista.moveToStart();
        assertEquals(20, lista.getValue());

        lista.next();
        assertEquals(30, lista.getValue());
    }

    @Test
    void testRemoverVarios() {
        lista.append(10);
        lista.append(20);
        lista.append(30);

        lista.moveToStart();
        assertEquals(10, lista.remove());  // < |20, 30 >
        assertEquals(2, lista.length());
        lista.moveToEnd();
        assertEquals(30, lista.remove());  // < |20 >
        assertEquals(1, lista.length());
        assertEquals(20, lista.remove());  // < >
        assertEquals(0, lista.length());

        assertTrue(lista.isEmpty());

        assertThrowsExactly(NoSuchElementException.class, () -> {
            lista.remove();
        });
    }

}