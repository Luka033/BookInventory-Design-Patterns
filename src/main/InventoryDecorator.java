package main;

import main.command.*;
import main.utils.FileUtils;

import java.io.File;
import java.util.List;


/**
 * Decorator class used to handle CRUD operations for a {@link BookInventory} as well as saving its state.
 */
public class InventoryDecorator implements Inventory {
    private static final int BACKUP_EVERY_N = 10;
    private static final String MEMENTO_FILE = "memento.ser";
    private static final String COMMAND_FILE = "command.ser";
    private final FileUtils fileUtils = new FileUtils();
    private BookInventory inventory;
    private int commandCounter = 0;

    public InventoryDecorator(BookInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Book book) {
        return executeOperation(new AddBookCommand(book));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addCopy(int bookId) {
        return executeOperation(new AddBookCopyCommand(bookId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sell(int bookId) {
        return executeOperation(new SellBookCommand(bookId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changePrice(int bookId, double newPrice) {
        return executeOperation(new ChangePriceCommand(bookId, newPrice));
    }

    @Override
    public Book find(int bookId) {
        return null;
    }

    @Override
    public Book find(String bookName) {
        return null;
    }

    /**
     * Executes the given command
     *
     * @param inventoryCommand to execute
     * @return true if the operation was successful otherwise false
     */
    private boolean executeOperation(InventoryCommand inventoryCommand) {
        if (inventoryCommand.execute(this.inventory)) {
            save(inventoryCommand);
            return true;
        } else {
            System.out.println("Failed to execute command");
        }
        return false;
    }

    /**
     * Saves a given command to a file. After every BACKUP_EVERY_N commands a {@link Memento}
     * will be saved to disk and a different file. If successful the file with commands will
     * be wiped in order to save space.
     *
     * @param inventoryCommand to save to disk
     */
    private void save(InventoryCommand inventoryCommand) {
        if (this.commandCounter < BACKUP_EVERY_N) {
            boolean appendObject = this.commandCounter > 0 || new File(COMMAND_FILE).exists();
            this.fileUtils.saveState(inventoryCommand, COMMAND_FILE, appendObject, true);
            this.commandCounter++;
        } else {
            if (fileUtils.saveState(this.inventory.save(), MEMENTO_FILE, false, false)) {
                this.commandCounter = 0;
                new File(COMMAND_FILE).delete();
            }
        }
    }

    /**
     * Uses a the {@link FileUtils} utility class to retrieve a list of objects. Loops through the list
     * and restores the inventory to its most recently saved state.
     */
    public void restoreState() {
        List<Object> objects = this.fileUtils.restoreState(MEMENTO_FILE);
        objects.addAll(this.fileUtils.restoreState(COMMAND_FILE));
        System.out.println("Restoring State");
        for (Object obj : objects) {
            if (obj instanceof Memento) {
                Memento memento = (Memento) obj;
                this.inventory.restore(memento);
            } else if (obj instanceof InventoryCommand) {
                InventoryCommand command = (InventoryCommand) obj;
                command.execute(this.inventory);
            }
        }
    }

    public static String getMementoFile() {
        return MEMENTO_FILE;
    }

    public static String getCommandFile() {
        return COMMAND_FILE;
    }
}
