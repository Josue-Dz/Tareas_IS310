package linkedlist.simple.v1;

import linkedlist.simple.v1.SimpleLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> lista;
    @BeforeEach
    void setUp() {
        lista = new SimpleLinkedList<>();
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
    void testPrimerNodo() {
        lista.append(10);

        // que debo probar ahora??
        assertEquals(1, lista.length());
        lista.moveToStart();
        assertEquals(10, lista.getValue());
        lista.moveToEnd();
        assertEquals(10, lista.getValue());
    }

    @Test
    void testAppendVarios() {
        lista.append(10);
        lista.append(20);
        lista.append(30);

        assertEquals(3, lista.length());
        lista.moveToStart();
        assertEquals(10, lista.getValue());

        lista.moveToEnd();
        assertEquals(30, lista.getValue());
    }

    @Test
    void testInsertVarios() {
        lista.append(10);  // < |10 >
        lista.moveToStart();
        lista.insert(20);  // < 20 |10 >
        lista.moveToEnd();
        lista.insert(30);  // < 20 30 |10 >

        assertEquals(3, lista.length());

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

    @Test
    void testNavegaciones() {
        lista.append(10);
        lista.append(20);
        lista.append(30);

        lista.moveToStart();
        assertFalse(lista.isAtEnd());

        lista.moveToEnd();
        assertTrue(lista.isAtEnd());

        lista.prev();
        assertEquals(20, lista.getValue());

        lista.next();
        assertEquals(30, lista.getValue());

        lista.next();
        assertEquals(30, lista.getValue());

        lista.moveToStart();
        lista.prev();
        assertEquals(10, lista.getValue());

        lista.moveToPos(1);
        assertEquals(20, lista.getValue());

        assertFalse(lista.moveToPos(100));
    }
}