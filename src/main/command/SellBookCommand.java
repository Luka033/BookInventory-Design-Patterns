package main.command;

import main.Inventory;

public class SellBookCommand implements InventoryCommand {
    private final int bookId;

    public SellBookCommand(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean execute(Inventory inventory) {
        return inventory.sell(this.bookId);
    }
}
