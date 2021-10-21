package test;

import main.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BookTest {
    @Test
    void testEquals() {
        Book testBook = new Book("Book1", 29.99);
        Book sameBook = new Book("Book1", 29.99);
        assertEquals(sameBook, testBook);
        Book notSameBook = new Book("Book2", 25.99);
        assertNotEquals(notSameBook, testBook);
    }
}