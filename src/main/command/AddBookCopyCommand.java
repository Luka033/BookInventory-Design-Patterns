package main.command;

import main.Inventory;

public class AddBookCopyCommand implements InventoryCommand {
    private final int bookId;

    public AddBookCopyCommand(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.addCopy(this.bookId);
    }
}
