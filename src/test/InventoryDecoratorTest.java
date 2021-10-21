package test;

import main.Book;
import main.BookInventory;
import main.InventoryDecorator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDecoratorTest {
    private static final String MEMENTO_FILE = InventoryDecorator.getMementoFile();
    private static final String COMMAND_FILE = InventoryDecorator.getCommandFile();
    private BookInventory inventory;
    private InventoryDecorator inventoryDecorator;

    @BeforeEach
    void beforeEach() {
        inventory = new BookInventory();
        inventoryDecorator = new InventoryDecorator(inventory);
    }

    @AfterAll
    static void afterAll() {
        clearFiles();
    }

    @Test
    void testAdd() {
        Book book1 = new Book("Book1", 29.99);
        assertTrue(inventoryDecorator.add(book1));
        assertEquals(inventory.find(book1.getId()), book1);

        Book book2 = new Book("Book2", 25.99);
        assertTrue(inventoryDecorator.add(book2));
        assertEquals(inventory.find(book2.getId()), book2);
    }

    @Test
    void testAddAlreadyExisting() {
        Book book1 = new Book("Book1", 29.99);
        inventoryDecorator.add(book1);
        assertEquals(inventory.find(book1.getId()).getQuantity(), 1);

        // Add another book in between for extra assurance
        inventoryDecorator.add(new Book("Book2", 25.99));
        inventoryDecorator.add(new Book("Book1", 29.99));
        assertEquals(inventory.find(book1.getId()).getQuantity(), 2);
    }

    @Test
    void testAddCopy() {
        Book book1 = new Book("Book1", 29.99);
        inventoryDecorator.add(book1);
        int bookId = book1.getId();
        assertEquals(inventory.find(bookId).getQuantity(), 1);
        assertTrue(inventoryDecorator.addCopy(bookId));
        assertEquals(inventory.find(bookId).getQuantity(), 2);
    }

    @Test
    void testSell() {
        assertFalse(inventoryDecorator.sell(1));
        Book book1 = new Book("Book1", 29.99);
        assertTrue(inventoryDecorator.add(book1));
        assertEquals(inventory.find(book1.getId()).getQuantity(), 1);
        assertTrue(inventoryDecorator.sell(book1.getId()));
        assertEquals(inventory.find(book1.getId()).getQuantity(), 0);
    }

    @Test
    void testChangePrice() {
        double newPrice = 9.99;
        assertFalse(inventoryDecorator.changePrice(1, newPrice));
        Book book1 = new Book("Book1", 29.99);
        assertTrue(inventoryDecorator.add(book1));
        assertEquals(inventory.find(book1.getId()).getPrice(), book1.getPrice());
        assertTrue(inventoryDecorator.changePrice(book1.getId(), newPrice));
        assertEquals(inventory.find(book1.getId()).getPrice(), newPrice);
    }


    private static void clearFiles() {
        if(new File(COMMAND_FILE).exists()) {
            new File(COMMAND_FILE).delete();
        }
        if(new File(MEMENTO_FILE).exists()) {
            new File(MEMENTO_FILE).delete();
        }
    }
}