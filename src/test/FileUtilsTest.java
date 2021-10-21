package test;

import main.*;
import main.command.*;
import main.utils.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FileUtilsTest {
    private static final String MEMENTO_FILE = InventoryDecorator.getMementoFile();
    private static final String COMMAND_FILE = InventoryDecorator.getCommandFile();
    private BookInventory inventory;
    private InventoryDecorator inventoryDecorator;
    private final FileUtils fileUtils = new FileUtils();


    @BeforeEach
    void beforeEach() {
        clearFiles();
        inventory = new BookInventory();
        inventoryDecorator = new InventoryDecorator(inventory);
    }

    @AfterAll
    static void afterAll() {
        clearFiles();
    }

    @Test
    void testFullRestoreState() {
        executeCommands();
        BookInventory restoredInventory = new BookInventory();
        InventoryDecorator tempInventoryDecorator = new InventoryDecorator(restoredInventory);
        tempInventoryDecorator.restoreState();
        System.out.println(restoredInventory);
        assertEquals(restoredInventory.toString(), inventory.toString());
    }

    @Test
    void testSerializable() {
        assertTrue(inventory instanceof Serializable);
        assertTrue(new Book("Book1", 29.99) instanceof Serializable);
        assertTrue(new Memento(new ArrayList<>(), 0) instanceof Serializable);
    }

    @Test
    void testSaveCommandSate() {
        InventoryCommand command = new AddBookCommand(new Book("Book1", 29.99));
        assertTrue(fileUtils.saveState(command, COMMAND_FILE, false, true));
        assertTrue(new File(COMMAND_FILE).exists());
    }

    @Test
    void testRestoreCommandSate() {
        inventoryDecorator.add(new Book("Book1", 29.99));
        List<Object> objects = fileUtils.restoreState(COMMAND_FILE);
        InventoryCommand command = (InventoryCommand) objects.get(0);
        assertTrue(command instanceof AddBookCommand);
    }

    @Test
    void testSaveMementoSate() {
        inventoryDecorator.add(new Book("Book1", 29.99));
        assertTrue(fileUtils.saveState(inventory.save(), MEMENTO_FILE, false, false));
        assertTrue(new File(MEMENTO_FILE).exists());
    }

    @Test
    void testRestoreMementoSate() {
        inventoryDecorator.add(new Book("Book1", 29.99));
        fileUtils.saveState(inventory.save(), MEMENTO_FILE, false, false);
        List<Object> objects = fileUtils.restoreState(MEMENTO_FILE);
        Memento memento = (Memento) objects.get(0);
        assertTrue(memento instanceof Memento);
        BookInventory restoredInventory = new BookInventory();
        restoredInventory.restore(memento);
        assertEquals(inventory.toString(), restoredInventory.toString());
    }

    private void executeCommands() {
        inventoryDecorator.add(new Book("Book1", 29.99));
        inventoryDecorator.addCopy(1);
        inventoryDecorator.addCopy(1);
        inventoryDecorator.add(new Book("Book2", 25.99));
        inventoryDecorator.add(new Book("Book3", 19.99));
        inventoryDecorator.sell(1);
        inventoryDecorator.sell(1);
        inventoryDecorator.add(new Book("Book4", 29.99));
        inventoryDecorator.add(new Book("Book5", 25.99));
        inventoryDecorator.add(new Book("Book6", 19.99));
        inventoryDecorator.sell(3);
        inventoryDecorator.changePrice(2, 9.99);
    }

    private static void clearFiles() {
        if (new File(COMMAND_FILE).exists()) {
            new File(COMMAND_FILE).delete();
        }
        if (new File(MEMENTO_FILE).exists()) {
            new File(MEMENTO_FILE).delete();
        }
    }
}