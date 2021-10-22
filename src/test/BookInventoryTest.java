package test;

import main.Book;
import main.BookInventory;
import main.Memento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookInventoryTest {
    private static BookInventory bookInventory;

    @BeforeAll
    static void beforeAll() {
        bookInventory = new BookInventory();
        addBooks();
    }

    @Test
    void testMemento() {
        Memento memento = bookInventory.save();
        assertEquals(memento.getMementoBookId(), 2);
        assertEquals(memento.getMementoBookInventory().toString(),
                "[Book{name='Book1', price=29.99, id='1', quantity=1}]");
        bookInventory.add(new Book("Book2", 19.99));
        bookInventory.restore(memento);
        assertEquals(memento.getMementoBookId(), 2);
        assertEquals(memento.getMementoBookInventory().toString(),
                "[Book{name='Book1', price=29.99, id='1', quantity=1}]");
    }

    @Test
    void testToString() {
        assertEquals(bookInventory.toString(),
                "[Book{name='Book1', price=29.99, id='1', quantity=1}]");
    }

    private static void addBooks() {
        bookInventory.add(new Book("Book1", 29.99));
    }
}