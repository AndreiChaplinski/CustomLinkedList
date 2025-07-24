import com.internship.CustomLinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomLinkedListTest {

    private CustomLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }

    @Test
    void testEmptyList() {
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
        assertThrows(NoSuchElementException.class, () -> list.getLast());
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    void testAddFirst() {
        list.addFirst("B");
        assertEquals(1, list.size());
        assertEquals("B", list.getFirst());
        assertEquals("B", list.getLast());

        list.addFirst("A");
        assertEquals(2, list.size());
        assertEquals("A", list.getFirst());
        assertEquals("B", list.getLast());
    }

    @Test
    void testAddLast() {
        list.addLast("A");
        assertEquals(1, list.size());
        assertEquals("A", list.getFirst());
        assertEquals("A", list.getLast());

        list.addLast("B");
        assertEquals(2, list.size());
        assertEquals("A", list.getFirst());
        assertEquals("B", list.getLast());
    }

    @Test
    void testAddAtIndex() {
        list.add(0, "A");
        list.add(1, "C");
        list.add(1, "B");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testGet() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testRemoveFirst() {
        list.addLast("A");
        list.addLast("B");

        assertEquals("A", list.removeFirst());
        assertEquals(1, list.size());
        assertEquals("B", list.getFirst());

        assertEquals("B", list.removeFirst());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveLast() {
        list.addLast("A");
        list.addLast("B");

        assertEquals("B", list.removeLast());
        assertEquals(1, list.size());
        assertEquals("A", list.getLast());

        assertEquals("A", list.removeLast());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveAtIndex() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        assertEquals("B", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));

        assertEquals("A", list.remove(0));
        assertEquals(1, list.size());
        assertEquals("C", list.getFirst());

        assertEquals("C", list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    void testNullElements() {
        assertThrows(NullPointerException.class, () -> list.addFirst(null));
        assertThrows(NullPointerException.class, () -> list.addLast(null));
        assertThrows(NullPointerException.class, () -> list.add(0, null));
    }

    @Test
    void testInvalidIndices() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "A"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"));

        list.addLast("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testComplexOperations() {
        list.addFirst("B");
        list.addFirst("A");
        list.addLast("D");
        list.add(2, "C");

        assertEquals(4, list.size());
        assertEquals("A", list.getFirst());
        assertEquals("D", list.getLast());

        assertEquals("A", list.removeFirst());
        assertEquals("D", list.removeLast());
        assertEquals("C", list.remove(1));

        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    void testSingleElementList() {
        list.addFirst("A");

        assertEquals("A", list.getFirst());
        assertEquals("A", list.getLast());
        assertEquals("A", list.get(0));

        assertEquals("A", list.removeFirst());
        assertEquals(0, list.size());

        list.addLast("A");
        assertEquals("A", list.removeLast());
        assertEquals(0, list.size());

        list.add(0, "A");
        assertEquals("A", list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    void testGetNodeOptimization() {
        for (int i = 0; i < 10; i++) {
            list.addLast("Element" + i);
        }

        assertEquals("Element0", list.get(0));
        assertEquals("Element4", list.get(4));

        assertEquals("Element5", list.get(5));
        assertEquals("Element9", list.get(9));
    }

    @Test
    void testRemoveAllElements() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        list.removeFirst();
        list.removeFirst();
        list.removeFirst();

        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }
}