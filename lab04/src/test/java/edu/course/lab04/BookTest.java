package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void booksWithSameIsbnAreEqualAndHaveSameHash() {
        Book a = new Book("111", "Java");
        Book b = new Book("111", "Another title");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        assertNotEquals(new Book("111", "Java"), new Book("222", "Java"));
    }

    @Test
    void equalsIsReflexiveAndSymmetric() {
        Book a = new Book("111", "Java");
        Book b = new Book("111", "Java");
        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void equalsWithNullAndOtherTypeIsFalse() {
        Book a = new Book("111", "Java");
        assertFalse(a.equals(null));
        assertFalse(a.equals("111"));
    }

    @Test
    void equalBookFindsValueInHashMap() {
        Map<Book, String> map = new HashMap<>();
        map.put(new Book("111", "Java"), "shelf A");
        assertEquals("shelf A", map.get(new Book("111", "Other")));
    }

    @Test
    void invalidArgumentsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "t"));
        assertThrows(IllegalArgumentException.class, () -> new Book(" ", "t"));
        assertThrows(IllegalArgumentException.class, () -> new Book("1", null));
    }
}